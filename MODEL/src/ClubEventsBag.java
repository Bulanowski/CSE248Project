import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import javax.ejb.EJB;
import javax.ejb.Singleton;
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
