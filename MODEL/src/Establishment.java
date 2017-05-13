import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 5/3/2017.
 */
public class Establishment extends Profile {

    private Set<Integer> events = new HashSet<>();
    private String imageSrc = "";
    private String timeOpen = "";
    private String timeClose = "";

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public void addEvent(int eventID) {
        events.add(eventID);
    }

    public void removeEvent(int eventID) {
        events.remove(eventID);
    }

    public Set<Integer> getEvents() {
        return events;
    }

    public String getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(String timeOpen) {
        this.timeOpen = timeOpen;
    }

    public String getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(String timeClose) {
        this.timeClose = timeClose;
    }

}
