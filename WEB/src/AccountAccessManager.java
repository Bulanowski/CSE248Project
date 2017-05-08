import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.StringReader;

/**
 * Created by Alex on 5/3/2017.
 */
@Path("/account")
public class AccountAccessManager {

    public static final AccountsBag accountsBag = new AccountsBag();
    private static final TokenManager tokenManager = new TokenManager();

    @POST
    @Path("/register")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String register(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        // TODO check for validity and availability of entered information
        JsonObjectBuilder registrationErrorBuilder = Json.createObjectBuilder();
        if (accountsBag.usernameInUse(jsonObject.getString("username"))) {
            registrationErrorBuilder.add("username", "Username is already in use");
        }
        if (!jsonObject.getString("email").matches("(\\S+@\\w+\\.\\S+)")) {
            registrationErrorBuilder.add("email", "This is not a valid email address");
        }
        Account account;
        if (jsonObject.getString("account").equals("Customer")) {
            account = new Customer(
                    jsonObject.getString("username"),
                    jsonObject.getString("password"),
                    jsonObject.getString("email"),
                    jsonObject.getString("address"),
                    jsonObject.getString("zip"),
                    jsonObject.getString("phone"),
                    jsonObject.getString("birthday")
            );
        } else if (jsonObject.getString("account").equals("Establishment")) {
            account = new Establishment(
                    jsonObject.getString("username"),
                    jsonObject.getString("password"),
                    jsonObject.getString("email"),
                    jsonObject.getString("address"),
                    jsonObject.getString("zip"),
                    jsonObject.getString("phone")
            );
        } else {
            return "Registration failed";
        }
        accountsBag.addAccount(account);
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
                    .add("url", (accountsBag.getUser(username) instanceof Customer ? "homepage/" : "homepage-establishment/"))
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
