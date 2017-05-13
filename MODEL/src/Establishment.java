import javax.json.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 5/3/2017.
 */
public class Establishment extends Profile {

    private String imageSrc;
    private String timeOpen;
    private String timeClose;
    private Set<Integer> events;

    public Establishment() {
        imageSrc = "";
        timeOpen = "";
        timeClose = "";
        events = new HashSet<>();
    }

    public Establishment(JsonObject jsonObject) {
        super(jsonObject);
        imageSrc = jsonObject.getString("imageSrc");
        timeOpen = jsonObject.getString("timeOpen");
        timeClose = jsonObject.getString("timeClose");
        for (JsonString tagJson : jsonObject.getJsonArray("events").getValuesAs(JsonString.class)) {
            events.add(Integer.parseInt(tagJson.getString()));
        }
    }

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

    public JsonObject toJson() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("name", getName());
        jsonObjectBuilder.add("address", getAddress());
        jsonObjectBuilder.add("phone", getPhone());
        jsonObjectBuilder.add("zip", getZip());
        if (getTickets().size() > 0) {
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (Ticket t : getTickets()) {
                jsonArrayBuilder.add(t.toJson());
            }
            jsonObjectBuilder.add("tickets", jsonArrayBuilder.build());
        } else {
            jsonObjectBuilder.add("tickets", Json.createArrayBuilder().build());
        }
        jsonObjectBuilder.add("imageSrc", getImageSrc());
        jsonObjectBuilder.add("timeOpen", getTimeOpen());
        jsonObjectBuilder.add("timeClose", getTimeClose());
        if (events.size() > 0) {
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (Integer i : getEvents()) {
                jsonArrayBuilder.add(i);
            }
            jsonObjectBuilder.add("event", jsonArrayBuilder.build());
        } else {
            jsonObjectBuilder.add("events", Json.createArrayBuilder().build());
        }
        return jsonObjectBuilder.build();
    }

}
