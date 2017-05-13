import javax.json.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 5/3/2017.
 */
public class Customer extends Profile {

    private String birthday;
    private String gender;
    private Set<TagType> preferences = new HashSet<>();

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
        return jsonObjectBuilder.build();
    }
}
