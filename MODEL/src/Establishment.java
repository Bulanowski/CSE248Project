import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 5/3/2017.
 */
public class Establishment extends Account {

    private List<Integer> events = new ArrayList<>();

    public Establishment(String username, String password, String email, String address, String zip, String phone) {
        super(username, password, email, address, zip, phone);
    }

    public void addEvent(int eventID) {
        events.add(eventID);
    }
}
