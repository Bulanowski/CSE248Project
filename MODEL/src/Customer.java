import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 5/3/2017.
 */
public class Customer extends Profile {

    private String birthday = "";
    private String gender = "";
    private Set<TagType> preferences = new HashSet<>();

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void addPreference(TagType e) {
        preferences.add(e);
    }

    public void removePreference(TagType e) {
        preferences.remove(e);
    }

    public Set<TagType> getPreferences() {
        return preferences;
    }
}
