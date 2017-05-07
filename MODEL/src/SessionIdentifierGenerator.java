import java.security.SecureRandom;
import java.math.BigInteger;

/**
 * Created by Alex on 5/3/2017.
 */
public final class SessionIdentifierGenerator {
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}
