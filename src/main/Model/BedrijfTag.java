package main.Model;

/**
 * Dit is de BedrijfTag class. Hier worden gegevens van een bedrijf en Tag relatie opgeslagen
 * @author Ruben van Til, Shaban Jama
 * @version 1.0, februari 2017
 */
public class BedrijfTag {

    /**
     * Dit zijn de standaard waardes van de BedrijfTag
     * tagId en bedrijfId
     */
    private int tagId, bedrijfId;

    /**
     * Methode die de id van de tag returned.
     * @return tagId
     */
    public int getTagId() {
        return tagId;
    }

    /**
     * Methode die de Tagid van de bedrijfTag set.
     * @param tagId is de id van de bedrijf.
     */
    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    /**
     * Methode die de id van de bedrijf returned.
     * @return bedrijfID
     */
    public int getBedrijfId() {
        return bedrijfId;
    }

    /**
     * Methode die de bedrijfId van de bedrijfTag set.
     * @param bedrijfId is de id van de bedrijf.
     */
    public void setBedrijfId(int bedrijfId) {
        this.bedrijfId = bedrijfId;
    }
}

