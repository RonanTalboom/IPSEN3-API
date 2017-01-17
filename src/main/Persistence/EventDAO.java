package main.Persistence;

import main.Model.Event;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 */
public class EventDAO extends ConnectDAO{


    private Collection<Event> events = new ArrayList<>();
    private Event event;

    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void insert() {

    }

    @Override
    public void select() {
        events.clear();
        try {
            connectToDB();
            preparedStatement = connection.prepareStatement("SELECT * FROM event");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Event e = new Event();
                e.setId(resultSet.getInt("id"));
                e.setKlantId(resultSet.getInt("klant_id"));
                e.setBeheerderId(resultSet.getInt("gebruiker_id"));
                e.setBeginTijd(resultSet.getTimestamp("begin_tijd"));
                e.setEindTijd(resultSet.getTimestamp("eind_tijd"));
                e.setBeschrijving(resultSet.getString("beschrijving"));
                e.setOnderwerp(resultSet.getString("onderwerp"));
                e.setLocatie(resultSet.getString("locatie"));

                events.add(e);
            }
            resultSet.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}