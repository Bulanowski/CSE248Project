import java.io.StringReader;
import java.util.HashMap;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Alex on 5/6/2017.
 */
@Path("/event")
public class MasterClubEventsManager {

    private static final HashMap<Integer, ClubEvent> clubEvents = new HashMap<>();

    @POST
    @Path("/register")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String registerEvent(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        String username = jsonObject.getString("username");
        Account account = AccountAccessManager.accountsBag.getUser(username);
        if (account instanceof Establishment) {
            Establishment establishment = (Establishment) account;
            String date = jsonObject.getString("date");
            String time = jsonObject.getString("time");
            Double price = jsonObject.getJsonNumber("price").doubleValue();
            Integer maxTickets = jsonObject.getInt("tickets");
            ClubEvent clubEvent = new ClubEvent(establishment, date, time, price, maxTickets);
            clubEvents.put(clubEvent.getEventID(), clubEvent);
            establishment.addEvent(clubEvent.getEventID());
            return "Event created successfully";
        }
        return "Event creation failed";
    }
}
