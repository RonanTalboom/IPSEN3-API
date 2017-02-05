package main.Persistence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Model.Bestand;
import main.Model.Klant;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * De KlantFileDAO is de DAO voor het opslaan en ophalen van bestanden uit database.
 * Created by drynl on 3-11-2016.
 * @author drynl, Mohammed El Baze
 * @version 0.1
 */
public class KlantFileDAO extends ConnectDAO<Bestand> {

    /**
     * Deze methode wordt niet gebruikt
     */
    @Override
    public void update(Bestand bestand) {}

    /**
     * Deze methode wordt niet gebruikt
     */
    @Override
    public Collection<Bestand> select() {
        return null;
    }

    /**
     * Deze methode wordt niet gebruikt
     */
    @Override
    public Bestand select(int id) {
        return null;
    }

    /**
     * Deze methode zorgt ervoor dat de delete wordt uitgevoerd om
     * geselecteerde bestand te verwijderen.
     */
    @Override
    public void delete(int id) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM klant_has_file WHERE id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }

    /**
     * Deze methode zorgt ervoor dat de insert wordt uitgevoerd om
     * een bestand toe te voegen.
     */
    @Override
    public int insert(Bestand bestand) {
        Connection connection = createConnection();
        byte[] bytes =bestand.getBase64().getBytes();
        int id = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO klant_has_file (klant_id,filename,file) VALUES (?,?,?)", RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, bestand.getKlant_Id());
            preparedStatement.setString(2, bestand.getFileName());
            preparedStatement.setBytes(3, bytes);
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

        /**
         * Deze methode zorgt ervoor dat de select wordt uitgevoerd om
         * alle bestanden te selecteren.
         */
        public List<Bestand> selectByKlant (int klantId) {
            Connection connection = createConnection();
            ArrayList<Bestand> bestanden = new ArrayList<>();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM klant_has_file WHERE klant_id = ?");
                preparedStatement.setInt(1, klantId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Bestand bestand = new Bestand();
                    bestand.setBestand_Id(resultSet.getInt("id"));
                    bestand.setKlant_Id(resultSet.getInt("klant_id"));
                    bestand.setFileName(resultSet.getString("filename"));
                    byte[] bytes = resultSet.getBytes("file");
                    bestand.setBase64(new String(bytes));
                    bestanden.add(bestand);

                }
//                preparedStatement.execute(); ??
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection(connection);
            return bestanden;
        }

}
