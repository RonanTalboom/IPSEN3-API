package main.Persistence;

import main.Model.KlantTag;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * De KlantTagDOA is de DOA die wordt gebruikt voor het communiceren met de Klant_has_tag Tabel.
 *
 * @author Shaban Jama
 * @version 1.0, November 2016
 */
public class KlantTagDAO extends ConnectDAO<KlantTag>{

    /**
     * Deze methode wordt niet gebruikt.
     */
    @Override
    public void update(KlantTag klantTag) {

    }

    /**
     * Deze methode wordt niet gebruikt.
     */
    @Override
    public KlantTag select(int id) {
        return null;
    }

    /**
     * Deze methode wordt niet gebruikt.
     */
    @Override
    public Collection<KlantTag> select() {
        return null;
    }

    /**
     * Deze methode is verantwoordelijk voor het verwijderen van een klant en tags relatie in de tabel.
     */
    @Override
    public void delete(int klantId) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM klant_has_tag Where klant_id =?");
            preparedStatement.setInt(1,klantId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }

    /**
     * Deze methode is verantwoordelijk voor het toevoegen van een klant tags relatie in de tabel
     */
    @Override
    public int insert(KlantTag klantTag) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO klant_has_tag (tag_id,klant_id) VALUES (?,?)");
            preparedStatement.setInt(1, klantTag.getTagId());
            preparedStatement.setInt(2,klantTag.getKlantId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return 0;
    }

    /**
     * Deze methode is verantwoordelijk voor het op halen van alle tags waar een klant aan gekoppelt is.
     */
    public List<KlantTag> selectByKlant(int klantID) {
        Connection connection = createConnection();
        ArrayList<KlantTag> klantTags = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT tag_id FROM klant_has_tag Where klant_id = ?");
            preparedStatement.setInt(1,klantID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                KlantTag klantTag = new KlantTag();
                klantTag.setKlantId(klantID);
                klantTag.setTagId(resultSet.getInt("tag_id"));

                klantTags.add(klantTag);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return klantTags;
    }

}
