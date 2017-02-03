/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.Services;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.basic.BasicCredentials;
import main.Model.Beheerder;
import main.Persistence.BeheerderDAO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class AuthenticationService implements Authenticator<BasicCredentials, Beheerder>, Authorizer<Beheerder>
{
    private final BeheerderDAO userDAO;

    @Inject
    public AuthenticationService(BeheerderDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    /**
     * valideren moet nog een user van db hebben
     * @param credentials
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Optional<Beheerder> authenticate(BasicCredentials credentials) throws AuthenticationException
    {
        for(Beheerder b : userDAO.selectActive() ){

            if(b.getEmail().equals(credentials.getUsername()) && b.getWachtwoord().equals(credentials.getPassword())){
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }


    //change me...
    @Override
    public boolean authorize(Beheerder user, String roleName)
    {

        //check rechten van gebruiker
        String[] roles;
        if(user.getRechten_id() == 2){
            //is een beheerder
            roles = new String[] { "BEHEERDER", "GUEST" };

        }else if(user.getRechten_id() == 1){
            //is een admin
            roles = new String[] { "BEHEERDER", "ADMIN","GUEST" };

        }else{
            //niks
            roles = new String[] { "GUEST"};
        }
        //controleren of de gebruiker de correcte role heeft
        if (roles != null)
        {
            for(String role : roles)
            {
                if(roleName.equals(role))
                {
                    return true;
                }
            }
        }

        return false;
    }
}
