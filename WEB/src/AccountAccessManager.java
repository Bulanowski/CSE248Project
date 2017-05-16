import javax.ejb.EJB;
import javax.json.*;
import javax.naming.directory.InvalidAttributeIdentifierException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Alex on 5/3/2017.
 */
@Path("/account")
public class AccountAccessManager {

    @EJB
    private AccountsBag accountsBag = new AccountsBag();

    @EJB
    private ClubEventsBag clubEventsBag = new ClubEventsBag();

    @EJB
    private TokenManager tokenManager = new TokenManager();

    @POST
    @Path("/register")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String register(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        // TODO check for validity and availability of entered information
        JsonObjectBuilder registrationErrorBuilder = Json.createObjectBuilder();
        boolean error = false;
        if (accountsBag.usernameInUse(jsonObject.getString("username"))) {
            registrationErrorBuilder.add("username", "Username is already in use");
            error = true;
        }
//        if (!jsonObject.getString("email").matches("(\\S+@\\w+\\.\\S+)")) {
//            registrationErrorBuilder.add("email", "This is not a valid email address");
//            error = true;
//        }
        if (error) {
            return registrationErrorBuilder.build().toString();
        }
        try {
            // TODO change this to just pass through the jsonObject
            accountsBag.addAccount(new Account(jsonObject));
        } catch (InvalidAttributeIdentifierException ex) {
            return "Registration failed";
        }
        return "Register Successful";
    }


