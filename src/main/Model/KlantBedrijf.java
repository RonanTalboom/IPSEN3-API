package main.Model;

/**
 * Dit is de KlantBedrijf model class. Deze is verantwoordelijk voor alle gegevens van de klant/Bedrijf.
 * @author Mohamed El Baze
 * @version 0.1 Januari 2017
 */
public class KlantBedrijf {
    /**
     * Dit zijn de standaard waardes van de klanten/Bedrijven
     * klantId en BedrijfId
     */
    private int klantId;
    private int bedrijfId;

    /**
     * Methode die de id van de klant returned
     * @return
     */
    public int getKlantId() {
        return klantId;
    }

    /**
     * Methode die de id van de klant set
     * @param klantId
     */
    public void setKlantId(int klantId) {
        this.klantId = klantId;
    }

    /**
     * Methode die de id van de bedrijf returned
     * @return
     */
    public int getBedrijfId() {
        return bedrijfId;
    }

    /**
     * Methode die de id van de bedrijf set
     * @param bedrijfId
     */
    public void setBedrijfId(int bedrijfId) {
        this.bedrijfId = bedrijfId;
    }
}
