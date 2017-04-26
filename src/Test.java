import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.StringReader;

/**
 * Created by phil on 4/20/17.
 */
@Path("/loginJava")
public class Test {

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String postMethod(String string) {
        JsonObject jsonObject = Json.createReader(new StringReader(string)).readObject();
        if (jsonObject.getString("password").equals("bar")) {
            JsonObject json = Json.createObjectBuilder()
                    .add("url", "/CSE248_war_exploded/homepage/")
                    .add("username", jsonObject.getString("username"))
                    .build();
            return json.toString();
        }
        else
            return "Login Failed";
    }
}