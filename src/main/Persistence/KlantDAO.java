package main.Persistence;

import com.google.inject.Singleton;
import main.Model.Klant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;


/**
 * Deze class wordt gebruikt met de database te communiceren.
 * Het is verantwoordelijk om query's uit te voeren.
 * Created by Mike,Shaban on 12-10-16.
 * @author Mike,Shaban, Mohamed El Baze
 * @version 0.1
 */
@Singleton
public class KlantDAO extends ConnectDAO<Klant> {

    /**
     * Deze methode zorgt ervoor dat de select wordt uitgevoerd om
     * alle klanten te selecteren.
     */
    @Override
    public List<Klant> select() {
        ArrayList<Klant> klantlist = new ArrayList<>();
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM klant");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Klant klant = new Klant();
                klant.setId(resultSet.getInt("id"));
                klant.setVoornaam(resultSet.getString("voornaam"));
                klant.setAchternaam(resultSet.getString("achternaam"));
                klant.setWoonplaats(resultSet.getString("woonplaats"));
                klant.setAdres(resultSet.getString("adres"));
                klant.setPostcode(resultSet.getString("postcode"));
                klant.setGeboortedatum(resultSet.getDate("geboortedatum"));
                klant.setTelefoon(resultSet.getString("telefoon"));
                klant.setLinkedin(resultSet.getString("linkedIn"));
                klant.setEmail(resultSet.getString("email"));
                klantlist.add(klant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return klantlist;
    }


    /**
     * Deze methode zorgt ervoor dat de select wordt uitgevoerd om
     * een enkele klant te selecteren.
     */
    @Override
    public Klant select(int id) {
        Klant klant = new Klant();
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM klant WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();


            klant.setId(resultSet.getInt("id"));
            klant.setVoornaam(resultSet.getString("voornaam"));
            klant.setAchternaam(resultSet.getString("achternaam"));
            klant.setWoonplaats(resultSet.getString("woonplaats"));
            klant.setAdres(resultSet.getString("adres"));
            klant.setPostcode(resultSet.getString("postcode"));
            klant.setGeboortedatum(resultSet.getDate("geboortedatum"));
            klant.setTelefoon(resultSet.getString("telefoon"));
            klant.setLinkedin(resultSet.getString("linkedIn"));
            klant.setEmail(resultSet.getString("email"));

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return klant;
    }

    /**
     * Deze methode zorgt ervoor dat de delete wordt uitgevoerd om
     * geselecteerde klant te verwijderen.
     */
    @Override
    public void delete(int id) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM klant WHERE id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }

    /**
     * Deze methode zorgt ervoor dat de delete wordt uitgevoerd om
     * geselecteerde klant te verwijderen.
     */
    @Override
    public void update(Klant klant) {

        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE klant SET voornaam = ?, achternaam = ?, adres " +
                    "= ?, postcode = ?, woonplaats = ?, geboortedatum = ?," +
                    "telefoon = ?, linkedin = ?,email = ?  WHERE id = ?");

            preparedStatement.setString(1, klant.getVoornaam());
            preparedStatement.setString(2, klant.getAchternaam());
            preparedStatement.setString(3, klant.getAdres());
            preparedStatement.setString(4, klant.getPostcode());
            preparedStatement.setString(5, klant.getWoonplaats());
            preparedStatement.setDate(6, klant.getGeboortedatum());
            preparedStatement.setString(7, klant.getTelefoon());
            preparedStatement.setString(8, klant.getLinkedin());
            preparedStatement.setString(9, klant.getEmail());
            preparedStatement.setInt(10, klant.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }

    /**
     * Deze methode zorgt ervoor dat de insert wordt uitgevoerd om
     * een klant toe te voegen.
     */
    @Override
    public int insert(Klant klant) {
        Connection connection = createConnection();
        int id = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO klant (voornaam,achternaam,adres," +
                    "postcode,woonplaats,geboortedatum,telefoon,linkedin,email) VALUES (?,?,?,?,?,?,?,?,?)", RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, klant.getVoornaam());
            preparedStatement.setString(2, klant.getAchternaam());
            preparedStatement.setString(3, klant.getAdres());
            preparedStatement.setString(4, klant.getPostcode());
            preparedStatement.setString(5, klant.getWoonplaats());
            preparedStatement.setDate(6, klant.getGeboortedatum());
            preparedStatement.setString(7, klant.getTelefoon());
            preparedStatement.setString(8, klant.getLinkedin());
            preparedStatement.setString(9, klant.getEmail());

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

}

