package it.polimi.ingsw.ps31.model.json;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Giuseppe on 11/05/2017.
 *
 * Classe per poter creare e leggere file specifici
 *
 * @see File
 * @see String
 * @see Path
 */
public abstract class JsonFile {

    private JsonFile() {

    }

    /**
     * ottengo il path assoluto delle projectResources nel quale verranno messi tutti i vari file e risorse del progetto
     *
     * @param fileName nome del file a cui si vuole accedere
     */
    public static String filePath(String fileName) {
        String path = Paths.get(".").toAbsolutePath().normalize().toString() + "\\projectResources\\" + fileName;
        //URL url = this.getClass().getResource("/jsonObject.json");
        return path;
    }

    /**
     * metodo per verificare se è già presente un file con un dato nome
     *
     * @param fileName nome del file che si vuole verificare se esiste prima di scriverci
     * @return
     */
    public static boolean isFileExists(String fileName) {
        String path = filePath(fileName);
        boolean ret;
        File file = new File(path);
        if (file.exists()) {
            System.out.println("Il file " + path + " esiste");
            ret = false;
        } else {
            System.out.println("Il file " + path + " è da creare");
            ret = true;
        }
        return ret;
    }

    /**
     * metodo per scrivere una stringa in un file
     *
     * @param jsonText stringa da scrivere
     * @param fileName nome del file che si vuole creare su cui poi scrivere
     */
    public static void saveJsonToFile(String jsonText, String fileName) {
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

    /**
     * metodo per leggere un file e salvarne il contenuto in una stringa
     *
     * @param fileName nome del file da leggere
     * @return stringa con le informazioni lette da file
     */
    public static String readJsonFromFile(String fileName) { //metodo per leggere una stringa
        Path file = Paths.get(filePath(fileName));
        String jsonText = null;
        try {
            jsonText = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonText;
    }
}
