package main.Persistence;

import com.google.inject.Singleton;
import javafx.collections.FXCollections;
import main.Model.Bedrijf;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Deze class wordt gebruikt met de database te praten. Het is verantwoordelijk voor het uitvoeren van queries(DLL)
 *
 * @author Mohamed El Baze
 * @version 0.1
 * @date 10/12/16
 */
@Singleton
public class BedrijfDAO extends ConnectDAO {

    /**
     * Hier wordt  een object van de Bedrijf model geinitieerd.
     * Zodat deze in een methode kan worden gebruikt
     */
    private Bedrijf bedrijf;
    /**
     * Hier wordt wordt een ObservableList van Bedrijf opgeslagen.
     * Deze bevat de bedrijven die worden opgehaald met de select methode.
     */
    private Collection<Bedrijf> observersBedrijven = FXCollections.observableArrayList();
    /**
     * Hier wordt error bericht opgeslagen zodat deze geprint kan worden..
     */
    private String errorBeschrijving;
    /**
     * Hier wordt opgeslagen hoeveel row zijn verandert bij het uitvoeren van de preparedstatement.
     */
    private int rows;

    /**
     * Deze methode zorgt ervoor dat de update statement wordt uitgevoerd
     * welke ervoor zorgt dat een bedrijf gewijzigd wordt.
     * @param id
     */
    @Override
    public void update(int id) {
        try {
            connectToDB();
            preparedStatement = connection.prepareStatement("UPDATE bedrijf SET bedrijfsnaam=? ,adres=?" +
                    " ,postcode=? ,website=? ,plaats=?,woonplaats=? ,contactpersoon=? ,telefoon=? ,email=? WHERE id=?");
            preparedStatement.setString(1, bedrijf.getBedrijfsnaam());
            preparedStatement.setString(2, bedrijf.getAdres());
            preparedStatement.setString(3, bedrijf.getPostcode());
            preparedStatement.setString(4, bedrijf.getWebsite());
            preparedStatement.setString(5, bedrijf.getPlaats());
            preparedStatement.setString(6, bedrijf.getWebsite());
            preparedStatement.setString(7, bedrijf.getContactpersoon());
            preparedStatement.setString(8, bedrijf.getTelefoon());
            preparedStatement.setString(9, bedrijf.getEmail());
            preparedStatement.setInt(10, bedrijf.getId());
            rows = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            errorBeschrijving = e.getLocalizedMessage();

        }

    }

    /**
     * Deze methode zorgt ervoor dat de delete statement wordt uitgevoegd.
     * welke ervoor zorgt dat een geslecteerd bedrijf wordt verwijderd.
     * @param id
     */
    @Override
    public void delete(int id) {
        try {
            connectToDB();
            preparedStatement = connection.prepareStatement("UPDATE bedrijf  set isactief = FALSE  WHERE id =?");
            preparedStatement.setInt(1,id);
            rows = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            errorBeschrijving = e.getLocalizedMessage();
        }
    }


    public void activeer(int id) {
        try {
            connectToDB();
            preparedStatement = connection.prepareStatement("UPDATE bedrijf  set isactief = TRUE  WHERE id =?");
            preparedStatement.setInt(1,id);
            rows = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            errorBeschrijving = e.getLocalizedMessage();
        }
    }

    /**
     * Deze methode zorgt ervoor dat de insert statement wordt uitgevoegd.
     * welke ervoor zorgt dat er een nieuwe bedrijf wordt toegevoegd.
     */
    @Override
    public void insert() {
        try {
            connectToDB();

            preparedStatement = connection.prepareStatement("INSERT INTO bedrijf (bedrijfsnaam ,adres,postcode," +
                    "website,plaats,woonplaats,contactpersoon,telefoon ,email) VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, bedrijf.getBedrijfsnaam());
            preparedStatement.setString(2, bedrijf.getAdres());
            preparedStatement.setString(3, bedrijf.getPostcode());
            preparedStatement.setString(4, bedrijf.getWebsite());
            preparedStatement.setString(5, bedrijf.getPlaats());
            preparedStatement.setString(6, bedrijf.getWebsite());
            preparedStatement.setString(7, bedrijf.getContactpersoon());
            preparedStatement.setString(8, bedrijf.getTelefoon());
            preparedStatement.setString(9, bedrijf.getEmail());
            rows = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            errorBeschrijving = e.getLocalizedMessage();
        }

    }

    /**
     * Deze methode zorgt ervoor dat de select statement wordt uitgevoerd.
     * welke ervoor zorgt dat alle bedrijfsgegevens worden opgehaald.
     */
    @Override
    public void select() {
        String query = "SELECT * FROM bedrijf";
        getBedrijfGegevens(query);
    }


    public void filterTag(String filter){
        String query = "select * from bedrijf as b\n" +
                "join bedrijf_has_tag as bt\n" +
                "on b.id = bt.bedrijf_id\n" +
                "join tag as t\n" +
                "on bt.tag_id = t.id WHERE naam = '"+filter+"' ";
        getBedrijfGegevens(query);
    }

    /**
     * Deze methode geeft een lijst met bedrijven van de datebase terug.
     * @param query
     */
    private void getBedrijfGegevens(String query) {
        observersBedrijven.clear();
        connectToDB();
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                bedrijf = new Bedrijf();
                bedrijf.setId(resultSet.getInt("id"));
                bedrijf.setBedrijfsnaam(resultSet.getString("bedrijfsnaam"));
                bedrijf.setAdres(resultSet.getString("adres"));
                bedrijf.setPostcode(resultSet.getString("postcode"));
                bedrijf.setWebsite(resultSet.getString("website"));
                bedrijf.setPlaats(resultSet.getString("plaats"));
                bedrijf.setContactpersoon(resultSet.getString("contactpersoon"));
                bedrijf.setTelefoon(resultSet.getString("telefoon"));
                bedrijf.setEmail(resultSet.getString("email"));
                bedrijf.isIsactief(resultSet.getBoolean("isactief"));
                observersBedrijven.add(bedrijf);
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deze methode geeft een error beschrijving met bedrijven van de datebase terug.
     * @return
     */
    public String getErrorBeschrijving() {
        return errorBeschrijving;
    }

    /**
     * Deze methode geeft de rij van bedrijven van de datebase terug.
     * @return
     */
    public int getRows() {
        return rows;
    }

    /**
     * Deze methode geeft de id met bedrijven van de datebase terug.
     * @return
     */
    public int getID(){
        String query = "SELECT id FROM bedrijf WHERE email ='"+ bedrijf.getEmail()+"'";
        return runIDstatement(query);
    }

    /**
     * Deze methode geeft een lijst met bedrijven van de datebase terug.
     * @return observersBedrijven
     */
    public Collection<Bedrijf> getObserversBedrijven() {
        return observersBedrijven;
    }

    /**
     * Deze methode geeft een geupdatet bedrijf mee aan een lijst.
     * @param bedrijf
     */
    public void setBedrijf(Bedrijf bedrijf) {
        this.bedrijf = bedrijf;
    }

}
