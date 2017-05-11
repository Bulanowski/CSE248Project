import java.io.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Alex on 5/10/2017.
 */
public class DataStorageHandler implements Observer {

    public void beginObserving(AccountsBag accountsBag, ClubEventsBag clubEventsBag) {
        accountsBag.addObserver(this);
        clubEventsBag.addObserver(this);
    }

    public Object readFromFile(String fileName) {
        Object o = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getFile(fileName)));
            o = ois.readObject();
            ois.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return o;
    }

    private void saveToFile(String fileName, Object o) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getFile(fileName)));
            oos.writeObject(o);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
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
        return new File(folderDir.concat(File.separatorChar + fileName + ".txt"));
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof AccountsBag) {
            saveToFile("accounts", o);
        } else if (observable instanceof ClubEventsBag) {
            saveToFile("clubEvents", o);
        } else {
            saveToFile("oops", "Shit");
        }
    }

}
