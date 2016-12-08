package main.Services;

import main.Model.Tag;
import main.Persistence.TagDAO;

/**
 *
 */
public class TagService {

    /**
     * Default constructor
     */
    public TagService() {
    }

    /**
     *
     */
    public TagDAO dao;


    /**
     * @param dao
     */
    public void TagService(TagDAO dao) {
        this.dao = dao;
    }

    /**
     *
     */
    public void getAll() {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void get(int id) {
        // TODO implement here
    }

    /**
     * @param tag
     */
    public void add(Tag tag) {
        // TODO implement here
    }

    /**
     * @param id
     * @param tag
     */
    public void update(int id, Tag tag) {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void delete(int id) {
        // TODO implement here
    }

}