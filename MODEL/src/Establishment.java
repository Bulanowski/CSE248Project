import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 5/3/2017.
 */
public class Establishment extends Profile {

    private List<Integer> events = new ArrayList<>();

    public void addEvent(int eventID) {
        events.add(eventID);
    }
}
