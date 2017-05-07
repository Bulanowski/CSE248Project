import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.io.StringReader;
import java.lang.reflect.Field;

/**
 * Created by Alex on 5/3/2017.
 */
public class Customer extends Account {

    String birthday;

    public Customer(String username, String password, String email, String address, String zip, String phone, String birthday) {
        super(username, password, email, address, zip, phone);
        this.birthday = birthday;
    }

    public String toJson() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        for (Field f : Account.class.getDeclaredFields()) {
            try {
                jsonObjectBuilder.add(f.getName(), f.get(this).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        for (Field f : Customer.class.getDeclaredFields()) {
            try {
                jsonObjectBuilder.add(f.getName(), f.get(this).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return jsonObjectBuilder.build().toString();
    }
}
