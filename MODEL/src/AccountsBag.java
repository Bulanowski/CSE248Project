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
                        .add("profile", Json.createObjectBuilder()
                                .add("name", "foo")
                                .add("address", "1 Foo Lane")
                                .add("zip", "11111")
                                .add("phone", "(123)-456-7890")
                                .add("birthday", "June 15")
                                .add("gender", "Male")
                                .add("preferences", Json.createArrayBuilder()
                                        .add("Hip_Hop")
                                        .add("Rock_N_Roll")
                                        .add("Metal")
                                        .build())
                                .build())
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
                        .add("profile", Json.createObjectBuilder()
                                .add("name", "bar")
                                .add("address", "1 Bar Dr")
                                .add("zip", "22222")
                                .add("phone", "(098)-765-4321")
                                .add("description", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                                .add("imageSrc", "https://i.imgur.com/1wc10tt.jpg")
                                .build())
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
