package main.Persistence;


import main.Model.Tag;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * De TagDAO is de DAO die wordt gebruikt voor het communiceren met de tag Tabel.
 *
 * @author Shaban Jama
 * @version 1.0, November 2016
 */
public class TagDAO extends ConnectDAO<Tag> {


    /**
     * Deze methode is verantwoordelijk voor het aanpassen van een tag in de tabel.
     */
    @Override
    public void update(Tag tag) {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tag SET naam = ?,beschrijving = ? WHERE id = ?");
            preparedStatement.setString(1, tag.getNaam());
            preparedStatement.setString(2, tag.getBeschrijving());
            preparedStatement.setInt(3, tag.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        closeConnection(connection);
    }

    /**
     * Deze methode is verantwoordelijk voor het verwijderen van een tag in de tabel.
     */
    @Override
    public void delete(int id) {
        Connection connection = createConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tag WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
    }

    /**
     * Deze methode is verantwoordelijk voor het toevoegen van een tag in de tabel.
     */
    @Override
    public int insert(Tag tag) {
        Connection connection = createConnection();
        int id = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tag (naam,beschrijving) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tag.getNaam());
            preparedStatement.setString(2, tag.getBeschrijving());
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
     * Deze methode is verantwoordelijk voor het uitlezen van een de tags in de tabel.
     * hiervan worden tag models gemaakt en opgeslagen in de observable list.
     */
    @Override
    public List<Tag> select() {
        ArrayList<Tag> tags = new ArrayList<>();
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tag");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Tag tag = new Tag();
                tag.setId(resultSet.getInt("id"));
                tag.setNaam(resultSet.getString("naam"));
                tag.setBeschrijving(resultSet.getString("beschrijving"));
                tags.add(tag);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return tags;
    }

    /**
     * Deze methode is verantwoordelijk voor het uitlezen van een de tags in de tabel.
     * Met het meegegeven id wordt een model gemaakt.
     *
     * @param id
     */
    @Override
    public Tag select(int id) {
        Tag tag = new Tag();
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tag WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            tag.setId(resultSet.getInt("id"));
            tag.setNaam(resultSet.getString("naam"));
            tag.setBeschrijving(resultSet.getString("beschrijving"));
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(connection);
        return tag;
    }
}
