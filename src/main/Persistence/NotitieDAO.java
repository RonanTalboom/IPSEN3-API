package main.Persistence;


import main.ErrorHandling.ExceptionDAO;
import main.Model.Bedrijf;
import main.Model.Beheerder;
import main.Model.Klant;
import main.Model.Notitie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


/**
 * De NotitieDAO is de DOA die wordt gebruikt voor het communiceren met de notitie Tabel.
 *
 * @author Shaban Jama, Mohammed EL Baze
 * @version 1.0, November 2016
 */
public class NotitieDAO extends ConnectDAO{

    /**
     * Hier wordt wordt een object van Notitie opgeslagen.
     * Deze bevat de notitiegegevens die wordt gebruikt in de statements
     */
    private Notitie notitie;

    /**
     * Hier wordt wordt een object van bedrijf opgeslagen.
     * Deze bevat de bedrijfgegevens die wordt gebruikt in de statements
     */
    private Bedrijf bedrijf = new Bedrijf();

    /**
     * Hier wordt wordt een object van klant opgeslagen.
     * Deze bevat de klantgegevens die wordt gebruikt in de statements
     */
    private Klant klant =  new Klant();

    private Beheerder gebruiker = new Beheerder();

    private Collection<Notitie>  notities = new ArrayList<Notitie>();

    /**
     * Hier wordt error bericht opgeslagen zodat deze geprint kan worden..
     */
    private String errorBeschrijving;

    /**
     * Hier wordt opgeslagen hoeveel row zijn verandert bij het uitvoeren van de preparedstatement.
     */
    private int rows;




