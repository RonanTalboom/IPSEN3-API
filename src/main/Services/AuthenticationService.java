/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.Services;

import main.Model.Beheerder;
import main.Model.Klant;
import main.Persistence.BeheerderDAO;
import main.Persistence.KlantDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.basic.BasicCredentials;


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

        userDAO.selectBeheerder();
        for(Beheerder b : userDAO.getBeheerders()){

            if(b.getEmail().equals(credentials.getUsername()) && b.getWachtwoord().equals(credentials.getPassword())){
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean authorize(Beheerder user, String roleName)
    {

        return true;
    }
}
