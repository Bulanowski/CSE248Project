import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;

/**
 * Created by Alex on 5/3/2017.
 */
public abstract class Account {

    String username;    // stored as plaintext
    String password;    // stored as SHA256
    String email;
    String address;
    String zip;
    String phone;

    public Account(String username, String password, String email, String address, String zip, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.zip = zip;
        this.phone = phone;
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

    public String getAddress() {
        return address;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone() {
        return phone;
    }

    public JsonObject toJson() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        Field[] fields = Account.class.getDeclaredFields();
        for (Field f : fields) {
            try {
                jsonObjectBuilder.add(f.getName(), f.get(this).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (this instanceof  Customer) {
            jsonObjectBuilder.add("homepage", "homepage/");
        } else {
            jsonObjectBuilder.add("homepage", "homepage-establishment/");
        }
        jsonObjectBuilder.add("type", this.getClass().getName());
        fields = this.getClass().getDeclaredFields();
        for (Field f : fields) {
            try {
                jsonObjectBuilder.add(f.getName(), f.get(this).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return jsonObjectBuilder.build();
    }

    public String toString() {
        return String.format("Username: %s, Password: %s", username, password);
    }
}
