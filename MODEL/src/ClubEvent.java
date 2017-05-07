import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by Alex on 5/6/2017.
 */
public class ClubEvent implements Serializable {

    private static int eventIDCounter = 1000000;
    private int eventID;
    private Establishment establishment;
    private String name;
    private String description;
    private String date;
    private boolean dateLock;
    private String time;
    private boolean timeLock;
    private double admissionPrice;
    private boolean priceLock;
    private int maxTickets;
    private int purchasedTickets;

//    public ClubEvent(Establishment establishment, String name, String description, String date, String time, double admissionPrice, int maxTickets) {
//        eventID = eventIDCounter++;
//        this.establishment = establishment;
//        this.name = name;
//        this.description = description;
//        this.date = date;
//        this.time = time;
//        this.admissionPrice = admissionPrice;
//        this.maxTickets = maxTickets;
//        purchasedTickets = 0;
//    }

    public ClubEvent(Establishment establishment, JsonObject jsonObject) {
        eventID = eventIDCounter++;
        this.establishment = establishment;
        name = jsonObject.getString("name");
        description = jsonObject.getString("description");
        date = jsonObject.getString("date");
        dateLock = false;
        time = jsonObject.getString("time");
        timeLock = false;
        admissionPrice = jsonObject.getJsonNumber("price").doubleValue();
        priceLock = false;
        maxTickets = jsonObject.getInt("tickets");
        purchasedTickets = 0;
    }

    public int getEventID() {
        return eventID;
    }

    public Establishment getEstablishment() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (!dateLock) {
            this.date = date;
        }
    }

    public boolean dateLocked() {
        return dateLock;
    }

    public void lockDate() {
        dateLock = true;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        if (!timeLock) {
            this.time = time;
        }
    }

    public boolean timeLocked() {
        return timeLock;
    }

    public void lockTime() {
        timeLock = true;
    }

    public double getAdmissionPrice() {
        return admissionPrice;
    }

    public void setAdmissionPrice(double admissionPrice) {
        if (!priceLock) {
            this.admissionPrice = admissionPrice;
        }
    }

    public boolean priceLocked() {
        return priceLock;
    }

    public void lockPrice() {
        priceLock = true;
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

    public String toJsonString() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        for (Field f : ClubEvent.class.getDeclaredFields()) {
            try {
                jsonObjectBuilder.add(f.getName(), f.get(this).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return jsonObjectBuilder.build().toString();
    }
}