    @POST
    @Path("/login")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        if (accountsBag.verifyLogin(username, password)) {
            JsonObject json = Json.createObjectBuilder()
                    .add("url", (accountsBag.getUser(username).getProfile() instanceof Customer ? "homepage/" : "homepage-establishment/"))
                    .add("token", tokenManager.assignNewToken(username))
                    .add("account", accountsBag.getUser(username).toJson())
                    .build();
            return json.toString();
        } else
            return "Login Failed";
    }

    @POST
    @Path("/signOut")
    @Consumes(MediaType.TEXT_PLAIN)
    public void signOut(String token) {
        tokenManager.removeUser(token);
    }

    @POST
    @Path("/settings/changePassword")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String changePassword(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        if (tokenManager.authenticateToken(jsonObject.getString("token"))) {
            Account account = accountsBag.getUser(tokenManager.getUsername(jsonObject.getString("token")));
            if (account.changePassword(jsonObject.getString("oldPassword"), jsonObject.getString("newPassword"))) {
                return "Password changed successfully";
            }
        }
        return "Password change failed";
    }

    @POST
    @Path("/settings/get")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getSettings(String token) {
        if (tokenManager.authenticateToken(token)) {
            Account account = accountsBag.getUser(tokenManager.getUsername(token));
            return account.getProfile().toJson().toString();
        }
        return "Invalid Token";
    }

    @POST
    @Path("/settings/set")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String setSettings(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        if (tokenManager.authenticateToken(jsonObject.getString("token"))) {
            Profile profile = accountsBag.getUser(tokenManager.getUsername(jsonObject.getString("token"))).getProfile();
            profile.setName(jsonObject.getString("name"));
            profile.setAddress(jsonObject.getString("address"));
            profile.setPhone(jsonObject.getString("phone"));
            profile.setZip(jsonObject.getString("zip"));
            if (profile instanceof Customer) {
                Customer customer = (Customer) profile;
                customer.setBirthday(jsonObject.getString("birthday"));
            } else if (profile instanceof Establishment) {
                Establishment establishment = (Establishment) profile;
                establishment.setImageSrc(jsonObject.getString("imageSrc"));
            }
            return "Settings changed successfully";
        }
        return "Settings change failed";
    }

    @POST
    @Path("/preferences/get")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getPreferences(String token) {
        if (tokenManager.authenticateToken(token)) {
            Account account = accountsBag.getUser(tokenManager.getUsername(token));
            if (account.getProfile() instanceof Customer) {
                Customer customer = (Customer) account.getProfile();
                Object[] preferences = customer.toJson().getJsonArray("preferences").toArray();

            }
        }
        return "Invalid Token";
    }

    @POST
    @Path("/preferences/set")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String setPreferences(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        String username = tokenManager.getUsername(jsonObject.getString("token"));
        if (username == null) {
            return "Invalid Token";
        }
        Account account = accountsBag.getUser(username);
        if (account.getProfile() instanceof Customer) {
            Customer customer = (Customer) account.getProfile();
            for (JsonString preferenceString : jsonObject.getJsonArray("preferences").getValuesAs(JsonString.class)) {
                customer.addPreference(TagType.valueOf(preferenceString.getString()));
            }
            return "Preferences changed successfully";
        }
        return "Failed to change preferences";
    }

    @POST
    @Path("/establishment/search")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String searchEstablishments(String jsonString) {
        JsonObject jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        String query = jsonObject.getString("query").toLowerCase();
        Integer page = Integer.parseInt(jsonObject.getString("page"));
        ArrayList<Establishment> searchResult = new ArrayList<>();
        ArrayList<Establishment> zipResult = new ArrayList<>();
        ArrayList<Establishment> nameResult = new ArrayList<>();
        ArrayList<Establishment> descriptionResult = new ArrayList<>();
        for (Establishment e : accountsBag.getEstablishments()) {
            if (e.getZip().toLowerCase().contains(query)) {
                zipResult.add(e);
            } else if (e.getName().toLowerCase().contains(query)) {
                nameResult.add(e);
            } else if (e.getDescription().toLowerCase().contains(query)) {
                descriptionResult.add(e);
            }
        }
        searchResult.addAll(zipResult);
        searchResult.addAll(nameResult);
        searchResult.addAll(descriptionResult);
        if (searchResult.size() == 0) {
            return "No results found";
        }
        if (searchResult.size() < 10 * (page - 1)) {
            page = 1;
        }
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (int i = 10 * (page - 1); i < page * 10; i++) {
            if (i >= searchResult.size()) {
                break;
            }
            jsonArrayBuilder.add(searchResult.get(i).toJson());
        }
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("pages", Math.ceil((double) searchResult.size() / 10));
        jsonObjectBuilder.add("result", jsonArrayBuilder.build());
        return jsonObjectBuilder.build().toString();
    }

    @POST
    @Path("/establishment/get")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getEstablishment(String establishmentName) {
        Establishment establishment = accountsBag.searchEstablishmentsByName(establishmentName);
        if (establishment != null) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("establishment", establishment.toJson());
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (Integer eventID : establishment.getEvents()) {
                ClubEvent clubEvent = clubEventsBag.getEvent(eventID);
                if (clubEvent != null) {
                    jsonArrayBuilder.add(clubEvent.toJson());
                }
            }
            jsonObjectBuilder.add("events", jsonArrayBuilder.build());
            return jsonObjectBuilder.build().toString();
        } else {
            return "Establishment " + establishmentName + " not found";
        }
    }

    @POST
    @Path("/establishment/get/editable")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getEstablishmentByToken(String token) {
        String username = tokenManager.getUsername(token);
        if (username == null) {
            return "Invalid token";
        }
        Account account = accountsBag.getUser(username);
        if (account.getProfile() instanceof Establishment) {
            Establishment establishment = (Establishment) account.getProfile();
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("establishment", establishment.toJson());
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (Integer eventID : establishment.getEvents()) {
                ClubEvent clubEvent = clubEventsBag.getEvent(eventID);
                if (clubEvent != null) {
                    jsonArrayBuilder.add(clubEvent.toJson());
                }
            }
            jsonObjectBuilder.add("events", jsonArrayBuilder.build());
            return jsonObjectBuilder.build().toString();
        }
        return "An error occurred";
    }

    @POST
    @Path("/token")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getUserFromToken(String token) {
        String username = tokenManager.getUsername(token);
        if (username == null) {
            return "Token Not Found";
        }
        Account account = accountsBag.getUser(username);
        if (account == null) {
            return "Username Not Found";
        }
        return account.toJson().getString("accountType");
    }
}
