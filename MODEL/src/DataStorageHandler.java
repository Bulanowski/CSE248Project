import javax.ejb.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.stream.JsonParsingException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Alex on 5/10/2017.
 */
@Singleton
public class DataStorageHandler {

    public JsonObject readFromFile(String fileName) {
        JsonReader jsonReader = null;
        JsonObject jsonObject = null;
        try {
            jsonReader = Json.createReader(new FileReader(getFile(fileName)));
            jsonObject = jsonReader.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonParsingException e) {
            System.err.println("JsonParsingException when loading from file " + fileName + ".dat");
        } finally {
            if (jsonReader != null) {
                jsonReader.close();
            }
        }
        return jsonObject;
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
