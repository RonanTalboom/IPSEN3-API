package main.Model;

/**
 * Created by Ruben van Til on 3-2-2017.
 */
public class KlantTag {
    private int TagId;
    private int KlantId;

    public int getTagId() {
        return TagId;
    }

    public void setTagId(int tagId) {
        TagId = tagId;
    }

    public int getKlantId() {
        return KlantId;
    }

    public void setKlantId(int klantId) {
        KlantId = klantId;
    }
}
