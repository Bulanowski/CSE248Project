import java.io.StringReader;
import java.util.ArrayList;
import javax.ejb.EJB;
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

    @EJB
    AccountsBag accountsBag = new AccountsBag();

    @EJB
    TokenManager tokenManager = new TokenManager();

    @EJB
    ClubEventsBag clubEventsBag = new ClubEventsBag();

    @POST
    @Path("/register")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String registerEvent(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        // TODO validity checking
        String token = jsonObject.getString("token");
        String username = tokenManager.getUsername(token);
        if (username == null) {
            return "Invalid token";
        }
        Account account = accountsBag.getUser(username); // If this is null the user account doesn't exist
        if (account.getProfile() instanceof Establishment) {
            Establishment establishment = (Establishment) account.getProfile();
            ClubEvent clubEvent = new ClubEvent(establishment, jsonObject);
            clubEventsBag.addEvent(clubEvent);
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
        ClubEvent event = clubEventsBag.getEvent(eventID);
        event.setName(jsonObject.getString("name"));
        event.setDescription(jsonObject.getString("description"));
        event.setImageSrc(jsonObject.getString("imageSrc"));
        event.setDate(jsonObject.getString("date"));
        event.setTime(jsonObject.getString("time"));
        event.setAdmissionPrice(jsonObject.getJsonNumber("price").doubleValue());
        return event.toJson().toString();
    }

    @POST
    @Path("/search")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String searchEvents(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        String query = jsonObject.getString("query");
        Integer page = jsonObject.getInt("page");
        ArrayList<ClubEvent> searchResult = clubEventsBag.search(query);
        if (searchResult.size() == 0) {
            return "No results found";
        }
        if (searchResult.size() < 10 * (page - 1)) {
            page = 1;
        }
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (int i = 10 * (page - 1); i < page * 10; i++) {
            if (i >= searchResult.size()) {
                break;
            }
            jsonArrayBuilder.add(searchResult.get(i).toJson());
        }
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("pages", Math.ceil((double) searchResult.size() / 10));
        jsonObjectBuilder.add("result", jsonArrayBuilder.build());
        return jsonObjectBuilder.build().toString();
    }

    @POST
    @Path("/get/{eventID}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getEvent(@PathParam("eventID") Integer eventID) {
        return clubEventsBag.getEvent(eventID).toJson().toString();
    }


    @POST
    @Path("/get/all")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllEvents() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (ClubEvent event : clubEventsBag.getAllEvents()) {
            jsonArrayBuilder.add(event.toJson().toString());
        }
        jsonObjectBuilder.add("events", jsonArrayBuilder.build());
        return jsonObjectBuilder.build().toString();
    }
}
