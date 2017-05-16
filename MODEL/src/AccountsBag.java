import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.json.*;
import javax.naming.directory.InvalidAttributeIdentifierException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alex on 5/3/2017.
 */
@Singleton
@Startup
public class AccountsBag {

    @EJB
    private DataStorageHandler dataStorageHandler = new DataStorageHandler();

    private final ObservableMap<String, Account> accounts = FXCollections.observableHashMap();  // username, account

    @PostConstruct
    public void init() {
        System.out.println("Loading accounts from accounts.dat");
        JsonObject jsonObject = dataStorageHandler.readFromFile("accounts");
        if (jsonObject != null) {
            Transaction.setTransactionIDCounter(jsonObject.getInt("transactionIDCounter"));
            JsonArray jsonArray = jsonObject.getJsonArray("accounts");
            for (JsonObject accountJson : jsonArray.getValuesAs(JsonObject.class)) {
                try {
                    Account account = new Account(accountJson, false);
                    addAccount(account);
                } catch (InvalidAttributeIdentifierException e) {
                    System.out.println("Failed to read account: " + accountJson);
                }
            }
            System.out.println("Successfully loaded accounts from accounts.dat");
        } else {
            System.out.println("Unable to load accounts from accounts.dat, loading default accounts.");
            Transaction.setTransactionIDCounter(1000000);
            try {
                JsonObject accountFooJson = Json.createObjectBuilder()
                        .add("username", "foo")
                        .add("password", "bar")
                        .add("email", "foo@bar.com")
                        .add("accountType", "Customer")
                        .add("profile", Json.createObjectBuilder().build())
                        .build();
                Account accountFoo = new Account(accountFooJson);
                addAccount(accountFoo);
            } catch (InvalidAttributeIdentifierException e) {
                System.out.println("Failed to create default account foo");
            }
            try {
                JsonObject accountBarJson = Json.createObjectBuilder()
                        .add("username", "bar")
                        .add("password", "foo")
                        .add("email", "bar@foo.com")
                        .add("accountType", "Establishment")
                        .add("profile", Json.createObjectBuilder().build())
                        .build();
                Account accountBar = new Account(accountBarJson);
                addAccount(accountBar);
            } catch (InvalidAttributeIdentifierException e) {
                System.out.println("Failed to create default account bar");
            }
        }
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Saving accounts to accounts.dat");
        dataStorageHandler.saveToFile("accounts", toJson());
        System.out.println("Saved accounts to accounts.dat");
    }

    public void addAccount(Account a) {
        if (!usernameInUse(a.getUsername())) {
            accounts.put(a.getUsername(), a);
        }
    }

    public boolean usernameInUse(String username) {
        return accounts.containsKey(username);
    }

    public boolean verifyLogin(String username, String password) {
        return usernameInUse(username) && accounts.get(username).verifyPassword(password);
    }

    public Account getUser(String username) {
        return accounts.get(username);
    }

    public List<Establishment> getEstablishments() {
        List<Establishment> establishments = new ArrayList<>();
        for (Account a : accounts.values()) {
            if (a.getProfile() instanceof Establishment) {
                establishments.add((Establishment) a.getProfile());
            }
        }
        return Collections.unmodifiableList(establishments);
    }

    public Establishment searchEstablishmentsByName(String name) {
        for (Account a : accounts.values()) {
            if (a.getProfile() instanceof Establishment && a.getProfile().getName().equals(name)) {
                return (Establishment) a.getProfile();
            }
        }
        return null;
    }

    public JsonObject toJson() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("transactionIDCounter", Transaction.getTransactionIDCounter());
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Account a : accounts.values()) {
            jsonArrayBuilder.add(a.toJson());
        }
        jsonObjectBuilder.add("accounts", jsonArrayBuilder.build());
        return jsonObjectBuilder.build();
    }

}
