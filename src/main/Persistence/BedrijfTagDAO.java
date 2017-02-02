package main.Persistence;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Model.Bedrijf;

import java.sql.SQLException;

/**
 * De BedrijfTagDOA is de DOA die wordt gebruikt voor het communiceren met de Bedrijf_has_tag Tabel.
 *
 * @author Shaban Jama, Mohamed El Baze
 * @version 1.0, November 2016
 */
public class BedrijfTagDAO extends ConnectDAO {


    /**
     * Hier wordt het bedrijfID opgeslagen voor het toevoegen van tags
     */
    private int bedrijfID;

    /**
     * Hier wordt opgeslagen welke tag wordt toegevoegd
     */
    private int tagID;


    private ObservableList<Integer> tagIDs = FXCollections.observableArrayList();

    /**
     * Deze methode wordt niet gebruikt.
     */
    @Override
    public void update(int id) {
    }

    /**
     * Deze methode is verantwoordelijk voor het verwijderen van een bedrijf en tags relatie in de tabel.
     */
    @Override
    public void delete(int id) {
        try {
            connectToDB();
            preparedStatement = connection.prepareStatement("DELETE FROM bedrijf_has_tag Where bedrijf_id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deze methode is verantwoordelijk voor het toevoegen van een bedrijf tags relatie in de tabel
     */
    @Override
    public void insert() {
        try {
            connectToDB();
            preparedStatement = connection.prepareStatement("INSERT INTO bedrijf_has_tag (tag_id,bedrijf_id) VALUES (?,?)");
            preparedStatement.setInt(1,tagID);
            preparedStatement.setInt(2,bedrijfID);
            preparedStatement.executeUpdate();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deze methode is verantwoordelijk voor het op halen van alle tags waar een bedrijf aan gekoppelt is.
     */
    @Override
    public void select() {
        tagIDs.clear();
        try {
            connectToDB();
            preparedStatement = connection.prepareStatement("SELECT tag_id FROM bedrijf_has_tag Where bedrijf_id = ?");
            preparedStatement.setInt(1,bedrijfID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                tagIDs.add(resultSet.getInt("tag_id"));
            }
            resultSet.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Zodra deze methode wordt aangeroepen wordt de bedrijfID geset.
     * @param bedrijfID
     */
    public void setBedrijfID(int bedrijfID) {
        this.bedrijfID = bedrijfID;
    }

    /**
     * Zodra deze methode wordt aangeroepen wordt de tagID geset.
     * @param tagID
     */
    public void setTagID(int tagID) {
        this.tagID = tagID;
    }


    public ObservableList<Integer> getTagIDs() {
        return tagIDs;
    }

}
