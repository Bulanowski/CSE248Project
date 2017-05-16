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
        birthday = jsonObject.getString("birthday", "");
        gender = jsonObject.getString("gender", "");
        if (jsonObject.containsKey("preferences") && !jsonObject.isNull("preferences")) {
            for (JsonString tagJson : jsonObject.getJsonArray("preferences").getValuesAs(JsonString.class)) {
                preferences.add(TagType.valueOf(tagJson.getString()));
            }
        }
        if (jsonObject.containsKey("tickets") && !jsonObject.isNull("tickets")) {
            for (JsonObject ticketJson : jsonObject.getJsonArray("tickets").getValuesAs(JsonObject.class)) {
                Ticket t = new Ticket(ticketJson);
                tickets.put(t.getTicketID(), t);
            }
        }
    }

    public void update(JsonObject jsonObject) {
        super.update(jsonObject);
        birthday = jsonObject.getString("birthday", birthday);
        gender = jsonObject.getString("gender", gender);
        JsonObject preferences = jsonObject.getJsonObject("preferences");
        for (JsonString addPreference : preferences.getJsonArray("checked").getValuesAs(JsonString.class)) {
            addPreference(TagType.valueOf(addPreference.getString()));
        }
        for (JsonString removePreference : preferences.getJsonArray("unchecked").getValuesAs(JsonString.class)) {
            removePreference(TagType.valueOf(removePreference.getString()));
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

    public int getMatchingPreferences(ClubEvent e) {
        int matchingCount = 0;
        for (TagType t : e.getTags()) {
            if (preferences.contains(t)) {
                matchingCount++;
            }
        }
        return matchingCount;
    }

    public void addTicket(Ticket t) {
        tickets.put(t.getTicketID(), t);
    }

    public void removeTicket(Integer ticketID) {
        tickets.remove(ticketID);
    }

    public Ticket getTicket(Integer ticketID) {
        return tickets.get(ticketID);
    }

    public Collection<Ticket> getTickets() {
        return tickets.values();
    }

    public JsonObject toJson() {
        JsonObjectBuilder jsonObjectBuilder = startBuildingJson();
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
