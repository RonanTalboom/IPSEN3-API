package main.Persistence;

import main.Model.BedrijfTag;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * De BedrijfTagDOA is de DOA die wordt gebruikt voor het communiceren met de Bedrijf_has_tag Tabel.
 *
 * @author Shaban Jama
 * @version 1.0, November 2016
 */
public class BedrijfTagDAO extends ConnectDAO<BedrijfTag>{

    /**
     * Deze methode wordt niet gebruikt.
     */
    @Override
    public void update(BedrijfTag bedrijfTag) {

    }

    /**
     * Deze methode wordt niet gebruikt.
     */
    @Override
    public BedrijfTag select(int id) {
        return null;
    }

    /**
     * Deze methode wordt niet gebruikt.
     */
    @Override
    public Collection<BedrijfTag> select() {
        return null;
    }

    /**
     * Deze methode is verantwoordelijk voor het verwijderen van een bedrijf en tags relatie in de tabel.
     */
    @Override
    public void delete(int bedrijfId) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM bedrijf_has_tag Where bedrijf_id =?");
            preparedStatement.setInt(1,bedrijfId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }

    /**
     * Deze methode is verantwoordelijk voor het toevoegen van een bedrijf tags relatie in de tabel
     */
    @Override
    public int insert(BedrijfTag bedrijfTag) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO bedrijf_has_tag (tag_id,bedrijf_id) VALUES (?,?)");
            preparedStatement.setInt(1, bedrijfTag.getTagId());
            preparedStatement.setInt(2,bedrijfTag.getBedrijfId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return 0;
    }

    /**
     * Deze methode is verantwoordelijk voor het op halen van alle tags waar een bedrijf aan gekoppelt is.
     */
    public List<BedrijfTag> selectByBedrijf(int bedrijfID) {
        Connection connection = createConnection();
        ArrayList<BedrijfTag> bedrijfTags = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT tag_id FROM bedrijf_has_tag Where bedrijf_id = ?");
            preparedStatement.setInt(1,bedrijfID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                BedrijfTag bedrijfTag = new BedrijfTag();
                bedrijfTag.setBedrijfId(bedrijfID);
                bedrijfTag.setTagId(resultSet.getInt("tag_id"));

                bedrijfTags.add(bedrijfTag);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return bedrijfTags;
    }

}
