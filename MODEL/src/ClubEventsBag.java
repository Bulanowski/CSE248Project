import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;

/**
 * Created by Alex on 5/10/2017.
 */
public class ClubEventsBag extends Observable implements Serializable {

    private final HashMap<Integer, ClubEvent> clubEvents; // eventID, event

    public ClubEventsBag (DataStorageHandler dataStorageHandler) {
        Object o = dataStorageHandler.readFromFile("events");
        if (o != null && o instanceof HashMap) {
            clubEvents = (HashMap) o;
        } else {
            clubEvents = new HashMap<>();
        }
    }

    public void addEvent(ClubEvent e) {
        clubEvents.put(e.getEventID(), e);
        saveChanges();
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

    public void saveChanges() {
        setChanged();
        notifyObservers(clubEvents);
    }
}
