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
            ClubEvent clubEvent = new ClubEvent(username, jsonObject);
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
        event.setPrice(jsonObject.getJsonNumber("price").doubleValue());
        return event.toJson().toString();
    }

    @POST
    @Path("/search")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String searchEvents(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        String query = jsonObject.getString("query");
        Integer page = Integer.parseInt(jsonObject.getString("page"));
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
    public String getEvent(Integer eventID) {
        ClubEvent clubEvent = clubEventsBag.getEvent(eventID);
        return clubEvent.toJson().toString();
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

    @POST
    @Path("/buyTicket")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String purchaseTicket(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        String token = jsonObject.getString("token");
        String username = tokenManager.getUsername(token);
        if (username == null) {
            return "Invalid token";
        }
        Account account = accountsBag.getUser(username);
        if (account.getProfile() instanceof Customer) {
            Customer customer = (Customer) account.getProfile();
            Integer eventID = jsonObject.getInt("eventID");
            Integer amount = jsonObject.getInt("amount");
            if (clubEventsBag.getEvent(eventID).increasePurchasedTickets(amount)) {
                Establishment establishment = (Establishment) accountsBag.getUser(clubEventsBag.getEvent(eventID).getEstablishment()).getProfile();
                Ticket t = new Ticket(eventID, username, amount);
                customer.addTicket(t);
                establishment.addTicket(t);
                return "Tickets purchased successfully";
            }
        }
        return "Tickets purchase failed";
    }
}
