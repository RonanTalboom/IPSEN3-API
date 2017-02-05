package main.Model;

/**
 * Dit is de KlantTag class. Hier worden gegevens van een klant en Tag relatie opgeslagen
 * @author Ruben van Til, Shaban Jama
 * @version 1.0, februari 2017
 */
public class KlantTag {

    /**
     * Dit zijn de standaard waardes van de KlantTag
     * tagId en klantId
     */
    private int tagId, klantId;

    /**
     * Methode die de id van de tag returned.
     * @return tagId
     */
    public int getTagId() {
        return tagId;
    }

    /**
     * Methode die de Tagid van de klantTag set.
     * @param tagId is de id van de klant.
     */
    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    /**
     * Methode die de id van de klant returned.
     * @return klantId
     */
    public int getKlantId() {
        return klantId;
    }

    /**
     * Methode die de klantId van de klanttag set.
     * @param klantId is de id van de klant.
     */
    public void setKlantId(int klantId) {
        this.klantId = klantId;
    }
}
