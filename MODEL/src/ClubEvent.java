import java.io.Serializable;

/**
 * Created by Alex on 5/6/2017.
 */
public class ClubEvent implements Serializable {

    private static int eventIDCounter = 1000000;
    private int eventID;
    private Establishment establishment;
    private String date;
    private String time;
    private double admissionPrice;
    private int maxTickets;
    private int purchasedTickets;

    public ClubEvent(Establishment establishment, String date, String time, double admissionPrice, int maxTickets) {
        eventID = eventIDCounter++;
        this.establishment = establishment;
        this.date = date;
        this.time = time;
        this.admissionPrice = admissionPrice;
        this.maxTickets = maxTickets;
        purchasedTickets = 0;
    }

    public int getEventID() {
        return eventID;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getAdmissionPrice() {
        return admissionPrice;
    }

    public void setAdmissionPrice(double admissionPrice) {
        this.admissionPrice = admissionPrice;
    }

    public int getMaxTickets() {
        return maxTickets;
    }

    public int getPurchasedTickets() {
        return purchasedTickets;
    }

    public boolean increasePurchasedTickets(int increaseAmount) {
        if (purchasedTickets + increaseAmount > maxTickets) {
            return false;
        }
        this.purchasedTickets += increaseAmount;
        return true;
    }

    public boolean decreasePurchasedTickets(int decreaseAmount) {
        if (purchasedTickets - decreaseAmount < 0) {
            return false;
        }
        this.purchasedTickets -= decreaseAmount;
        return true;
    }
}
