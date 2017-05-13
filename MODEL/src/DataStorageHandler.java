import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableMap;

import javax.ejb.Singleton;
import javax.json.*;
import java.io.*;
import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Alex on 5/10/2017.
 */
@Singleton
public class DataStorageHandler {

    public void connectAccountsListener(ObservableMap<String, Account> observableMap) {
//        observableMap.addListener(new InvalidationListener() {
//            @Override
//            public void invalidated(Observable observable) {
//                LinkedList<Account> accounts = new LinkedList<>();
//                accounts.addAll(((ObservableMap<String, Account>) observable).values());
//                saveToFile("accounts", accounts);
//            }
//        });
    }

    public void connectClubEventsListener(ObservableMap<Integer, ClubEvent> observableMap) {
//        observableMap.addListener(new InvalidationListener() {
//            @Override
//            public void invalidated(Observable observable) {
//                JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
//                for (ClubEvent clubEvent : ((ObservableMap<String, ClubEvent>) observable).values()) {
//                    jsonArrayBuilder.add(clubEvent.toJson());
//                }
//                saveToFile("events", jsonArrayBuilder.build().toString());
//            }
//        });
    }

    public String readFromFile(String fileName) {
        Scanner in = null;
        String s = null;
        try {
            in = new Scanner(getFile(fileName));
            in.useDelimiter("\\A");
            if (in.hasNext()) {
                s = in.next();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return s;
    }

    public void saveToFile(String fileName, JsonArray jsonArray) {
        JsonWriter jsonWriter = null;
        try {
            jsonWriter = Json.createWriter(new FileWriter(getFile(fileName)));
            jsonWriter.writeArray(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (jsonWriter != null) {
                jsonWriter.close();
            }
        }
    }

    public void saveToFile(String fileName, JsonObject jsonObject) {
        JsonWriter jsonWriter = null;
        try {
            jsonWriter = Json.createWriter(new FileWriter(getFile(fileName)));
            jsonWriter.writeObject(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (jsonWriter != null) {
                jsonWriter.close();
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
        file = new File(folderDir.concat(File.separatorChar + fileName + ".dat"));
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
