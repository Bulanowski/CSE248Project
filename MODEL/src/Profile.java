import javax.json.JsonObject;
import java.util.*;

/**
 * Created by Alex on 5/10/2017.
 */
public abstract class Profile {

    private String name;
    private String address;
    private String zip;
    private String phone;
    private HashMap<Integer, Transaction> transactions = new HashMap<>(); // transactionID, transaction

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
        for (JsonObject transactionJson : jsonObject.getJsonArray("transactions").getValuesAs(JsonObject.class)) {
            Transaction t = new Transaction(transactionJson);
            transactions.put(t.getTransactionID(), t);
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

    public void addTransaction(Transaction t) {
        transactions.put(t.getTransactionID(), t);
    }

    public Transaction getTransaction(Integer transactionID) {
        return transactions.get(transactionID);
    }

    public Collection<Transaction> getTransactions() {
        return transactions.values();
    }

    public abstract JsonObject toJson();
}