    /**
     * Deze methode is verantwoordelijk voor het aanpassen van een notitie in de tabel.
     */
    /**
     *
     *
     * inplaats van id van de notitie de id die als param is meegenomen gebruiken
     * @param id
     */
    @Override
    public void update(int id) {
        try {
            connectToDB();
            preparedStatement = connection.prepareStatement("UPDATE notitie SET titel = ?," +
                    "beschrijving = ?, bedrijf_id = ?, klant_id = ?, gebruiker_id = ? " +
                    "WHERE id=?");
            preparedStatement.setString(1,notitie.getTitel());
            preparedStatement.setString(2,notitie.getBeschrijving());

            if (notitie.getBedrijfID() == 0){
                preparedStatement.setNull(4,java.sql.Types.INTEGER);
            }else{
                preparedStatement.setInt(3,notitie.getBedrijfID());
            }

            if((notitie.getKlantID() == 0)){
                preparedStatement.setNull(4,java.sql.Types.INTEGER);
            }else{
                preparedStatement.setInt(4,notitie.getKlantID());

            }
            preparedStatement.setInt(5,notitie.getGebruikerID());
            preparedStatement.setInt(6,id);
            rows = preparedStatement.executeUpdate();
            closeConnection();
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getLocalizedMessage());
        }
    }

    /**
     * Deze methode is verantwoordelijk voor het verwijderen van een notitie in de tabel.
     */
    @Override
    public void delete(int id) {
        try {
            connectToDB();
            preparedStatement = connection.prepareStatement("DELETE FROM notitie WHERE id = ?");
            preparedStatement.setInt(1,id);
            rows = preparedStatement.executeUpdate();
            closeConnection();
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getLocalizedMessage());
        }
    }

    /**
     * Deze methode is verantwoordelijk voor het toevoegen van een notitie in de tabel.
     */
    @Override
    public void insert() {
        try {
            connectToDB();
            preparedStatement = connection.prepareStatement("INSERT INTO notitie (titel,beschrijving,bedrijf_id," +
                    "klant_id,gebruiker_id) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1,notitie.getTitel());
            preparedStatement.setString(2,notitie.getBeschrijving());
            if (notitie.getBedrijfID() == 0){
                preparedStatement.setNull(4,java.sql.Types.INTEGER);
            }else{
                preparedStatement.setInt(3,notitie.getBedrijfID());
            }
            if((notitie.getKlantID() == 0)){
                preparedStatement.setNull(4,java.sql.Types.INTEGER);
            }else{
                preparedStatement.setInt(4,notitie.getKlantID());
            }
            preparedStatement.setInt(5,notitie.getGebruikerID());
            rows = preparedStatement.executeUpdate();
            closeConnection();
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getLocalizedMessage());
        }
    }

    /**
     * Deze methode is verantwoordelijk voor het uitlezen van een de notities in de tabel van een specifieke klant.
     */
    public void filterKlantNotitie(){

        try {
            connectToDB();
            preparedStatement = connection.prepareStatement("SELECT notitie.*, klant.achternaam,klant.voornaam, " +
                    "bedrijf.bedrijfsnaam,bedrijf.woonplaats FROM notitie JOIN klant ON notitie.klant_id = klant.id JOIN bedrijf " +
                    "on notitie.bedrijf_id = bedrijf.id WHERE klant_id = ?");
            preparedStatement.setInt(1,klant.getId());
            resultSet = preparedStatement.executeQuery();
//            vulObserversNotities(resultSet);
            connection.close();
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getLocalizedMessage());
        }
    }

    /**
     Deze methode is verantwoordelijk voor het uitlezen van een de notities in de tabel van een specifieke bedrijf.
     */
    public void filterBedrijfNotitie(){

        try {
            connectToDB();
            preparedStatement = connection.prepareStatement("SELECT notitie.*, klant.achternaam,klant.voornaam, " +
                    "bedrijf.bedrijfsnaam,bedrijf.woonplaats FROM notitie JOIN klant ON notitie.klant_id = klant.id JOIN bedrijf " +
                    "on notitie.bedrijf_id = bedrijf.id WHERE bedrijf_id = ?");
            preparedStatement.setInt(1,klant.getId());
            resultSet = preparedStatement.executeQuery();
//            vulObserversNotities(resultSet);
            connection.close();
        } catch (SQLException e) {
            throw new ExceptionDAO(e.getLocalizedMessage());
        }
    }
    /**
     * Deze methode is verantwoordelijk voor het uitlezen van alle notities in de tabel.
     */
    @Override
    public void select() {

//        try {
//            connectToDB();
            String query ="SELECT notitie.*, klant.achternaam AS \"klantAchternaam\", klant.voornaam AS \"klantVoornaam\"," +
                    "bedrijf.bedrijfsnaam," +
                    " gebruiker.voornaam as \"gebruikerVoornaam\", gebruiker.achternaam AS \"gebruikerAchternaam\" " +
                    "FROM notitie " +
                    "LEFT JOIN klant ON notitie.klant_id = klant.id " +
                    "LEFT JOIN bedrijf ON notitie.bedrijf_id = bedrijf.id " +
                    "LEFT JOIN gebruiker ON notitie.gebruiker_id = gebruiker.id ";

//            preparedStatement = connection.prepareStatement("SELECT * FROM notitie");

//            resultSet = preparedStatement.executeQuery();
            vulObserversNotities(query);
//            connection.close();
//        } catch (SQLException e) {
//            throw new ExceptionDAO(e.getLocalizedMessage());
//        }

    }

    /**
     * Deze methode is verantwoordelijk voor het maken van de notities models gebaseerd op de resultset.
     * de models worden opgeslagen in de observable list.
//     * @param resultSet
     * @throws SQLException
     */
    public void vulObserversNotities(String query)  {
        notities.clear();
        connectToDB();
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                klant = new Klant();
                bedrijf = new Bedrijf();
                gebruiker = new Beheerder();
                notitie = new Notitie();
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
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Deze methode is verantwoordelijk voor het ophalen van Klanten gebaseerd op de resultset.
     * de models worden opgeslagen in de observable list.
     * @param searchString
     * @return
     */
//    public ObservableList<Klant> getSuggestionsKlant(String searchString) {
//        connectToDB();
//        String query ="SELECT * FROM klant  WHERE voornaam LIKE '%"+searchString+"%'";
//
//        try {
//            resultSet = statement.executeQuery(query);
//            while (resultSet.next()){
//                klant = new Klant();
//                klant.setId(resultSet.getInt("id"));
//                klant.setVoornaam(resultSet.getString("voornaam"));
//                klant.setAchternaam(resultSet.getString("achternaam"));
//
//            }
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return klantenLijst;
//    }
    /**
     * Deze methode is verantwoordelijk voor het ophalen van bedrijven gebaseerd op de resultset.
     * de models worden opgeslagen in de observable list.
     * @param searchString
     * @return
     */
//    public ObservableList<Bedrijf> getSuggestionsBedrijf(String searchString) {
//        connectToDB();
//        String query ="select * from bedrijf  where bedrijfsnaam LIKE '"+searchString+"%'";
//        try {
//            resultSet = statement.executeQuery(query);
//            bedrijvenLijst.clear();
//            while(resultSet.next()){
//                bedrijf = new Bedrijf();
//                bedrijf.setId(resultSet.getInt("id"));
//                bedrijf.setBedrijfsnaam(resultSet.getString("bedrijfsnaam"));
//                bedrijf.setAdres(resultSet.getString("woonplaats"));
//                bedrijvenLijst.addAll(bedrijf);
//            }
//            connection.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return bedrijvenLijst;
//    }

//    /**
//     * Zodra deze methode wordt aangeroepen geeft het de observable list van alle notities
//     */
//    public ObservableList<Notitie> getObserversNotities() {
//        return observersNotities;
//    }

    /**
     * Zodra deze methode wordt aangeroepen wordt de notitie geset.
     * @param notitie
     */
    public void setNotitie(Notitie notitie) {
        this.notitie = notitie;
    }

    /**
     * Zodra deze methode wordt aangeroepen wordt de klant geset.
     * @param klant
     */
    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    /**
     * Zodra deze methode wordt aangeroepen wordt de bedrijf geset.
     * @param bedrijf
     */
    public void setBedrijf(Bedrijf bedrijf) {
        this.bedrijf = bedrijf;
    }

    /**
     * Deze methode zorgt ervoor dat zodra deze wordt aangeroepen die de notitie returnd.
     */
    public Notitie getNotitie() {
        return notitie;
    }

    /**
     * Deze methode zorgt ervoor dat zodra deze wordt aangeroepen die de errorBeschrijving returnd.
     */
    public String getErrorBeschrijving() {
        return errorBeschrijving;
    }

    /**
     * Deze methode zorgt ervoor dat zodra deze wordt aangeroepen die de rows returnd.
     */
    public int getRows() {
        return rows;
    }

    public Collection<Notitie> getNotities() {
        return notities;
    }

}
