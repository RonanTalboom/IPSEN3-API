package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.collections.ObservableList;
import main.Model.KlantTag;
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
    public Collection<Integer> get(int klantid) {
        List<Integer> tagIDs = new ArrayList<>();
        for(KlantTag kt : dao.selectByKlant(klantid))
            tagIDs.add(kt.getTagId());
        return tagIDs;
    }

    /**
     * @param tagIDs
     * @param klantID
     */
    public void add(Collection<Integer> tagIDs, int klantID) {
        dao.delete(klantID);
        for (Integer tagID: tagIDs) {
            KlantTag kt = new KlantTag();
            kt.setKlantId(klantID);
            kt.setTagId(tagID);

            dao.insert(kt);
        }
    }

    /**
     * @param klantID
     */
    public void delete(int klantID) {
        dao.delete(klantID);
    }

}