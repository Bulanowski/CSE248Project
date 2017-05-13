import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.util.*;

/**
 * Created by Alex on 5/10/2017.
 */
@Singleton
public class ClubEventsBag {

    @EJB
    DataStorageHandler dataStorageHandler = new DataStorageHandler();

    private final ObservableMap<Integer, ClubEvent> clubEvents = FXCollections.observableHashMap(); // eventID, event

    public ClubEventsBag() {
        Object o = dataStorageHandler.readFromFile("events");
        if (o != null) {
            for (ClubEvent e : (LinkedList<ClubEvent>) o) {
                clubEvents.put(e.getEventID(), e);
            }
        } else {
            for (int i=0;i<35;i++) {
                System.out.println("added event "+i);
                JsonObjectBuilder json = Json.createObjectBuilder();
                json.add("name","Event Name "+i);
                json.add("description",i+" Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ac tellus tortor. Aliquam sit amet posuere libero, at semper magna. In nullam.");
                json.add("imageSrc","https://i.imgur.com/1wc10tt.jpg");
                json.add("date","1");
                json.add("time","1");
                json.add("price",1.0);
                json.add("tickets",1);
                ClubEvent event = new ClubEvent(null,json.build());
                clubEvents.put(event.getEventID(), event);
            }
        }
        dataStorageHandler.connectClubEventsListener(clubEvents);
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
            if (e.getName().contains(query)) {
                searchResult.add(e);
            }
        }
        return searchResult;
    }
}
