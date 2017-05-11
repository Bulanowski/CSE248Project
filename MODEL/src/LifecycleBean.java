import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Created by Alex on 5/11/2017.
 */
@Singleton
@Startup
public class LifecycleBean {

    public static final DataStorageHandler dataStorageHandler = new DataStorageHandler();
    public static final AccountsBag accountsBag = new AccountsBag(dataStorageHandler);
    public static final TokenManager tokenManager = new TokenManager();
    public static final ClubEventsBag clubEventsBag = new ClubEventsBag(dataStorageHandler);

    @PostConstruct
    public void init() {
        dataStorageHandler.beginObserving(accountsBag, clubEventsBag);
    }

    @PreDestroy
    public void destroy() {
        accountsBag.saveChanges();
        clubEventsBag.saveChanges();
    }
}
