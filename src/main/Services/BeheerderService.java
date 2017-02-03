package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.Beheerder;
import main.Persistence.BeheerderDAO;

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
    public void delete(int id) {
        dao.delete(id);
    }


    public Beheerder me(Beheerder beheerder){

        for(Beheerder b : dao.selectActive()){

            if(b.getEmail().equals(beheerder.getEmail()) && b.getWachtwoord().equals(beheerder.getWachtwoord())){
                return b;
            }
        }
        return null;
    }

}