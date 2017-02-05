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
 *
 */
@Singleton
public class BeheerderService {

    /**
     *
     */
    public BeheerderDAO dao;



    /**
     * @param dao
     */
    @Inject
    public BeheerderService(BeheerderDAO dao) {
        this.dao = dao;
    }

    /**
     *
     */
    public Collection<Beheerder> getAll() {
        return dao.select();

    }

    /**
     * @param id
     */
    public Beheerder get(int id) {
        return dao.select(id);

    }

    /**
     * @param beheerder
     */
    public int add(Beheerder beheerder) {
        return dao.insert(beheerder);
    }

    /**
     * @param id
     * @param beheerder
     */
    public void update(Beheerder authenticator, int id, Beheerder beheerder) {
        dao.update(beheerder);
    }

    /**
     * @param id
     */
    public void delete(int id, boolean isactief) {
        dao.deleteUndo(id, isactief);
    }


    public Beheerder me(Beheerder beheerder){

        GenerateHash(beheerder.getWachtwoord());

        for(Beheerder b : dao.selectActive()){

            if(b.getEmail().equals(beheerder.getEmail()) && b.getWachtwoord().equals(beheerder.getWachtwoord())){
                return b;
            }
        }
        return null;
    }
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