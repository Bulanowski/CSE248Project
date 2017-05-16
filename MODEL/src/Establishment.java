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
    private Set<Integer> events = new HashSet<>();
    private String[] hours = new String[7];
    // TODO private Set<Item> availableItems new Set<>();

    public Establishment() {
        imageSrc = "";
        timeOpen = "";
        timeClose = "";
    }

    public Establishment(JsonObject jsonObject) {
        super(jsonObject);
        imageSrc = jsonObject.getString("imageSrc", "");
        timeOpen = jsonObject.getString("timeOpen", "");
        timeClose = jsonObject.getString("timeClose", "");
        if (jsonObject.containsKey("events") && !jsonObject.isNull("events")) {
            for (JsonNumber jsonNumber : jsonObject.getJsonArray("events").getValuesAs(JsonNumber.class)) {
                events.add(jsonNumber.intValueExact());
            }
        } else {
            System.err.println("Events not found in account " + jsonObject.getString("username"));
        }
        if (jsonObject.containsKey("hours") && !jsonObject.isNull("hours")) {
            int i = 0;
            for (JsonString jsonString : jsonObject.getJsonArray("hours").getValuesAs(JsonString.class)) {
                hours[i++] = jsonString.getString();
            }
        } else {
            System.err.println("Hours not found in account " + jsonObject.getString("username"));
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
        JsonObjectBuilder jsonObjectBuilder = startBuildingJson();
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
        if (hours != null) {
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (String s : hours) {
                jsonArrayBuilder.add(s);
            }
            jsonObjectBuilder.add("hours", jsonArrayBuilder.build());
        } else {
            jsonObjectBuilder.add("hours", Json.createArrayBuilder().build());
        }
        return jsonObjectBuilder.build();
    }

}
