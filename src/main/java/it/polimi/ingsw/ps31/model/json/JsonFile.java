package it.polimi.ingsw.ps31.model.json;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Giuseppe on 11/05/2017.
 */
public abstract class JsonFile {

    private JsonFile(){

    }

    public static String filePath(String fileName) { //ottengo il patch della cartella in cui si trova l oggetto che chiama il metodo
        String path = Paths.get(".").toAbsolutePath().normalize().toString() + "\\projectResources\\" + fileName;
        //URL url = this.getClass().getResource("/jsonObject.json");
        return path;
    }

    public static boolean  newFile(String fileName) { //creazione file nella cartella / controllo se esiste già
        String path = filePath(fileName);
        System.out.println(path);
        boolean ret;

        File file = new File(path);

        if (file.exists()) {
            System.out.println("Il file " + path + " esiste");
            ret = false;
        }
        else {
            System.out.println("Il file " + path + " è da creare");
            ret = true;
        }
         return ret;
    }

    public static void saveJsonToFile(String jsonText, String fileName) { // metodo per scrivere una stringa nel file
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePath(fileName)), StandardCharsets.UTF_8))) {
            writer.write(jsonText);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readJsonFromFile(String fileName) { //metodo per leggere una stringa
        Path file = Paths.get(filePath(fileName));
        String jsonText=null;
        try {
            jsonText = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonText;
    }
}
