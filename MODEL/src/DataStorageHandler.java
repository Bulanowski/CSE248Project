import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableMap;

import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.io.*;
import java.util.LinkedList;

/**
 * Created by Alex on 5/10/2017.
 */
@Singleton
public class DataStorageHandler {

    public void connectAccountsListener(ObservableMap<String, Account> observableMap) {
        observableMap.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                LinkedList<Account> accounts = new LinkedList<>();
                accounts.addAll(((ObservableMap<String, Account>) observable).values());
                saveToFile("accounts", accounts);
            }
        });
    }

    public void connectClubEventsListener(ObservableMap<Integer, ClubEvent> observableMap) {
        observableMap.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
                for (ClubEvent clubEvent : ((ObservableMap<String, ClubEvent>) observable).values()) {
                    jsonArrayBuilder.add(clubEvent.toJson());
                }
                saveToFile("events", jsonArrayBuilder.build().toString());
            }
        });
    }

    public Object readFromFile(String fileName) {
        ObjectInputStream ois = null;
        Object o = null;
        try {
            FileInputStream fis = new FileInputStream(getFile(fileName));
            if (fis.available() < 0) {
                ois = new ObjectInputStream(fis);
                o = ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return o;
    }

    private void saveToFile(String fileName, Object o) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(getFile(fileName)));
            oos.writeObject(o);
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private File getFile(String fileName) {
        File file = new File("");
        String fileDir = file.getAbsolutePath();
        String folderDir = fileDir.substring(0, fileDir.lastIndexOf(File.separatorChar)).concat(File.separatorChar + "data");
        File folder = new File(folderDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        file = new File(folderDir.concat(File.separatorChar + fileName + ".txt"));
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
