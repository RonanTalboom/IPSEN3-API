package main.Services;

import com.google.inject.Inject;
import main.Model.Beheerder;
import main.Model.Event;
import main.Persistence.EventDAO;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Dit is de EventService. Deze klasse is verantwoordelijk voor het communiceren met de EventDAO.
 *
 * @author Ruben van Til
 * @version 1.0, Februari 2017
 */
public class EventService {

    /**
     * Object van de DAO. Dit doet de communicatie met de database.
     */
    public EventDAO dao;


    /**
     * Constructor.
     *
     * @param dao Injectie van DAO object.
     */
    @Inject
    public EventService(EventDAO dao) {
        this.dao = dao;
    }

    /**
     * Methode om alle events uit de database op te halen.
     *
     * @param beheerder Beheerder die gebruikt wordt om opgehaalde events te filteren op de beheerder die de aanvraag doet.
     * @return Collectie van events die behoren tot meegegeven beheerder.
     */
    public Collection<Event> getAll(Beheerder beheerder) {
        ArrayList<Event> events = new ArrayList<>();
        for (Event event : dao.select())
            if (event.getBeheerderId() == (beheerder.getId()))
                events.add(event);
        return events;
    }

    /**
     * Methode om een enkel event op te halen uit de database
     *
     * @param id        id van het event dat moet worden opgehaald.
     * @param beheerder Indien deze beheerder niet de eigenaar is van het object wordt er een null teruggegeven.
     * @return Event object met het meegegeven id.
     */
    public Event get(Beheerder beheerder, int id) {
        Event event = dao.select(id);
        if (event.getBeheerderId() == (beheerder.getId()))
            return event;
        return null;
    }

    /**
     * methode om een event toe te voegen aan de database.
     *
     * @param event Het event object dat moet worden toegevoegd aan de database.
     */
    public void add(Event event) {
        dao.insert(event);
    }

    /**
     * Methode om een Event in de database aan te passen
     *
     * @param event Nieuwe versie van het event dat moet worden aangepast.
     */
    public void update(Event event) {
        dao.update(event);
    }

    /**
     * Methode om een Event uit de database te verwijderen.
     *
     * @param id ID van het Event dat verwijdert moet worden.
     */
    public void delete(int id) {
        dao.delete(id);
    }
}
