/**
 * Created by bonaa23 on 5/3/2017.
 */
public class Establishment extends Account {

    public Establishment(String username, String password, String email, String address, String zip, String phone) {
        super(username, password, email, address, zip, phone);
    }

    public String toJson() {
        return super.toString();
    }
}
