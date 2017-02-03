package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.collections.ObservableList;
import main.Model.BedrijfTag;
import main.Persistence.BedrijfTagDAO;

import java.util.*;

/**
 *
 */
@Singleton
public class BedrijfTagService {

    /**
     *
     */
    public BedrijfTagDAO dao;

    /**
     * @param dao
     */
    @Inject
    public void BedrijfTagService(BedrijfTagDAO dao) {
        this.dao = dao;
    }

    /**
     * @param bedrijfid
     */
    public Collection<Integer> get(int bedrijfid) {
        List<Integer> tagIDs = new ArrayList<>();
        for(BedrijfTag kt : dao.selectByBedrijf(bedrijfid))
            tagIDs.add(kt.getTagId());
        return tagIDs;
    }

    /**
     * @param tagIDs
     * @param bedrijfID
     */
    public void add(Collection<Integer> tagIDs, int bedrijfID) {
        dao.delete(bedrijfID);
        for (Integer tagID: tagIDs) {
            BedrijfTag kt = new BedrijfTag();
            kt.setBedrijfId(bedrijfID);
            kt.setTagId(tagID);

            dao.insert(kt);
        }
    }

    /**
     * @param bedrijfID
     */
    public void delete(int bedrijfID) {
        dao.delete(bedrijfID);
    }

}