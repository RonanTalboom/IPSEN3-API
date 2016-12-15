package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.Tag;
import main.Persistence.TagDAO;

import java.util.Collection;

/**
 *
 */
@Singleton
public class TagService {

    /**
     *
     */
    public TagDAO dao;

    /**
     * @param dao
     */
    @Inject
    public void TagService(TagDAO dao) {
        this.dao = dao;
    }

    /**
     *
     */
    public Collection<Tag> getAll() {
        dao.select();
        return dao.getObserversTags();
    }

    /**
     * @param id
     */
    public Tag get(int id) {
        dao.select();
        for (Tag tag: dao.getObserversTags()){
            if (tag.getId() == (id)) {
                return tag;
            }
        }
        return null;
    }

    /**
     * @param tag
     */
    public void add(Tag tag) {
        dao.setTag(tag);
        dao.insert();
    }

    /**
     * @param id
     * @param tag
     */
    public void update(int id, Tag tag) {
        dao.setTag(tag);
        dao.update(id);
    }

    /**
     * @param id
     */
    public void delete(int id) {
        dao.delete(id);
    }

}