package main.Persistence;


import main.Model.Bedrijf;
import main.Model.Klant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Model.KlantBedrijf;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * De KlantBedrijfDOA is de DOA die wordt gebruikt voor het communiceren met de Klant_has_bedrijf Tabel.
 *
 * @author Mohammed El Baze
 * @version 1.0, November 2016
 */
public class KlantBedrijfDAO extends ConnectDAO<KlantBedrijf> {

    /**
     * Deze methode wordt niet gebruikt.
     */
    @Override
    public void update(KlantBedrijf kb) {
    }

    /**
     * Deze methode wordt niet gebruikt.
     */
    @Override
    public void delete(int id) {
    }

    /**
     * Deze methode wordt niet gebruikt.
     */
    @Override
    public Collection<KlantBedrijf> select() {
        return null;
    }

    /**
     * Deze methode wordt niet gebruikt.
     */
    @Override
    public KlantBedrijf select(int id) {
        return null;
    }

    /**
     * Deze methode is verantwoordelijk voor het verwijderen van een klant bedrijf relatie in de tabel.
     */
    public void delete(int klantId, int bedrijfId) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM klant_has_bedrijf Where klant_id =? AND " +
                    "bedrijf_id =?");
            preparedStatement.setInt(1, klantId);
            preparedStatement.setInt(2, bedrijfId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }

    /**
     * Deze methode is verantwoordelijk voor het toevoegen van een klant bedrijf relatie in de tabel
     */
    @Override
    public int insert(KlantBedrijf kb) {
        Connection connection = createConnection();
        int id = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO klant_has_bedrijf (klant_id,bedrijf_id) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, kb.getKlantId());
            preparedStatement.setInt(2, kb.getBedrijfId());
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
     * Deze methode is verantwoordelijk voor het op halen van alle bedrijven waar een klant werkzaam is.
     */
    public Collection<Bedrijf> selectWerkzameBedrijven(int klantId) {
        Connection connection = createConnection();
        ArrayList<Bedrijf> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM klant_has_bedrijf as kb " +
                    "join bedrijf as b on b.id = kb.bedrijf_id WHERE klant_id = ?");

            preparedStatement.setInt(1, klantId);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = fillBedrijfListFromResultSet(resultSet, list);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return list;
    }

    /**
     * Deze methode is verantwoordelijk voor het op halen van alle klanten die werkzaam zijn bij een bedrijf.
     */
    public ArrayList<Klant> selectWerkzameKlanten(int bedrijfID) {
        Connection connection = createConnection();
        ArrayList<Klant> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM klant_has_bedrijf as kb join klant as k " +
                    "on k.id = kb.klant_id WHERE bedrijf_id =?");

            preparedStatement.setInt(1, bedrijfID);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = fillKlantListFromResultSet(resultSet, list);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return list;
    }

    /**
     * Deze methode is verantwoordelijk voor het op halen van alle klanten die niet werkzaam zijn bij een bedrijf.
     */
    public ArrayList<Klant> selectOverigeKlanten(int bedrijfID) {
        Connection connection = createConnection();
        ArrayList<Klant> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM klant where id NOT IN " +
                    "(SELECT klant_id FROM klant_has_bedrijf WHERE  bedrijf_id = ?)");

            preparedStatement.setInt(1, bedrijfID);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = fillKlantListFromResultSet(resultSet, list);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return list;

    }

    /**
     * Deze methode is verantwoordelijk voor het op halen van alle bedrijven waar een klant niet werkzaam is.
     */
    public ArrayList<Bedrijf> selectOverigebedrijven(int klantID) {
        Connection connection = createConnection();
        ArrayList<Bedrijf> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bedrijf where id NOT IN " +
                    "(SELECT bedrijf_id FROM klant_has_bedrijf WHERE  klant_id = ?)");

            preparedStatement.setInt(1, klantID);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = fillBedrijfListFromResultSet(resultSet, list);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return list;

    }

    /**
     * Deze methode is verantwoordelijk voor het maken van de klant models gebaseerd op de resultset.
     * Deze models slaat hij op in de list.
     *
     * @param list      krijgt een  list mee om te vullen.
     * @param resultSet ResultSet om te gebruiken
     */
    private ArrayList<Klant> fillKlantListFromResultSet(ResultSet resultSet, ArrayList<Klant> list) throws SQLException {
        while (resultSet.next()) {
            Klant klant = new Klant();
            klant.setId(resultSet.getInt("id"));
            klant.setVoornaam(resultSet.getString("Voornaam"));
            klant.setAchternaam(resultSet.getString("Achternaam"));
            klant.setWoonplaats(resultSet.getString("Woonplaats"));
            klant.setAdres(resultSet.getString("Adres"));
            klant.setPostcode(resultSet.getString("Postcode"));
            klant.setGeboortedatum(resultSet.getDate("Geboortedatum"));
            klant.setTelefoon(resultSet.getString("Telefoon"));
            klant.setEmail(resultSet.getString("email"));
            klant.setLinkedin(resultSet.getString("LinkedIn"));
            list.add(klant);
        }
        return list;
    }

    /**
     * Deze methode is verantwoordelijk voor het maken van de bedrijf models gebaseerd op de resultset.
     * Deze models slaat hij op in de list.
     *
     * @param list      krijgt een list mee om te vullen.
     * @param resultSet ResultSet om te gebruiken
     */

    private ArrayList<Bedrijf> fillBedrijfListFromResultSet(ResultSet resultSet, ArrayList<Bedrijf> list) throws SQLException {

        while (resultSet.next()) {
            Bedrijf bedrijf = new Bedrijf();
            bedrijf.setId(resultSet.getInt("id"));
            bedrijf.setBedrijfsnaam(resultSet.getString("bedrijfsnaam"));
            bedrijf.setAdres(resultSet.getString("adres"));
            bedrijf.setPostcode(resultSet.getString("postcode"));
            bedrijf.setWebsite(resultSet.getString("website"));
            bedrijf.setPlaats(resultSet.getString("plaats"));
            bedrijf.setContactpersoon(resultSet.getString("contactpersoon"));
            bedrijf.setTelefoon(resultSet.getString("telefoon"));
            bedrijf.setEmail(resultSet.getString("email"));
            list.add(bedrijf);
        }
        return list;
    }

}
