package main.Persistence;

import com.google.inject.Singleton;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import javafx.collections.FXCollections;
import main.Model.Bedrijf;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Deze class wordt gebruikt met de database te praten. Het is verantwoordelijk voor het uitvoeren van queries(DLL)
 *
 * @author Mohamed El Baze
 * @version 0.1
 * @date 10/12/16
 */
@Singleton
public class BedrijfDAO extends ConnectDAO<Bedrijf> {


    /**
     * Deze methode zorgt ervoor dat de update statement wordt uitgevoerd
     * welke ervoor zorgt dat een bedrijf gewijzigd in de database.
     *
     * @param bedrijf
     */
    @Override
    public void update(Bedrijf bedrijf) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE bedrijf SET bedrijfsnaam=? ,adres=?" +
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
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);

    }

    /**
     * Deze methode zorgt ervoor dat de delete statement wordt uitgevoegd.
     * welke ervoor zorgt dat een geslecteerd bedrijf wordt verwijderd uit de database.
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE bedrijf  set isactief = FALSE  WHERE id =?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection( connection);
    }

    public void activeer(int id) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE bedrijf  set isactief = TRUE  WHERE id =?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection( connection);
    }

    /**
     * Deze methode zorgt ervoor dat de insert statement wordt uitgevoegd.
     * welke ervoor zorgt dat er een nieuwe bedrijf wordt toegevoegd aan de database.
     *
     * @param bedrijf
     */
    @Override
    public int insert(Bedrijf bedrijf) {
        Connection connection = createConnection();
        int id = -1;
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO bedrijf (bedrijfsnaam ,adres,postcode," +
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
                preparedStatement.executeUpdate();

                ResultSet rs = preparedStatement.getGeneratedKeys();
                rs.next();
                id = rs.getInt(1);
                rs.close();
            } catch (SQLException e) {
                e.getLocalizedMessage();
            }
        closeConnection( connection);
        return id;
    }

    /**
     * Deze methode zorgt ervoor dat de select statement wordt uitgevoerd.
     * welke ervoor zorgt dat alle bedrijfsgegevens worden opgehaald.
     */
    @Override
    public List<Bedrijf> select() {
        Connection connection = createConnection();
        ArrayList<Bedrijf> bedrijven = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bedrijf;");
            ResultSet resultSet = preparedStatement.executeQuery();
            bedrijven = fillListFromResultSet(resultSet, bedrijven);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return bedrijven;
    }

    /**
     * Deze methode zorgt ervoor dat de select statement wordt uitgevoerd.
     * welke ervoor zorgt dat alle bedrijfsgegevens worden opgehaald.
     */
    @Override
    public Bedrijf select(int id) {
        Connection connection = createConnection();
        Bedrijf bedrijf = new Bedrijf();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bedrijf WHERE id = ?;");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return bedrijf;
    }

    /**
     * Deze methode geeft een lijst met bedrijven uit de resultset van de database terug.
     *
     * @param bedrijven
     * @param resultSet
     */
    private ArrayList<Bedrijf> fillListFromResultSet(ResultSet resultSet, ArrayList<Bedrijf> bedrijven) throws SQLException{

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
            bedrijf.isIsactief(resultSet.getBoolean("isactief"));
            bedrijven.add(bedrijf);
        }
        return bedrijven;
    }

}
