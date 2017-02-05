package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.Beheerder;
import main.Persistence.BeheerderDAO;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Dit is de  BeheerderService. Dit klasse is verantwoordlijk voor het communiceren met de DOA.
 * @author Murtaza Aydogdu
 * @version 1.0, Januari 2017
 */
@Singleton
public class BeheerderService {

    /**
     * Dit is een Object van BeheerderDAO. Dit is nodig om de communiceren met de database.
     */
    public BeheerderDAO dao;

    /**
     * Dit van BeheerderService
     * @param dao geinjecteerd in de class.
     */
    @Inject
    public BeheerderService(BeheerderDAO dao) {
        this.dao = dao;
    }

    /**
     * Methode bedoeldt om alle Beheerders uit de database op te halen.
     * @return collection van Beheerder.
     */
    public Collection<Beheerder> getAll() {
        return dao.select();

    }

    /**
     * Methode bedoelt voor het ophalen van de juiste Beheerder in de database.
     * @param id van beheerder.
     */
    public Beheerder get(int id) {
        return dao.select(id);

    }

    /**
     * Methode bedoelt voor het toevoegen van een Beheerder in de database.
     * @param beheerder
     */
    public int add(Beheerder beheerder) {
        return dao.insert(beheerder);
    }

    /**
     * Methode bedoelt voor het wijzigen van een Beheerder in de database.
     * @param id
     * @param beheerder
     */
    public void update(Beheerder authenticator, int id, Beheerder beheerder) {
        dao.update(beheerder);
    }

    /**
     * Methode bedoelt voor het verwijderen van een Beheerder in de database.
     * @param id
     */
    public void delete(int id, boolean isactief) {
        dao.deleteUndo(id, isactief);
    }

    /**
     * Methode bedoelt voor het ophalen van de juiste beheerdergegevens bij het inloggen.
     * @param beheerder
     * @return Beheerder
     */
    public Beheerder me(Beheerder beheerder){

        GenerateHash(beheerder.getWachtwoord());

        for(Beheerder b : dao.selectActive()){

            if(b.getEmail().equals(beheerder.getEmail()) && b.getWachtwoord().equals(beheerder.getWachtwoord())){
                return b;
            }
        }
        return null;
    }

    /**
     * Methode bedoelt dat bij het inloggen de wachtwoord wordt geencrypt.
     * @param input
     * @return geencrypte wachtwoord
     */
    public String GenerateHash(String input){

        MessageDigest objSHA = null;
        try {
            objSHA = MessageDigest.getInstance("SHA-512");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
            byte[] bytSHA = objSHA.digest(input.getBytes());
            BigInteger intNumber = new BigInteger(1, bytSHA);
            String strHashCode = intNumber.toString(16);

            while (strHashCode.length() < 128) {
                strHashCode = "0" + strHashCode;
            }
        return strHashCode;
    }

}