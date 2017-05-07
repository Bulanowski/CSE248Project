import java.util.HashMap;

/**
 * Created by Alex on 5/6/2017.
 */
public class TokenManager {

    private HashMap<String, String> tokens = new HashMap<>(); // token, username
    private SessionIdentifierGenerator sig = new SessionIdentifierGenerator();

    public String assignNewToken(String username) {
        String token = sig.nextSessionId();
        tokens.put(token, username);
        return token;
    }

    public String getUsername(String token) {
        return tokens.get(token);
    }
}
