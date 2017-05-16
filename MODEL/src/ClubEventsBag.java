import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.json.*;
import java.util.*;

/**
 * Created by Alex on 5/10/2017.
 */
@Singleton
@Startup
@DependsOn("AccountsBag")
public class ClubEventsBag {

    @EJB
    private DataStorageHandler dataStorageHandler = new DataStorageHandler();

    @EJB
    private AccountsBag accountsBag = new AccountsBag();

    private final ObservableMap<Integer, ClubEvent> clubEvents = FXCollections.observableHashMap(); // eventID, event

    @PostConstruct
    public void init() {
        System.out.println("Loading events from events.dat");
        JsonObject jsonObject = dataStorageHandler.readFromFile("events");
        if (jsonObject != null) {
            try {
                ClubEvent.setEventIDCounter(jsonObject.getInt("eventIDCounter"));
                Ticket.setTicketIDCounter(jsonObject.getInt("ticketIDCounter"));
                JsonArray jsonArray = jsonObject.getJsonArray("events");
                for (JsonObject eventJson : jsonArray.getValuesAs(JsonObject.class)) {
                    ClubEvent clubEvent = new ClubEvent(eventJson);
                    addEvent(clubEvent);
                }
                System.out.println("Successfully loaded events from events.dat");
            } catch (Exception e) {
                e.printStackTrace();
                loadDefaultEvents();
            }
        } else {
            loadDefaultEvents();
        }
    }

    private void loadDefaultEvents() {
        System.out.println("Unable to load events from events.dat, loading default events.");
        ClubEvent.setEventIDCounter(1000000);
        Ticket.setTicketIDCounter(1000000);
        for (int i=0;i<35;i++) {
            Random r = new Random();
            String establishmentName = "bar";
            JsonObjectBuilder json = Json.createObjectBuilder();
            json.add("name","Event Name "+i);
            json.add("description",i+" Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac tellus tortor. Aliquam sit amet posuere libero, at semper magna. In nullam.");
            json.add("imageSrc","https://i.imgur.com/1wc10tt.jpg");
            json.add("date","22/9/1963");
            json.add("time","12:30 p.m");
            json.add("price",(r.nextFloat()*50));
            json.add("maxTickets",r.nextInt(50)+10);
            ClubEvent event = new ClubEvent(establishmentName, json.build());
            event.addTag(((i % 3) == 0 ? TagType.Rock : ((i % 3) == 1 ? TagType.Pop : TagType.Jazz)));
            addEvent(event);
            if (accountsBag.usernameInUse(establishmentName) && accountsBag.getUser(establishmentName).getProfile() instanceof Establishment) {
                Establishment establishment = (Establishment) accountsBag.getUser(establishmentName).getProfile();
                establishment.addEvent(event.getEventID());
            }
        }
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Saving events to events.dat");
        dataStorageHandler.saveToFile("events", toJson());
    }

    public void addEvent(ClubEvent clubEvent) {
        clubEvents.put(clubEvent.getEventID(), clubEvent);
    }

    public ClubEvent getEvent(Integer eventID) {
        return clubEvents.get(eventID);
    }

    public Collection<ClubEvent> getAllEvents() {
        return clubEvents.values();
    }

    public JsonObject toJson() {

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("eventIDCounter", ClubEvent.getEventIDCounter());
        jsonObjectBuilder.add("ticketIDCounter", Ticket.getTicketIDCounter());
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (ClubEvent clubEvent : clubEvents.values()) {
            jsonArrayBuilder.add(clubEvent.toJson());
        }
        jsonObjectBuilder.add("events", jsonArrayBuilder.build());
        return jsonObjectBuilder.build();
    }

//    public ArrayList<ClubEvent> search(String query) {
//        ArrayList<ClubEvent> searchResult = new ArrayList<>();
//        for (ClubEvent e : clubEvents.values()) {
//            if (e.getName().toLowerCase().contains(query.toLowerCase())) {
//                searchResult.add(e);
//            }
//        }
//        return searchResult;
//    }
}
