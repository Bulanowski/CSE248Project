import javax.json.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 5/3/2017.
 */
public class Customer extends Profile {

    private String birthday;
    private String gender;
    private Set<TagType> preferences = new HashSet<>();
    private HashMap<Integer, Ticket> tickets = new HashMap<>(); // ticketID, ticket

    public Customer() {
        birthday = "";
        gender = "";
    }

    public Customer(JsonObject jsonObject) {
        super(jsonObject);
        birthday = jsonObject.getString("birthday");
        gender = jsonObject.getString("gender");
        for (JsonString tagJson : jsonObject.getJsonArray("preferences").getValuesAs(JsonString.class)) {
            preferences.add(TagType.valueOf(tagJson.getString()));
        }
        for (JsonObject ticketJson : jsonObject.getJsonArray("tickets").getValuesAs(JsonObject.class)) {
            Ticket t = new Ticket(ticketJson);
            tickets.put(t.getTicketID(), t);
        }
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void addPreference(TagType e) {
        preferences.add(e);
    }

    public void removePreference(TagType e) {
        preferences.remove(e);
    }

    public Set<TagType> getPreferences() {
        return preferences;
    }

    public void addTicket(Ticket t) {
        tickets.put(t.getTicketID(), t);
    }

    public void removeTicket(Ticket t) {
        tickets.remove(t.getTicketID());
    }

    public Ticket getTicket(Integer ticketID) {
        return tickets.get(ticketID);
    }

    public Collection<Ticket> getTickets() {
        return tickets.values();
    }

    public JsonObject toJson() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("name", getName());
        jsonObjectBuilder.add("address", getAddress());
        jsonObjectBuilder.add("phone", getPhone());
        jsonObjectBuilder.add("zip", getZip());
        if (getTransactions().size() > 0) {
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (Transaction t : getTransactions()) {
                jsonArrayBuilder.add(t.toJson());
            }
            jsonObjectBuilder.add("transactions", jsonArrayBuilder.build());
        } else {
            jsonObjectBuilder.add("transactions", Json.createArrayBuilder().build());
        }
        jsonObjectBuilder.add("birthday", getBirthday());
        jsonObjectBuilder.add("gender", getGender());
        if (getPreferences().size() > 0) {
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (TagType t : getPreferences()) {
                jsonArrayBuilder.add(t.name());
            }
            jsonObjectBuilder.add("preferences", jsonArrayBuilder.build());
        } else {
            jsonObjectBuilder.add("preferences", Json.createArrayBuilder().build());
        }
        if (getTickets().size() > 0) {
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (Ticket t : getTickets()) {
                jsonArrayBuilder.add(t.toJson());
            }
            jsonObjectBuilder.add("tickets", jsonArrayBuilder.build());
        } else {
            jsonObjectBuilder.add("tickets", Json.createArrayBuilder().build());
        }
        return jsonObjectBuilder.build();
    }
}
