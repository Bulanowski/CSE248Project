import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Created by Alex on 5/13/2017.
 */
public class Ticket {

    private static int ticketIDCounter = 1000000;
    private final int ticketID;
    private final Integer eventID;
    private final String customer;
    private final int amount;

    public Ticket(Integer eventID, String customer, int amount) {
        ticketID = ticketIDCounter++;
        this.eventID = eventID;
        this.customer = customer;
        this.amount = amount;
    }

    public Ticket(JsonObject jsonObject) {
        ticketID = jsonObject.getInt("ticketID");
        eventID = jsonObject.getInt("eventID");
        customer = jsonObject.getString("customer");
        amount = jsonObject.getInt("amount");
    }

    public static void setTicketIDCounter(int ticketIDCounter) {
        Ticket.ticketIDCounter = ticketIDCounter;
    }

    public static int getTicketIDCounter() {
        return Ticket.ticketIDCounter;
    }

    public int getTicketID() {
        return ticketID;
    }

    public Integer getEventID() {
        return eventID;
    }

    public String getCustomer() {
        return customer;
    }

    public int getAmount() {
        return amount;
    }

    public JsonObject toJson() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("ticketID", ticketID);
        jsonObjectBuilder.add("eventID", eventID);
        jsonObjectBuilder.add("customer", customer);
        jsonObjectBuilder.add("amount", amount);
        return jsonObjectBuilder.build();
    }

    public boolean equals(Object o) {
        if (o instanceof Ticket) {
            Ticket ticket = (Ticket) o;
            return ticket.getTicketID() == this.getTicketID();
        }
        return false;
    }

}
