package main.Persistence;


import main.ErrorHandling.ExceptionDAO;
import main.Model.Bedrijf;
import main.Model.Beheerder;
import main.Model.Klant;
import main.Model.Notitie;

import javax.management.Notification;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * De NotitieDAO is de DOA die wordt gebruikt voor het communiceren met de notitie Tabel.
 *
 * @author Shaban Jama, Mohammed EL Baze, Ronan Talboom
 * @version 1.0, Februari 2017
 */
public class NotitieDAO extends ConnectDAO<Notitie>{
    /**
     * Deze methode is verantwoordelijk voor het aanpassen van een notitie in de tabel.
     *
     * @param notitie
     */
    @Override
    public void update(Notitie notitie) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE notitie SET titel = ?," +
                    "beschrijving = ?, bedrijf_id = ?, klant_id = ?, gebruiker_id = ? " +
                    "WHERE id=?");
            preparedStatement.setString(1,notitie.getTitel());
            preparedStatement.setString(2,notitie.getBeschrijving());

            if (notitie.getBedrijfID() == 0){
                preparedStatement.setNull(3,java.sql.Types.INTEGER);
            }else{
                preparedStatement.setInt(3,notitie.getBedrijfID());
            }

            if((notitie.getKlantID() == 0)){
                preparedStatement.setNull(4,java.sql.Types.INTEGER);
            }else{
                preparedStatement.setInt(4,notitie.getKlantID());

            }
            if(notitie.getGebruikerID() == 0){
                preparedStatement.setNull(5,java.sql.Types.INTEGER);
            }else{
                preparedStatement.setInt(5,notitie.getGebruikerID());
            }
            preparedStatement.setInt(6,notitie.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getLocalizedMessage());
        }
        closeConnection(connection);
    }


     /**
     *Deze methode is verantwoordelijk voor het verwijderen van een notitie in de tabel.
     * @param id
     */
    @Override
    public void delete(int id) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM notitie WHERE id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getLocalizedMessage());
        }
        closeConnection(connection);
    }

    /**
     * Deze methode is verantwoordelijk voor het toevoegen van een notitie in de tabel.
     * @param notitie
     */
    @Override
    public int insert(Notitie notitie) {
        Connection connection = createConnection();
        int id = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO notitie (titel,beschrijving,bedrijf_id," +
                    "klant_id,gebruiker_id) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,notitie.getTitel());
            preparedStatement.setString(2,notitie.getBeschrijving());
            if (notitie.getBedrijfID() == 0){
                preparedStatement.setNull(3,java.sql.Types.INTEGER);
            }else{
                preparedStatement.setInt(3,notitie.getBedrijfID());
            }
            if(notitie.getKlantID() == 0){
                preparedStatement.setNull(4,java.sql.Types.INTEGER);
            }else{
                preparedStatement.setInt(4,notitie.getKlantID());
            }
            if(notitie.getGebruikerID() == 0){
                preparedStatement.setNull(5,java.sql.Types.INTEGER);
            }else{
                preparedStatement.setInt(5,notitie.getGebruikerID());
            }

            preparedStatement.executeUpdate();


            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            rs.close();

        } catch (SQLException e) {
            throw new ExceptionDAO(e.getLocalizedMessage());
        }
        closeConnection(connection);
        return id;
    }




    /**
     * Deze methode is verantwoordelijk voor het uitlezen van alle notities in de tabel.
     * @return notities
     */
    @Override
    public List<Notitie> select() {
        Connection connection = createConnection();
        ArrayList<Notitie> notities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT notitie.*, klant.achternaam AS \"klantAchternaam\", klant.voornaam AS \"klantVoornaam\"," +
                    "bedrijf.bedrijfsnaam," +
                    " gebruiker.voornaam as \"gebruikerVoornaam\", gebruiker.achternaam AS \"gebruikerAchternaam\" " +
                    "FROM notitie " +
                    "LEFT JOIN klant ON notitie.klant_id = klant.id " +
                    "LEFT JOIN bedrijf ON notitie.bedrijf_id = bedrijf.id " +
                    "LEFT JOIN gebruiker ON notitie.gebruiker_id = gebruiker.id ");
            ResultSet resultSet = preparedStatement.executeQuery();
            notities = fillListFromResultSet(resultSet, notities);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return notities;

    }

    /**
     * Deze methode is verantwoordelijk voor het ophalen van een enkele notitie
     * @param id
     * @return notitie
     */
    @Override
    public Notitie select(int id) {
        Connection connection = createConnection();
        Notitie notitie = new Notitie();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM notitie WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Klant klant = new Klant();
            Bedrijf bedrijf = new Bedrijf();
            Beheerder gebruiker = new Beheerder();
            notitie.setId(resultSet.getInt("id"));
            notitie.setTitel(resultSet.getString("titel"));
            notitie.setBeschrijving(resultSet.getString("beschrijving"));
            notitie.setDatum(resultSet.getDate("datum"));
            notitie.setBedrijfID(resultSet.getInt("bedrijf_id"));
            notitie.setKlantID(resultSet.getInt("klant_id"));
            notitie.setGebruikerID(resultSet.getInt("gebruiker_id"));
            klant.setVoornaam(resultSet.getString("klantVoornaam"));
            klant.setAchternaam(resultSet.getString("klantAchternaam"));
            bedrijf.setBedrijfsnaam(resultSet.getString("bedrijfsnaam"));
            gebruiker.setVoornaam(resultSet.getString("gebruikerVoornaam"));
            gebruiker.setAchternaam(resultSet.getString("gebruikerAchternaam"));
            notitie.setKlantNaam(klant);
            notitie.setBedrijfNaam(bedrijf);
            notitie.setGebruikerNaam(gebruiker);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return notitie;

    }

    /**
     *  Deze methode is verantwoordelijk voor het ophalen van notities die bij een klant horen.
     * @param klantId
     * @return notities
     */
    public List<Notitie> selectbyKlant(int klantId) {
        Connection connection = createConnection();
        ArrayList<Notitie> notities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT notitie.*, klant.achternaam AS \"klantAchternaam\", klant.voornaam AS \"klantVoornaam\", "+
                    "bedrijf.bedrijfsnaam, " +
                    "gebruiker.voornaam as \"gebruikerVoornaam\", gebruiker.achternaam AS \"gebruikerAchternaam\" " +
                    "FROM notitie " +
                    "LEFT JOIN klant ON notitie.klant_id = klant.id " +
                    "LEFT JOIN bedrijf ON notitie.bedrijf_id = bedrijf.id " +
                    "LEFT JOIN gebruiker ON notitie.gebruiker_id = gebruiker.id WHERE klant_id = ? ORDER BY datum DESC");
            preparedStatement.setInt(1,klantId);
            ResultSet resultSet = preparedStatement.executeQuery();
            notities = fillListFromResultSet(resultSet, notities);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return notities;

    }


    /**
     * Deze methode is verantwoordelijk voor het uitlezen van een de notities in de tabel van een specifieke bedrijf.

     * @param bedrijfId
     * @return notities
     */
    public List<Notitie> selectbyBedrijf(int bedrijfId) {
        Connection connection = createConnection();
        ArrayList<Notitie> notities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT notitie.*, klant.achternaam AS \"klantAchternaam\", klant.voornaam AS \"klantVoornaam\", "+
                    "bedrijf.bedrijfsnaam, " +
                    "gebruiker.voornaam as \"gebruikerVoornaam\", gebruiker.achternaam AS \"gebruikerAchternaam\" " +
                    "FROM notitie " +
                    "LEFT JOIN klant ON notitie.klant_id = klant.id " +
                    "LEFT JOIN bedrijf ON notitie.bedrijf_id = bedrijf.id " +
                    "LEFT JOIN gebruiker ON notitie.gebruiker_id = gebruiker.id WHERE bedrijf_id = ? ORDER BY datum DESC");
            preparedStatement.setInt(1,bedrijfId);
            ResultSet resultSet = preparedStatement.executeQuery();
            notities = fillListFromResultSet(resultSet, notities);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return notities;

    }



    /**
     * Deze methode is verantwoordelijk voor het maken van de notities models gebaseerd op de resultset.
     * de models worden opgeslagen in de observable list.
     * @param resultSet
     * @param notities
     * @throws SQLException
     */

    private ArrayList<Notitie> fillListFromResultSet(ResultSet resultSet, ArrayList<Notitie> notities) throws SQLException{

        while (resultSet.next()) {
            Klant klant = new Klant();
            Bedrijf bedrijf = new Bedrijf();
            Beheerder gebruiker = new Beheerder();
            Notitie notitie = new Notitie();
            notitie.setId(resultSet.getInt("id"));
            notitie.setTitel(resultSet.getString("titel"));
            notitie.setBeschrijving(resultSet.getString("beschrijving"));
            notitie.setDatum(resultSet.getDate("datum"));
            notitie.setBedrijfID(resultSet.getInt("bedrijf_id"));
            notitie.setKlantID(resultSet.getInt("klant_id"));
            notitie.setGebruikerID(resultSet.getInt("gebruiker_id"));
            klant.setVoornaam(resultSet.getString("klantVoornaam"));
            klant.setAchternaam(resultSet.getString("klantAchternaam"));
            bedrijf.setBedrijfsnaam(resultSet.getString("bedrijfsnaam"));
            gebruiker.setVoornaam(resultSet.getString("gebruikerVoornaam"));
            gebruiker.setAchternaam(resultSet.getString("gebruikerAchternaam"));
            notitie.setKlantNaam(klant);
            notitie.setBedrijfNaam(bedrijf);
            notitie.setGebruikerNaam(gebruiker);
            notities.add(notitie);
        }
        return notities;
    }

}
