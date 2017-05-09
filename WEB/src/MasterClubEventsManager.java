import java.io.StringReader;
import java.util.HashMap;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
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
        // TODO validity checking
        String token = jsonObject.getString("token");
        String username = AccountAccessManager.tokenManager.getUsername(token);
        if (username == null) {
            return "Invalid token";
        }
        Account account = AccountAccessManager.accountsBag.getUser(username); // If this is null the user account doesn't exist
        if (account instanceof Establishment) {
            Establishment establishment = (Establishment) account;
            ClubEvent clubEvent = new ClubEvent(establishment, jsonObject);
            clubEvents.put(clubEvent.getEventID(), clubEvent);
            establishment.addEvent(clubEvent.getEventID());
            return "Event created successfully";
        }
        return "Event creation failed";
    }

    @POST
    @Path("/modify/{eventID}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String modifyEvent(@PathParam("eventID") Integer eventID, String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        ClubEvent event = clubEvents.get(eventID);
        event.setName(jsonObject.getString("name"));
        event.setDescription(jsonObject.getString("description"));
        event.setDate(jsonObject.getString("date"));
        event.setTime(jsonObject.getString("time"));
        event.setAdmissionPrice(jsonObject.getJsonNumber("price").doubleValue());
        return event.toJsonString();
    }

    @POST
    @Path("/get/{eventID}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getEvent(@PathParam("eventID") Integer eventID) {
        return clubEvents.get(eventID).toJsonString();
    }


    @POST
    @Path("/get/all")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllEvents() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (ClubEvent event : clubEvents.values()) {
            jsonArrayBuilder.add(event.toJsonString());
        }
        jsonObjectBuilder.add("events", jsonArrayBuilder.build());
        return jsonObjectBuilder.build().toString();
    }
}
