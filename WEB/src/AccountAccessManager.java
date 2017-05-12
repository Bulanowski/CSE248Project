import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.naming.directory.InvalidAttributeIdentifierException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;

/**
 * Created by Alex on 5/3/2017.
 */
@Path("/account")
public class AccountAccessManager {

    @EJB
    AccountsBag accountsBag = new AccountsBag();

    @EJB
    TokenManager tokenManager = new TokenManager();

    @POST
    @Path("/register")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String register(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        // TODO check for validity and availability of entered information
        JsonObjectBuilder registrationErrorBuilder = Json.createObjectBuilder();
        boolean error = false;
        if (accountsBag.usernameInUse(jsonObject.getString("username"))) {
            registrationErrorBuilder.add("username", "Username is already in use");
            error = true;
        }
//        if (!jsonObject.getString("email").matches("(\\S+@\\w+\\.\\S+)")) {
//            registrationErrorBuilder.add("email", "This is not a valid email address");
//            error = true;
//        }
        if (error) {
            return registrationErrorBuilder.build().toString();
        }
        try {
            // TODO change this to just pass through the jsonObject
            accountsBag.addAccount(new Account(jsonObject));
        } catch (InvalidAttributeIdentifierException ex) {
            return "Registration failed";
        }
        return "Register Successful";
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        if (accountsBag.verifyLogin(username, password)) {
            JsonObject json = Json.createObjectBuilder()
                    .add("url", (accountsBag.getUser(username).getProfile() instanceof Customer ? "homepage/" : "homepage-establishment/"))
                    .add("token", tokenManager.assignNewToken(username))
                    .add("account", accountsBag.getUser(username).toJson())
                    .build();
            return json.toString();
        } else
            return "Login Failed";
    }

    @POST
    @Path("/signOut")
    @Consumes(MediaType.TEXT_PLAIN)
    public void signOut(String token) {
        tokenManager.removeUser(token);
    }

    @POST
    @Path("/settings/changePassword")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String changePassword(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        if (tokenManager.authenticateToken(jsonObject.getString("token"))) {
            Account account = accountsBag.getUser(tokenManager.getUsername(jsonObject.getString("token")));
            if (account.changePassword(jsonObject.getString("oldPassword"), jsonObject.getString("newPassword"))) {
                return "Password changed successfully";
            }
        }
        return "Password change failed";
    }

    @POST
    @Path("/settings/get")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getSettings(String token) {
        if (tokenManager.authenticateToken(token)) {
            Account account = accountsBag.getUser(tokenManager.getUsername(token));
            return account.getProfile().toJson().toString();
        }
        return "Invalid Token";
    }

    @POST
    @Path("/settings/set")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String setSettings(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        if (tokenManager.authenticateToken(jsonObject.getString("token"))) {
            Profile profile = accountsBag.getUser(tokenManager.getUsername(jsonObject.getString("token"))).getProfile();
            profile.setName(jsonObject.getString("name"));
            profile.setAddress(jsonObject.getString("address"));
            profile.setPhone(jsonObject.getString("phone"));
            profile.setZip(jsonObject.getString("zip"));
            if (profile instanceof Customer) {
                Customer customer = (Customer) profile;
                customer.setBirthday(jsonObject.getString("birthday"));
            } else if (profile instanceof Establishment) {
                Establishment establishment = (Establishment) profile;
                establishment.setTimeOpen(jsonObject.getString("timeOpen"));
                establishment.setTimeClose(jsonObject.getString("timeClose"));
            }
            return "Settings changed successfully";
        }
        return "Settings change failed";
    }

    @POST
    @Path("/token")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getUserFromToken(String token) {
        String username = tokenManager.getUsername(token);
        if (username == null) {
            return "Token Not Found";
        }
        Account account = accountsBag.getUser(username);
        if (account == null) {
            return "Username Not Found";
        }
        return account.toJson().toString();
    }
}
