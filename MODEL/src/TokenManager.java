import javax.ejb.Singleton;
import java.util.HashMap;

/**
 * Created by Alex on 5/6/2017.
 */
@Singleton
public class TokenManager {

    private final HashMap<String, String> tokens = new HashMap<>(); // token, username
    private final SessionIdentifierGenerator sig = new SessionIdentifierGenerator();
    //Token: long timeImMilliseconds, default expiration time, String username

    public String assignNewToken(String username) {
        String token = sig.nextSessionId();
        tokens.put(token, username);
        return token;
    }

    public boolean authenticateToken(String token) {
        return tokens.containsKey(token);
    }

    public String getUsername(String token) {
        return tokens.get(token);
    }

    public void removeUser(String token) {
        tokens.remove(token);
    }
}
