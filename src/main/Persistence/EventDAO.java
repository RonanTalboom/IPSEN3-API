package main.Persistence;

import main.Model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * Deze class wordt gebruikt met de database te communiceren.
 * Het is verantwoordelijk om query's uit te voeren.
 * @author Ruben van Til
 */
public class EventDAO extends ConnectDAO<Event>{


    @Override
    public void update(Event event) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE event SET klant_id = ?, gebruiker_id = ?, begin_tijd =" +
                    " ?, eind_tijd = ?, onderwerp = ?, beschrijving = ?,locatie = ? WHERE id = ? ");

            preparedStatement.setInt(1, event.getKlantId());
            preparedStatement.setInt(2, event.getBeheerderId());
            preparedStatement.setTimestamp(3, new Timestamp(event.getBeginTijd().getTime()));
            preparedStatement.setTimestamp(4, new Timestamp(event.getEindTijd().getTime()));
            preparedStatement.setString(5, event.getOnderwerp());
            preparedStatement.setString(6, event.getBeschrijving());
            preparedStatement.setString(7, event.getLocatie());
            preparedStatement.setInt(8, event.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }

    @Override
    public void delete(int id) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM event WHERE id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }

    @Override
    public int insert(Event event) {
        Connection connection = createConnection();
        int id = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO event (klant_id,gebruiker_id,begin_tijd,eind_tijd," +
                    "onderwerp,beschrijving,locatie) VALUES (?,?,?,?,?,?,?)", RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, event.getKlantId());
            preparedStatement.setInt(2, event.getBeheerderId());
            preparedStatement.setTimestamp(3, new Timestamp(event.getBeginTijd().getTime()));
            preparedStatement.setTimestamp(4, new Timestamp(event.getEindTijd().getTime()));
            preparedStatement.setString(5, event.getOnderwerp());
            preparedStatement.setString(6, event.getBeschrijving());
            preparedStatement.setString(7, event.getLocatie());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            rs.close();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        closeConnection(connection);
        return id;
    }

    @Override
    public List<Event> select() {
        ArrayList<Event> events = new ArrayList<>();
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM event ORDER BY begin_tijd");
            ResultSet resultSet = preparedStatement.executeQuery();

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return events;
    }

    @Override
    public Event select(int id){
        Event event = new Event();
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM event ORDER BY begin_tijd");
            ResultSet resultSet = preparedStatement.executeQuery();


            event.setId(resultSet.getInt("id"));
            event.setKlantId(resultSet.getInt("klant_id"));
            event.setBeheerderId(resultSet.getInt("gebruiker_id"));
            event.setBeginTijd(resultSet.getTimestamp("begin_tijd"));
            event.setEindTijd(resultSet.getTimestamp("eind_tijd"));
            event.setBeschrijving(resultSet.getString("beschrijving"));
            event.setOnderwerp(resultSet.getString("onderwerp"));
            event.setLocatie(resultSet.getString("locatie"));

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return event;
    }
}