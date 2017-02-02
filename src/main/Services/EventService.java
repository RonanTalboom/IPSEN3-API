package main.Services;

import com.google.inject.Inject;
import main.Model.Beheerder;
import main.Model.Event;
import main.Persistence.EventDAO;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Ruben van Til on 17-1-2017.
 */
public class EventService {

    /**
     *
     */
    public EventDAO dao;



    /**
     * @param dao
     */
    @Inject
    public EventService(EventDAO dao) {
        this.dao = dao;
    }

    /**
     *
     */
    public Collection<Event> getAll(Beheerder beheerder) {
        dao.select();
        ArrayList<Event> events = new ArrayList<>();
        for(Event event: dao.getEvents()) {
            if(event.getBeheerderId() == (beheerder.getId())){
                events.add(event);
            }

        }
        return events;
    }

    /**
     * @param id
     */
    public Event get(int id) {
        dao.select();
        for(Event event: dao.getEvents()) {
            if(event.getId() == (id)){
                return event;
            }

        }
        return null;

    }

    /**
     * @param event
     */
    public void add(Event event) {
        dao.setEvent(event);
        dao.insert();
    }

    /**
     * @param authenticator
     * @param id
     * @param event
     */
    public void update(Beheerder authenticator, int id, Event event) {
        dao.setEvent(event);
        dao.update(id);
    }

    /**
     * @param id
     */
    public void delete(int id) {
        dao.delete(id);
    }
}
