package main.Model;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by drynl on 3-11-2016.
 */
public class Bestand {
    /**
     * Dit zijn de standaard waardes van de bestand
     * bestand_Id, Klant_Id, fileName
     */
    private int bestand_Id;
    private int klant_Id;
    private String fileName;
    private String base64;


    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    /**
     * Methode die de klant_id van de bestand set
     */
    public int getKlant_Id() {
        return klant_Id;
    }

    public void setKlant_Id(int klant_Id) {
        this.klant_Id = klant_Id;
    }

    /**
     * Methode die de fileName van de bestand returned
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * Methode die de fileName van de bestand set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * Methode die de bestand_id van de bestand returned
     * @return bestand_id
     */
    public int getBestand_Id() {
        return bestand_Id;
    }
    /**
     * Methode die de bestandId van de bestand set
     */
    public void setBestand_Id(int bestand_Id) {
        this.bestand_Id = bestand_Id;
    }
}
