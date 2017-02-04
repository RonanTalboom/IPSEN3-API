package main.Persistence;


//import Controller.LoginController;
import com.google.inject.Singleton;
import main.Model.Beheerder;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Deze class wordt gebruikt met de database te praten.
 * Het is verantwoordelijk om query uit te voeren.
 * Created by murtazaaydogdu on 12-10-16.
 * @author Murtaza Aydogdu
 * @version 1.0, Nov 2016
 */
@Singleton
public class BeheerderDAO extends ConnectDAO<Beheerder> {


    /**
     * Deze methode zorgt ervoor dat de update wordt uitgevoerd om
     * de nieuwe waarde van de beheerder in te vullen.
     * @param beheerder
     */
    @Override
    public void update(Beheerder beheerder) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE gebruiker SET voornaam = ?, achternaam = ?, adres =" +
                    " ?, postcode = ?, woonplaats = ?, telefoon = ?,email = ?, wachtwoord = ?, isactief = ? WHERE id = ?");
            preparedStatement.setString(1,beheerder.getVoornaam());
            preparedStatement.setString(2,beheerder.getAchternaam());
            preparedStatement.setString(3,beheerder.getAdres());
            preparedStatement.setString(4,beheerder.getPostcode());
            preparedStatement.setString(5,beheerder.getWoonplaats());
            preparedStatement.setString(6,beheerder.getTelefoon());
            preparedStatement.setString(7,beheerder.getEmail());
            preparedStatement.setString(8,beheerder.getWachtwoord());
            preparedStatement.setBoolean(9,beheerder.isActief());
            preparedStatement.setInt(10, beheerder.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }
    /**
     * Deze methode zorgt ervoor dat de delete wordt uitgevoerd om
     * geselecteerde beheerder te verwijderen.
     * @param id
     */
    @Override
    public void delete(int id) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE gebruiker set isActief = FALSE WHERE ID = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection( connection);
    }

    public void deleteUndo(int id, boolean bool) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE gebruiker set isActief = ? WHERE ID = ?");
            preparedStatement.setBoolean(1,bool);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection( connection);
    }

    /**
     * Deze methode zorgt ervoor dat de insert wordt uitgevoerd om
     * de nieuwe een nieuwe beheerder toe te voegen.
     */
    @Override
    public int insert(Beheerder beheerder) {
        Connection connection = createConnection();
        int id = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO gebruiker (voornaam,achternaam,adres,postcode," +
                    "woonplaats,telefoon,email,wachtwoord,rechten_id) VALUES (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, beheerder.getVoornaam());
            preparedStatement.setString(2, beheerder.getAchternaam());
            preparedStatement.setString(3, beheerder.getAdres());
            preparedStatement.setString(4, beheerder.getPostcode());
            preparedStatement.setString(5, beheerder.getWoonplaats());
            preparedStatement.setString(6, beheerder.getTelefoon());
            preparedStatement.setString(7, beheerder.getEmail());
            preparedStatement.setString(8, beheerder.getWachtwoord());
            preparedStatement.setInt(9, beheerder.getRechten_id());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return id;
    }
    /**
     * Deze methode zorgt ervoor dat de select wordt uitgevoerd om
     * alle beheerders te selecteren.
     */
    @Override
    public List<Beheerder> select() {
        ArrayList<Beheerder> beheerders = new ArrayList<>();
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM gebruiker ORDER BY id;");
            ResultSet resultSet = preparedStatement.executeQuery();
            beheerders = fillListFromResultSet(resultSet, beheerders);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return beheerders;
    }

    /**
     * Deze methode zorgt ervoor dat alle beheerders wordt geselecteerd die actief zijn.
     */
    public List<Beheerder> selectActive(){
        ArrayList<Beheerder> beheerders = new ArrayList<>();
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM gebruiker WHERE isactief = true ORDER BY id;");
            ResultSet resultSet = preparedStatement.executeQuery();
            beheerders = fillListFromResultSet(resultSet, beheerders);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return beheerders;
    }

    /**
     * Deze methode zorgt ervoor dat de select statement wordt uitgevoerd.
     * welke ervoor zorgt dat alle bedrijfsgegevens worden opgehaald.
     */
    @Override
    public Beheerder select(int id) {
        Connection connection = createConnection();
        Beheerder beheerder = new Beheerder();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM gebruiker WHERE id = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            beheerder.setId(resultSet.getInt("id"));
            beheerder.setVoornaam(resultSet.getString("voornaam"));
            beheerder.setAchternaam(resultSet.getString("achternaam"));
            beheerder.setAdres(resultSet.getString("adres"));
            beheerder.setPostcode(resultSet.getString("postcode"));
            beheerder.setWoonplaats(resultSet.getString("woonplaats"));
            beheerder.setTelefoon(resultSet.getString("telefoon"));
            beheerder.setEmail(resultSet.getString("email"));
            beheerder.setWachtwoord(resultSet.getString("wachtwoord"));
            beheerder.setRechten_id(resultSet.getInt("rechten_id"));
            beheerder.setActief(resultSet.getBoolean("isactief"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return beheerder;
    }

    /**
     * Deze methode geeft een lijst met beheerders uit de resultset van de database terug.
     *
     * @param beheerders
     * @param resultSet
     */
    private ArrayList<Beheerder> fillListFromResultSet(ResultSet resultSet, ArrayList<Beheerder> beheerders) throws SQLException{

        while (resultSet.next()) {
            Beheerder beheerder = new Beheerder();

            beheerder.setId(resultSet.getInt("id"));
            beheerder.setVoornaam(resultSet.getString("voornaam"));
            beheerder.setAchternaam(resultSet.getString("achternaam"));
            beheerder.setAdres(resultSet.getString("adres"));
            beheerder.setPostcode(resultSet.getString("postcode"));
            beheerder.setWoonplaats(resultSet.getString("woonplaats"));
            beheerder.setTelefoon(resultSet.getString("telefoon"));
            beheerder.setEmail(resultSet.getString("email"));
            beheerder.setWachtwoord(resultSet.getString("wachtwoord"));
            beheerder.setRechten_id(resultSet.getInt("rechten_id"));
            beheerder.setActief(resultSet.getBoolean("isactief"));

            beheerders.add(beheerder);
        }
        return beheerders;
    }



}
