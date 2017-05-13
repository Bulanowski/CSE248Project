import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.json.*;
import java.io.StringReader;
import java.util.*;

/**
 * Created by Alex on 5/10/2017.
 */
@Singleton
@Startup
public class ClubEventsBag {

    @EJB
    DataStorageHandler dataStorageHandler = new DataStorageHandler();

    private final ObservableMap<Integer, ClubEvent> clubEvents = FXCollections.observableHashMap(); // eventID, event

    @PostConstruct
    public void init() {
        System.out.println("Loading events from events.dat");
        String s = dataStorageHandler.readFromFile("events");
        if (s != null) {
            try {
                JsonObject jsonObject = Json.createReader(new StringReader(s)).readObject();
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
//        dataStorageHandler.connectClubEventsListener(clubEvents);
    }

    private void loadDefaultEvents() {
        System.out.println("Unable to load events from events.dat, loading default events.");
        ClubEvent.setEventIDCounter(1000000);
        Ticket.setTicketIDCounter(1000000);
        for (int i=0;i<35;i++) {
            JsonObjectBuilder json = Json.createObjectBuilder();
            json.add("name","Event Name "+i);
            json.add("description",i+" Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac tellus tortor. Aliquam sit amet posuere libero, at semper magna. In nullam.");
            json.add("imageSrc","https://i.imgur.com/1wc10tt.jpg");
            json.add("date","1");
            json.add("time","1");
            json.add("price",1.0);
            json.add("maxTickets",1);
            ClubEvent event = new ClubEvent("", json.build());
            event.addTag(((i % 3) == 0 ? TagType.Rock : ((i % 3) == 1 ? TagType.Pop : TagType.Jazz)));
            addEvent(event);
        }
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Saving events to events.dat");
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("eventIDCounter", ClubEvent.getEventIDCounter());
        jsonObjectBuilder.add("ticketIDCounter", Ticket.getTicketIDCounter());
//        jsonObjectBuilder.add("ticketIDCounter", 1000000);
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (ClubEvent clubEvent : clubEvents.values()) {
            jsonArrayBuilder.add(clubEvent.toJson());
        }
        jsonObjectBuilder.add("events", jsonArrayBuilder.build());
        dataStorageHandler.saveToFile("events", jsonObjectBuilder.build());
    }

    public void addEvent(ClubEvent e) {
        clubEvents.put(e.getEventID(), e);
    }

    public ClubEvent getEvent(Integer id) {
        return clubEvents.get(id);
    }

    public Collection<ClubEvent> getAllEvents() {
        return clubEvents.values();
    }

    public ArrayList<ClubEvent> search(String query) {
        ArrayList<ClubEvent> searchResult = new ArrayList<>();
        for (ClubEvent e : clubEvents.values()) {
            if (e.getName().toLowerCase().contains(query.toLowerCase())) {
                searchResult.add(e);
            }
        }
        return searchResult;
    }
}
