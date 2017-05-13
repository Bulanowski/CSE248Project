import javax.json.*;
import java.io.Serializable;
import java.io.StringReader;
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
    private Set<TagType> tags;
    private String name;
    private String description;
    private String imageSrc;
    private String date;
    private String time;
    private double price;
    private int maxTickets;
    private int purchasedTickets;

    public ClubEvent(String establishment, JsonObject jsonObject) {
        eventID = eventIDCounter++;
        this.establishment = establishment;
        tags = new HashSet<>();
        name = jsonObject.getString("name");
        description = jsonObject.getString("description");
        imageSrc = jsonObject.getString("imageSrc");
        date = jsonObject.getString("date");
        time = jsonObject.getString("time");
        price = jsonObject.getJsonNumber("price").doubleValue();
        maxTickets = jsonObject.getInt("maxTickets");
        purchasedTickets = 0;
    }

    protected ClubEvent(JsonObject jsonObject) {
        eventID = Integer.parseInt(jsonObject.getString("eventID"));
        establishment = jsonObject.getString("establishment");
        tags = new HashSet<>();
        for (JsonString value : jsonObject.getJsonArray("tags").getValuesAs(JsonString.class)) {
            tags.add(TagType.valueOf(value.getString()));
        }
        name = jsonObject.getString("name");
        description = jsonObject.getString("description");
        imageSrc = jsonObject.getString("imageSrc");
        date = jsonObject.getString("date");
        time = jsonObject.getString("time");
        price = Double.parseDouble(jsonObject.getString("price"));
        maxTickets = Integer.parseInt(jsonObject.getString("maxTickets"));
        purchasedTickets = Integer.parseInt(jsonObject.getString("purchasedTickets"));
    }

    public static void setEventIDCounter(int eventIDCounter) {
        ClubEvent.eventIDCounter = eventIDCounter;
    }

    public static int getEventIDCounter() {
        return ClubEvent.eventIDCounter;
    }

    public int getEventID() {
        return eventID;
    }

    public String getEstablishment() {
        return establishment;
    }

    public void addTag(TagType e) {
        tags.add(e);
    }

    public void removeTag(TagType e) {
        tags.remove(e);
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
                if (f.getName().equals("tags")) {
                    JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
                    for (TagType tag : tags) {
                        jsonArrayBuilder.add(tag.toString());
                    }
                    jsonObjectBuilder.add("tags", jsonArrayBuilder.build());
                } else
                    if (!f.getName().equals("eventIDCounter")) {
                    jsonObjectBuilder.add(f.getName(), f.get(this).toString());
                }
            } catch (NullPointerException e) {
                jsonObjectBuilder.add(f.getName(), "");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return jsonObjectBuilder.build();
    }
}
