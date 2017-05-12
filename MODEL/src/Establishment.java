import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 5/3/2017.
 */
public class Establishment extends Profile {

    private List<Integer> events = new ArrayList<>();
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
