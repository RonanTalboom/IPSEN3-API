package main.Persistence;

import java.sql.*;
import java.util.Collection;

/**
 * Dit is een abstracte class, de dao klasses
 * extenden van deze klas.
 * Created by Mike,Shaban,Murtaza,Mohamed on 12-10-16.
 *
 * @author Mike, Shaban, Mohamed El Baze, Murtaza, Ruben van Til
 * @version 0.1
 */
public abstract class ConnectDAO<T> {
    /**
     * Hier wordt het IP  opgeslagen.
     */
    protected String ip = "145.97.16.190";
    /**
     * Hier wordt de poortnummer  opgeslagen.
     */
    protected String poortnummer = "5432";
    /**
     * Hier wordt de Dbnaam  opgeslagen.
     */
    protected String DbNaam = "IPSEN3G09";
//    IPSEN3G09
    /**
     * Hier wordt de DbGebruikersnaam  opgeslagen.
     */
    protected String DbGebruikersnaam = "IPSEN3G9";
    /**
     * Hier wordt de DbWachtwoord  opgeslagen.
     */
    protected String DbWachtwoord = "IPSEN3G9";

    /**
     * Deze methode zorgt ervoor dat de driver ingeladen wordt
     * Vervolgens wordt er een connectie gemaakt.
     *
     * @return connection
     */
    public Connection createConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + poortnummer + "/" +
                    DbNaam, DbGebruikersnaam, DbWachtwoord);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {

            e.printStackTrace();

        }
        return null;
    }

    /**
     * Deze methode zorgt ervoor dat de connectie afgesloten wordt
     *
     * @param connection
     */
    public void closeConnection( Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Subclasses worden gedwongen om deze methode te verkrijgen
     */
    public abstract void update(T t);

    /**
     * Subclasses worden gedwongen om deze methode te verkrijgen
     */
    public abstract void delete(int id);

    /**
     * Subclasses worden gedwongen om deze methode te verkrijgen
     */
    public abstract int insert(T t);

    /**
     * Subclasses worden gedwongen om deze methode te verkrijgen
     */
    public abstract Collection<T> select();
    /**
     * Subclasses worden gedwongen om deze methode te verkrijgen
     */
    public abstract T select(int id);
}
