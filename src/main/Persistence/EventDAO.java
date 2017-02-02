package main.Persistence;

import main.Model.Event;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 */
public class EventDAO extends ConnectDAO{


    private Collection<Event> events = new ArrayList<>();
    private Event event;
    private int rows;
    private String errorBeschrijving;

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
        try {
            connectToDB();
            preparedStatement = connection.prepareStatement("UPDATE event SET klant_id = ?, gebruiker_id = ?, begin_tijd =" +
                    " ?, eind_tijd = ?, onderwerp = ?, beschrijving = ?,locatie = ? WHERE id = ? ");

            preparedStatement.setInt(1, event.getKlantId());
            preparedStatement.setInt(2, event.getBeheerderId());
            preparedStatement.setTimestamp(3, new Timestamp(event.getBeginTijd().getTime()));
            preparedStatement.setTimestamp(4, new Timestamp(event.getEindTijd().getTime()));
            preparedStatement.setString(5, event.getOnderwerp());
            preparedStatement.setString(6, event.getBeschrijving());
            preparedStatement.setString(7, event.getLocatie());
            preparedStatement.setInt(8, event.getId());
            rows = preparedStatement.executeUpdate();
            closeConnection();
        } catch (SQLException e) {
            errorBeschrijving = e.getLocalizedMessage();
//            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            connectToDB();
            preparedStatement = connection.prepareStatement("DELETE FROM event WHERE id = ?");
            preparedStatement.setInt(1,id);
            rows = preparedStatement.executeUpdate();
            closeConnection();
        } catch (SQLException e) {
            errorBeschrijving = e.getLocalizedMessage();
        }
    }

    @Override
    public void insert() {
        try {
            System.out.println("Hallo");
            connectToDB();
            preparedStatement = connection.prepareStatement("INSERT INTO event (klant_id,gebruiker_id,begin_tijd,eind_tijd," +
                    "onderwerp,beschrijving,locatie) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, event.getKlantId());
            preparedStatement.setInt(2, event.getBeheerderId());
            preparedStatement.setTimestamp(3, new Timestamp(event.getBeginTijd().getTime()));
            preparedStatement.setTimestamp(4, new Timestamp(event.getEindTijd().getTime()));
            preparedStatement.setString(5, event.getOnderwerp());
            preparedStatement.setString(6, event.getBeschrijving());
            preparedStatement.setString(7, event.getLocatie());
            rows = preparedStatement.executeUpdate();
            System.out.println("something happened1");
            closeConnection();
            System.out.println("something happened2");

        }
        catch (Exception e){
            errorBeschrijving = e.getLocalizedMessage();
            System.out.println("something happened3 " + errorBeschrijving);
            e.printStackTrace();
        }
    }

    @Override
    public void select() {
        events.clear();
        try {
            connectToDB();
            preparedStatement = connection.prepareStatement("SELECT * FROM event ORDER BY begin_tijd");
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
            errorBeschrijving = e.getLocalizedMessage();
//            e.printStackTrace();
        }
    }

    public String getErrorBeschrijving(){
        return errorBeschrijving;
    }

    public int getRows(){
        return rows;
    }
}