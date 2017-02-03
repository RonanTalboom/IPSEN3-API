package main.Model;

/**
 * Created by Ruben van Til on 3-2-2017.
 */
public class BedrijfTag {
    private int TagId;
    private int BedrijfId;

    public int getTagId() {
        return TagId;
    }

    public void setTagId(int tagId) {
        TagId = tagId;
    }

    public int getBedrijfId() {
        return BedrijfId;
    }

    public void setBedrijfId(int bedrijfId) {
        BedrijfId = bedrijfId;
    }
}

