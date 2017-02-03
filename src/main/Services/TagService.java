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
        return dao.select();
    }

    /**
     * @param id
     */
    public Tag get(int id) {
        return dao.select(id);
    }

    /**
     * @param tag
     */
    public void add(Tag tag) {
        dao.insert(tag);
    }

    /**
     * @param id
     * @param tag
     */
    public void update(int id, Tag tag) {
        dao.update(tag);
    }

    /**
     * @param id
     */
    public void delete(int id) {
        dao.delete(id);
    }

}