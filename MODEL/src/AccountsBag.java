import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.LinkedList;

/**
 * Created by Alex on 5/3/2017.
 */
@Singleton
public class AccountsBag {

    @EJB
    DataStorageHandler dataStorageHandler = new DataStorageHandler();

    private final ObservableMap<String, Account> accounts = FXCollections.observableHashMap();  // username, account

    public AccountsBag() {
        Object o = dataStorageHandler.readFromFile("accounts");
        if (o != null) {
            for (Account a : (LinkedList<Account>) o) {
                accounts.put(a.getUsername(), a);
            }
        }
        dataStorageHandler.connectAccountsListener(accounts);
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
