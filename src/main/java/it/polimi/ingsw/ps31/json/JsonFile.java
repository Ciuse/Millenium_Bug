package it.polimi.ingsw.ps31.json;

import java.io.*;
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
        String path = Paths.get(".").toAbsolutePath().normalize().toString() + "\\" + fileName;
        return path;
    }

    public static void newFile(String fileName) { //creazione file nella cartella / controllo se esiste già
        String path = filePath(fileName);
        System.out.println(path);
        try {
            File file = new File(path);

            if (file.exists())
                System.out.println("Il file " + path + " esiste");
            else if (file.createNewFile())
                System.out.println("Il file " + path + " è stato creato");
            else
                System.out.println("Il file " + path + " non può essere creato");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveJsonToFile(String jsonText, String fileName) { // metodo per scrivere una stringa nel file
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
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
        Path file = Paths.get(fileName);
        String jsonText=null;
        try {
            jsonText = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonText;
    }
}
