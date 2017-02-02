package main.Services;

import com.google.inject.Inject;
import javafx.collections.ObservableList;
import main.Persistence.BedrijfTagDAO;

import java.util.*;

/**
 *
 */
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
     * @param bedrijfID
     */
    public ObservableList<Integer> get(int bedrijfID) {
        dao.setBedrijfID(bedrijfID);
        dao.select();
        return dao.getTagIDs();

    }

    /**
     * @param bedrijfID
     * @param tagIDs
     */
    public void add(Collection<Integer> tagIDs, int bedrijfID) {
        dao.setBedrijfID(bedrijfID);
        dao.delete(bedrijfID);
        for (Integer tagID: tagIDs) {
            dao.setTagID(tagID);
            dao.insert();
        }
    }

    /**
     * @param bedrijfID
     */
    public void delete(int bedrijfID) {
        dao.delete(bedrijfID);
    }

}