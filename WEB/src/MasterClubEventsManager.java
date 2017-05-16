import java.io.StringReader;
import java.util.ArrayList;
import java.util.Set;
import javax.ejb.EJB;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Alex on 5/6/2017.
 */
@Path("/event")
public class MasterClubEventsManager {

    @EJB
    private AccountsBag accountsBag = new AccountsBag();

    @EJB
    private TokenManager tokenManager = new TokenManager();

    @EJB
    private ClubEventsBag clubEventsBag = new ClubEventsBag();

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
    @Path("/modify")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String modifyEvent(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        ClubEvent event = clubEventsBag.getEvent(jsonObject.getInt("eventID"));
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
        String query = jsonObject.getString("query").toLowerCase();
        Integer page = Integer.parseInt(jsonObject.getString("page"));
        ArrayList<ClubEvent> searchResult = new ArrayList<>();
        ArrayList<ClubEvent> zipResult = new ArrayList<>();
        ArrayList<ClubEvent> nameResult = new ArrayList<>();
        ArrayList<ClubEvent> descriptionResult = new ArrayList<>();
        for (ClubEvent e : clubEventsBag.getAllEvents()) {
            if (accountsBag.getUser(e.getEstablishment()).getProfile().getZip().toLowerCase().contains(query)) {
                zipResult.add(e);
            } else if (e.getName().toLowerCase().contains(query)) {
                nameResult.add(e);
            } else if (e.getDescription().toLowerCase().contains(query)) {
                descriptionResult.add(e);
            }
        }
        searchResult.addAll(zipResult);
        searchResult.addAll(nameResult);
        searchResult.addAll(descriptionResult);
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
    @Path("/search/recommended")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String searchRecommendedEvents(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader((jsonString))).readObject();
        String token = jsonObject.getString("token");
        String query = jsonObject.getString("query").toLowerCase();
        Integer page = Integer.parseInt(jsonObject.getString("page"));
        String username = tokenManager.getUsername(token);
        if (username == null) {
            return "Invalid token";
        }
        Account account = accountsBag.getUser(username);
        if (account.getProfile() instanceof Customer) {
            Customer customer = (Customer) account.getProfile();
            Set<TagType> preferences = customer.getPreferences();
            Integer size = preferences.size();
            ArrayList<ArrayList<ClubEvent>> recommendedResult = new ArrayList<>();
            for (int i = 0; i < size + 1; i++) {
                recommendedResult.add(new ArrayList<ClubEvent>());
            }
            if (recommendedResult.size() == 0) {
                return "Preferences not set";
            }
            for (ClubEvent e : clubEventsBag.getAllEvents()) {
//                if (accountsBag.getUser(e.getEstablishment()).getProfile().getZip().toLowerCase().contains(query) || e.getName().toLowerCase().contains(query) || e.getDescription().toLowerCase().contains(query)) {
                if (e.getName().toLowerCase().contains(query) || e.getDescription().toLowerCase().contains(query)) {
                    recommendedResult.get(customer.getMatchingPreferences(e)).add(e);
                }
            }
            ArrayList<ClubEvent> searchResult = new ArrayList<>();
            for (int i = size; i >= 0; i--) {
                searchResult.addAll(recommendedResult.get(i));
            }
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
        return "Establishments cannot use recommended search";
    }

    @POST
    @Path("/get")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getEvent(Integer eventID) {
        ClubEvent c = clubEventsBag.getEvent(eventID);
        return c.toJson().toString();
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
    @Path("/ticket/buy")
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
                Ticket ticket = new Ticket(eventID, username, amount);
                customer.addTicket(ticket);
                Double price = clubEventsBag.getEvent(eventID).getPrice();
                Transaction transaction = new Transaction(customer.getName(), establishment.getName(), amount.doubleValue() * price, eventID);
                customer.addTransaction(transaction);
                establishment.addTransaction(transaction);
                return "Tickets purchased successfully";
            }
        }
        return "Tickets purchase failed";
    }

    @POST
    @Path("/ticket/cancel")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String cancelTicket(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        String token = jsonObject.getString("token");
        String username = tokenManager.getUsername(token);
        if (username == null) {
            return "Invalid token";
        }
        Account account = accountsBag.getUser(username);
        if (account.getProfile() instanceof Customer) {
            Customer customer = (Customer) account.getProfile();
            Integer ticketID = jsonObject.getInt("ticketID");
            Ticket t = customer.getTicket(ticketID);
            if (t != null && clubEventsBag.getEvent(t.getEventID()).decreasePurchasedTickets(t.getAmount())) {
                Establishment establishment = (Establishment) accountsBag.getUser(clubEventsBag.getEvent(t.getEventID()).getEstablishment()).getProfile();
                customer.removeTicket(t.getTicketID());
                Double price = clubEventsBag.getEvent(t.getEventID()).getPrice();
                Transaction transaction = new Transaction(establishment.getName(), customer.getName(), (double) t.getAmount() * price, t.getEventID());
                customer.addTransaction(transaction);
                establishment.addTransaction(transaction);
                return "Ticket canceled successfully";
            } else {
                return "Invalid ticket ID";
            }
        }
        return "Ticket cancel failed";
    }

    @POST
    @Path("/ticket/get/all")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getTickets(String token) {
        String username = tokenManager.getUsername(token);
        if (username == null) {
            return "Invalid token";
        }
        Account account = accountsBag.getUser(username);
        Profile profile = account.getProfile();
        if (profile instanceof Customer) {
            Customer customer = (Customer) profile;
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (Ticket t : customer.getTickets()) {
                JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
                jsonObjectBuilder.add("ticketID", t.getTicketID());
                jsonObjectBuilder.add("eventID", t.getEventID());
                jsonObjectBuilder.add("customer", t.getCustomer());
                jsonObjectBuilder.add("amount", t.getAmount());
                jsonObjectBuilder.add("event", clubEventsBag.getEvent(t.getEventID()).toJson());
                jsonArrayBuilder.add(jsonObjectBuilder.build());
            }
            JsonArray jsonArray = jsonArrayBuilder.build();
            if (jsonArray.size() == 0) {
                return "No tickets found";
            }
            return jsonArray.toString();
        }
        return "Failed to retrieve tickets";
    }

    @POST
    @Path("/transaction/get")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getTransactions(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        String token = jsonObject.getString("token");
        String username = tokenManager.getUsername(token);
        if (username == null) {
            return "Invalid token";
        }
        Account account = accountsBag.getUser(username);
        Profile profile = account.getProfile();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Transaction t : profile.getTransactions()) {
            jsonArrayBuilder.add(t.toJson());
        }
        return jsonArrayBuilder.build().toString();
    }

    @POST
    @Path("/transaction/search")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String searchTransactions(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        String token = jsonObject.getString("token");
        String username = tokenManager.getUsername(token);
        if (username == null) {
            return "Invalid token";
        }
        String query = jsonObject.getString("query");
        Profile profile = accountsBag.getUser(username).getProfile();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Transaction t : profile.getTransactions()) {
            if (t.getReceiver().toLowerCase().contains(query.toLowerCase()) || t.getSender().toLowerCase().contains(query.toLowerCase())) {
                jsonArrayBuilder.add(t.toJson());
            }
        }
        return jsonArrayBuilder.build().toString();
    }
}
