import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.StringReader;

/**
 * Created by phil on 4/20/17.
 */
@Path("user/{string}")
public class Test {

    @GET
    @Produces("text/plain")
    public String send(@PathParam("string") String name) {
        return name;
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String postMethod(String string) {
        JsonObject jsonObject = Json.createReader(new StringReader(string)).readObject();
        if (jsonObject.getString("password").equals("bar")) {
            JsonObject json = Json.createObjectBuilder()
                    .add("url", "homepage.jsp")
                    .add("username", jsonObject.getString("username"))
                    .build();
            return json.toString();
        }
        else
            return "Login Failed";
    }
}