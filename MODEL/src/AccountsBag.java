import java.util.HashMap;

/**
 * Created by bonaa23 on 5/3/2017.
 */
public class AccountsBag {

    HashMap<String, Account> accounts = new HashMap<>();

    public void addAccount(Account a) {
//        if (!usernameInUse(a.getUsername())) {
            accounts.put(a.getUsername(), a);
//        }
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