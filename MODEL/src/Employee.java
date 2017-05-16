import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Created by Alex on 5/15/2017.
 */
public class Employee {

    private String firstName;
    private String lastName;
    private String position;
    private double wage;
    private EmploymentStatus employmentStatus;

    public Employee() {
        firstName = "";
        lastName = "";
        position = "";
        wage = 0.0;
        employmentStatus = EmploymentStatus.Employed;
    }

    public Employee(JsonObject jsonObject) {
        firstName = jsonObject.getString("firstName", "");
        lastName = jsonObject.getString("lastName", "");
        position = jsonObject.getString("position", "");
        wage = jsonObject.getJsonNumber("wage").doubleValue();
        employmentStatus = EmploymentStatus.valueOf(jsonObject.getString("employmentStatus", "Employed"));
    }

    public void update(JsonObject jsonObject) {
        setFirstName(jsonObject.getString("firstName", firstName));
        setLastName(jsonObject.getString("lastName", lastName));
        setPosition(jsonObject.getString("position", position));
        if (jsonObject.containsKey("wage")) {
            setWage(jsonObject.getJsonNumber("wage").doubleValue());
        }
        setEmploymentStatus(EmploymentStatus.valueOf(jsonObject.getString("employmentStatus", "Employed")));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public EmploymentStatus getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(EmploymentStatus employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public JsonObject toJson() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("firstName", getFirstName());
        jsonObjectBuilder.add("lastName", getLastName());
        jsonObjectBuilder.add("position", getPosition());
        jsonObjectBuilder.add("wage", getWage());
        jsonObjectBuilder.add("employmentStatus", getEmploymentStatus().name());
        return jsonObjectBuilder.build();
    }
}
