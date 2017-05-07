import javax.json.Json;
import javax.json.JsonObject;
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
        if (accountsBag.usernameInUse(jsonObject.getString("username"))) {
            return "Username in use";
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
}
