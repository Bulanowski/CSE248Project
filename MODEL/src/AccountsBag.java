import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;

/**
 * Created by Alex on 5/3/2017.
 */
public class AccountsBag extends Observable implements Serializable {

    private final HashMap<String, Account> accounts;    // username, account

    public AccountsBag(DataStorageHandler dataStorageHandler) {
        Object o = dataStorageHandler.readFromFile("accounts");
        if (o != null && o instanceof HashMap) {
            accounts = (HashMap<String, Account>) o;
        } else {
            accounts = new HashMap<>();
        }
    }

    public void addAccount(Account a) {
        if (!usernameInUse(a.getUsername())) {
            accounts.put(a.getUsername(), a);
            saveChanges();
        }
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

    public void saveChanges() {
        setChanged();
        notifyObservers(accounts);
    }
}
