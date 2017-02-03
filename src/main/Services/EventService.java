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
        ArrayList<Event> events = new ArrayList<>();
        for(Event event: dao.select())
            if(event.getBeheerderId() == (beheerder.getId()))
                events.add(event);
        return events;
    }

    /**
     * @param id
     */
    public Event get(int id) {
        return dao.select(id);
    }

    /**
     * @param event
     */
    public void add(Event event) {
        dao.insert(event);
    }

    /**
     * @param authenticator
     * @param id
     * @param event
     */
    public void update(Beheerder authenticator, int id, Event event) {
        dao.update(event);
    }

    /**
     * @param id
     */
    public void delete(int id) {
        dao.delete(id);
    }
}
