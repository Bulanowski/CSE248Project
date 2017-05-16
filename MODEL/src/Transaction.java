import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Calendar;

/**
 * Created by Alex on 5/13/2017.
 */
public class Transaction {

    private static int transactionIDCounter = 1000000;
    private int transactionID;
    private String sender;
    private String receiver;
    private String date;
    private double payment;
    private int eventID;
    private String eventName;

    public Transaction(String sender, String receiver, double payment, int eventID, String eventName) {
        transactionID = transactionIDCounter++;
        this.sender = sender;
        this.receiver = receiver;
        this.date = Calendar.getInstance().getTime().toString();
        this.payment = payment;
        this.eventID = eventID;
        this.eventName = eventName;
    }

    public Transaction(JsonObject jsonObject) {
        transactionID = jsonObject.getInt("transactionID");
        sender = jsonObject.getString("sender");
        receiver = jsonObject.getString("receiver");
        date = jsonObject.getString("date");
        payment = jsonObject.getJsonNumber("payment").doubleValue();
        eventID = jsonObject.getInt("eventID");
        eventName = jsonObject.getString("eventName");
    }

    public static void setTransactionIDCounter(int transactionIDCounter) {
        Transaction.transactionIDCounter = transactionIDCounter;
    }

    public static int getTransactionIDCounter() {
        return Transaction.transactionIDCounter;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getDate() {
        return date;
    }

    public double getPayment() {
        return payment;
    }

    public Integer getEventID() {
        return eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public JsonObject toJson() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("transactionID", transactionID);
        jsonObjectBuilder.add("sender", sender);
        jsonObjectBuilder.add("receiver", receiver);
        jsonObjectBuilder.add("date", date);
        jsonObjectBuilder.add("payment", payment);
        jsonObjectBuilder.add("eventID", eventID);
        jsonObjectBuilder.add("eventName", eventName);
        return jsonObjectBuilder.build();
    }
}
