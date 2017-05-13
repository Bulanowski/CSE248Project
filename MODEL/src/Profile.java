import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Alex on 5/10/2017.
 */
public abstract class Profile {

    private String name;
    private String address;
    private String zip;
    private String phone;
    private HashMap<Integer, Ticket> tickets = new HashMap<>(); // ticketID, ticket

    Profile() {
        name = "";
        address = "";
        zip = "";
        phone = "";
    }

    Profile(JsonObject jsonObject) {
        name = jsonObject.getString("name");
        address = jsonObject.getString("address");
        zip = jsonObject.getString("zip");
        phone = jsonObject.getString("phone");
        for (JsonObject ticketJson : jsonObject.getJsonArray("tickets").getValuesAs(JsonObject.class)) {
            Ticket t = new Ticket(ticketJson);
            tickets.put(t.getTicketID(), t);
        }
    }

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

    public abstract JsonObject toJson();
}
