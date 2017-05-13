import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 5/6/2017.
 */
public class ClubEvent implements Serializable {

    private static int eventIDCounter = 1000000;
    private int eventID;
    // change to just hold a String of the establishment username
    private String establishment;
    private Set<TagType> preferences;
    private String name;
    private String description;
    private String imageSrc;
    private String date;
    private String time;
    private double admissionPrice;
    private int maxTickets;
    private int purchasedTickets;

    public ClubEvent(String establishment, JsonObject jsonObject) {
        eventID = eventIDCounter++;
        this.establishment = establishment;
        preferences = new HashSet<>();
        name = jsonObject.getString("name");
        description = jsonObject.getString("description");
        imageSrc = jsonObject.getString("imageSrc");
        date = jsonObject.getString("date");
        time = jsonObject.getString("time");
        admissionPrice = jsonObject.getJsonNumber("price").doubleValue();
        maxTickets = jsonObject.getInt("tickets");
        purchasedTickets = 0;
    }

    public int getEventID() {
        return eventID;
    }

    public String getEstablishment() {
        return establishment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getAdmissionPrice() {
        return admissionPrice;
    }

    public void setAdmissionPrice(double admissionPrice) {
        this.admissionPrice = admissionPrice;
    }

    public int getMaxTickets() {
        return maxTickets;
    }

    public int getPurchasedTickets() {
        return purchasedTickets;
    }

    public boolean increasePurchasedTickets(int increaseAmount) {
        if (purchasedTickets + increaseAmount > maxTickets) {
            return false;
        }
        this.purchasedTickets += increaseAmount;
        return true;
    }

    public boolean decreasePurchasedTickets(int decreaseAmount) {
        if (purchasedTickets - decreaseAmount < 0) {
            return false;
        }
        this.purchasedTickets -= decreaseAmount;
        return true;
    }

    public JsonObject toJson() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        for (Field f : ClubEvent.class.getDeclaredFields()) {
            try {
//                if (f.getName().equals("establishment")) {
//                    jsonObjectBuilder.add(f.getName(), establishment.toJson());
//                } else {
                jsonObjectBuilder.add(f.getName(), f.get(this).toString());
//                }
            } catch (NullPointerException e) {
                jsonObjectBuilder.add(f.getName(), "");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return jsonObjectBuilder.build();
    }
}
