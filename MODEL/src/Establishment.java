import javax.json.*;
import java.util.*;

/**
 * Created by Alex on 5/3/2017.
 */
public class Establishment extends Profile {

    private String description;
    private String imageSrc;
    private Set<Integer> events = new HashSet<>();
    private String[] hours = new String[7];
    private ArrayList<Employee> employees = new ArrayList<>();
    // TODO private Set<Item> availableItems new Set<>();

    public Establishment() {
        description = "";
        imageSrc = "";
    }

    public Establishment(JsonObject jsonObject) {
        super(jsonObject);
        description = jsonObject.getString("description", "");
        imageSrc = jsonObject.getString("imageSrc", "");
        if (jsonObject.containsKey("events") && !jsonObject.isNull("events")) {
            for (JsonNumber jsonNumber : jsonObject.getJsonArray("events").getValuesAs(JsonNumber.class)) {
                events.add(jsonNumber.intValueExact());
            }
        } else {
            System.err.println("Events not found in account " + jsonObject.getString("username"));
        }
        if (jsonObject.containsKey("hours") && !jsonObject.isNull("hours")) {
            int i = 0;
            for (JsonString jsonString : jsonObject.getJsonArray("hours").getValuesAs(JsonString.class)) {
                hours[i++] = jsonString.getString();
            }
        } else {
            System.err.println("Hours not found in account " + jsonObject.getString("username"));
        }
        if (jsonObject.containsKey("employees") && !jsonObject.isNull("employees")) {
            for (JsonObject employeeJson : jsonObject.getJsonArray("employees").getValuesAs(JsonObject.class)) {
                employees.add(new Employee(employeeJson));
            }
        } else {
            System.err.println("Employees not found in account " + jsonObject.getString("username"));
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public void addEvent(int eventID) {
        events.add(eventID);
    }

    public void removeEvent(int eventID) {
        events.remove(eventID);
    }

    public Set<Integer> getEvents() {
        return events;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void updateEmployee(int index, JsonObject updateEmployeeJson) {
        employees.get(index).update(updateEmployeeJson);
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public JsonObject toJson() {
        JsonObjectBuilder jsonObjectBuilder = startBuildingJson();
        jsonObjectBuilder.add("description", getDescription());
        jsonObjectBuilder.add("imageSrc", getImageSrc());
        if (events.size() > 0) {
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (Integer i : getEvents()) {
                jsonArrayBuilder.add(i);
            }
            jsonObjectBuilder.add("event", jsonArrayBuilder.build());
        } else {
            jsonObjectBuilder.add("events", Json.createArrayBuilder().build());
        }
        if (hours != null) {
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (String s : hours) {
                jsonArrayBuilder.add(s);
            }
            jsonObjectBuilder.add("hours", jsonArrayBuilder.build());
        } else {
            jsonObjectBuilder.add("hours", Json.createArrayBuilder().build());
        }
        if (employees.size() > 0) {
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (Employee employee : getEmployees()) {
                jsonArrayBuilder.add(employee.toJson());
            }
            jsonObjectBuilder.add("employees", jsonArrayBuilder.build());
        } else {
            jsonObjectBuilder.add("employees", Json.createArrayBuilder().build());
        }
        return jsonObjectBuilder.build();
    }
}
