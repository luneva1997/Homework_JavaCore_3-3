import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        openZip("C:/Users/whats/Desktop/Games/savegames/zip.zip", "C:/Users/whats/Desktop/Games/savegames/");
        GameProgress game = openProgress("C:/Users/whats/Desktop/Games/savegames/save0");
        System.out.println(game);
    }

    public static void openZip(String path, String directory) {
        try (ZipInputStream unzipper = new ZipInputStream(new
                FileInputStream(path))) {
            ZipEntry entry;
            String name;
            while ((entry = unzipper.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream file = new FileOutputStream(directory + name);
                for (int c = unzipper.read(); c != -1; c = unzipper.read()) {
                    file.write(c);
                }
                file.flush();
                unzipper.closeEntry();
                file.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress (String file){
        GameProgress game = null;
        try (FileInputStream reader = new FileInputStream(file);
             ObjectInputStream readerO = new ObjectInputStream(reader)){
            game = (GameProgress) readerO.readObject();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return game;
    }
}