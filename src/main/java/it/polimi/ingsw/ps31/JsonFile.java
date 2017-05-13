package it.polimi.ingsw.ps31;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Giuseppe on 11/05/2017.
 */
public class JsonFile {

    public static String FilePath(String nomeFile) {
        String path;
        return path = Paths.get(".").toAbsolutePath().normalize().toString() + "\\" + nomeFile;
    }

    public static void newFile(String nomeFile) {
        String path = FilePath(nomeFile);
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

    public static void saveJsonFile(String jsonText, String name) {
        Path file = Paths.get(name);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(name), StandardCharsets.UTF_8))) {
            writer.write(jsonText);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readJsonFile(String name) {
        Path file = Paths.get(name);
        String jsonText=null;
        try {
            jsonText = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonText;
    }
}
