import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alex on 5/10/2017.
 */
public abstract class Profile implements Serializable {

    private String name;
    private String address;
    private String zip;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public JsonObject toJson() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        List<Field> fields = new ArrayList<>();
        Collections.addAll(fields, this.getClass().getSuperclass().getDeclaredFields());
        Collections.addAll(fields, this.getClass().getDeclaredFields());
        for (Field f : fields) {
            try {
                f.setAccessible(true);
                jsonObjectBuilder.add(f.getName(), f.get(this).toString());
                f.setAccessible(false);
            } catch (NullPointerException e) {
                jsonObjectBuilder.add(f.getName(), "");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return jsonObjectBuilder.build();
    }
}
