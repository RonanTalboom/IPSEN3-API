/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.Services;

import main.Model.Klant;
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
public class AuthenticationService implements Authenticator<BasicCredentials, Klant>, Authorizer<Klant>
{
    private final KlantDAO userDAO;

    @Inject
    public AuthenticationService(KlantDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<Klant> authenticate(BasicCredentials credentials) throws AuthenticationException
    {

        userDAO.select();
        for(Klant k : userDAO.getKlantlist()){
            if(k.getEmail().equals(credentials.getPassword()) && k.getEmail().equals(credentials.getUsername())){
                return Optional.of(k);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean authorize(Klant user, String roleName)
    {
        if(roleName.equals("GUEST")){
            return true;

        }else{
            return false;
        }
    }
}
