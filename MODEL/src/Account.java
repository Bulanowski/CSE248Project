import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.naming.directory.InvalidAttributeIdentifierException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alex on 5/3/2017.
 */
public class Account implements Serializable {

    private String username;    // stored as plaintext
    private String password;    // stored as SHA256
    private String email;
    private Profile profile;

    public Account(JsonObject jsonObject) throws InvalidAttributeIdentifierException {
        username = jsonObject.getString("username");
        password = jsonObject.getString("password");
        email = jsonObject.getString("email");
        String accountType = jsonObject.getString("type");
        if (accountType.equals("Customer")) {
            profile = new Customer();
        } else if (accountType.equals("Establishment")) {
            profile = new Establishment();
        } else {
            throw new InvalidAttributeIdentifierException();
        }
    }

    public Account(String username, String password, String email, String accountType) throws InvalidAttributeIdentifierException {
        this.username = username;
        this.password = password;
        this.email = email;
        if (accountType.equals("Customer")) {
            profile = new Customer();
        } else if (accountType.equals("Establishment")) {
            profile = new Establishment();
        } else {
            throw new InvalidAttributeIdentifierException();
        }
    }

    public String getUsername() {
        return username;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

    public String getEmail() {
        return email;
    }

    public Profile getProfile() {
        return profile;
    }

    public JsonObject toJson() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("username", username);
        jsonObjectBuilder.add("password", password);
        jsonObjectBuilder.add("email", email);
        jsonObjectBuilder.add("type", profile.getClass().getName());
        List<Field> fields = new ArrayList<>();
        Collections.addAll(fields, profile.getClass().getSuperclass().getDeclaredFields());
        Collections.addAll(fields, profile.getClass().getDeclaredFields());
        for (Field f : fields) {
            try {
                f.setAccessible(true);
                jsonObjectBuilder.add(f.getName(), f.get(profile).toString());
            } catch (NullPointerException e) {
                jsonObjectBuilder.add(f.getName(), "");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (profile instanceof Customer) {
            jsonObjectBuilder.add("homepage", "homepage/");
        } else {
            jsonObjectBuilder.add("homepage", "homepage-establishment/");
        }
        return jsonObjectBuilder.build();
    }
}
