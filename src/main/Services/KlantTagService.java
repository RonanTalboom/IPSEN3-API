package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.collections.ObservableList;
import main.Persistence.KlantTagDAO;

import java.util.*;

/**
 *
 */
@Singleton
public class KlantTagService {

    /**
     *
     */
    public KlantTagDAO dao;

    /**
     * @param dao
     */
    @Inject
    public void KlantTagService(KlantTagDAO dao) {
        this.dao = dao;
    }

    /**
     * @param klantid
     */
    public ObservableList<Integer> get(int klantid) {
        dao.setKlantID(klantid);
        dao.select();
        return dao.getTagIDs();

    }

    /**
     * @param tagIDs
     * @param klantID
     */
    public void add(Collection<Integer> tagIDs, int klantID) {
        dao.setKlantID(klantID);
        dao.delete(klantID);
        for (Integer tagID: tagIDs) {
            dao.setTagID(tagID);
            dao.insert();
        }
    }

    /**
     * @param klantID
     */
    public void delete(int klantID) {
        dao.setKlantID(klantID);
        dao.delete(klantID);
    }

}