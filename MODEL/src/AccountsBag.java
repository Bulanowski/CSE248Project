import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.json.*;
import javax.naming.directory.InvalidAttributeIdentifierException;
import java.io.StringReader;
import java.util.LinkedList;

/**
 * Created by Alex on 5/3/2017.
 */
@Singleton
@Startup
public class AccountsBag {

    @EJB
    DataStorageHandler dataStorageHandler = new DataStorageHandler();

    private final ObservableMap<String, Account> accounts = FXCollections.observableHashMap();  // username, account

    @PostConstruct
    public void init() {
        System.out.println("Loading accounts from accounts.dat");
        String s = dataStorageHandler.readFromFile("accounts");
        if (s != null) {
            JsonArray jsonArray = Json.createReader(new StringReader(s)).readArray();
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
            try {
                JsonObject accountFooJson = Json.createObjectBuilder().add("username", "foo").add("password", "bar").add("email", "foo@bar.com").add("accountType", "Customer").build();
                Account accountFoo = new Account(accountFooJson);
                addAccount(accountFoo);
            } catch (InvalidAttributeIdentifierException e) {
                System.out.println("Failed to create default account foo");
            }
            try {
                JsonObject accountBarJson = Json.createObjectBuilder().add("username", "bar").add("password", "foo").add("email", "bar@foo.com").add("accountType", "Establishment").build();
                Account accountBar = new Account(accountBarJson);
                addAccount(accountBar);
            } catch (InvalidAttributeIdentifierException e) {
                System.out.println("Failed to create default account bar");
            }
        }
//        dataStorageHandler.connectAccountsListener(accounts);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Saving accounts to accounts.dat");
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Account a : accounts.values()) {
            jsonArrayBuilder.add(a.toJson());
        }
        dataStorageHandler.saveToFile("accounts", jsonArrayBuilder.build());
    }

    public void addAccount(Account a) {
        if (!usernameInUse(a.getUsername())) {
            accounts.put(a.getUsername(), a);
        }
    }

    public void addListener(InvalidationListener listener) {
        accounts.addListener(listener);
    }

    public boolean usernameInUse(String username) {
        return accounts.containsKey(username);
    }

    public boolean verifyLogin(String username, String password) {
        return (usernameInUse(username) ? accounts.get(username).verifyPassword(password) : false);
    }

    public Account getUser(String username) {
        return accounts.get(username);
    }

}
