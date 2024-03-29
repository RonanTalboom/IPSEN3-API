package main.Services;

import main.Model.Klant;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author Peter van Vliet
 * @param <T>
 */
public class BaseService<T>
{
    public T requireResult(T model)
    {
        if (model == null)
        {
            throw new NotFoundException();
        }
        
        return model;
    }
    
    public void assertSelf(Klant klant1, Klant klant2)
    {
        if (!klant1.equals(klant2))
        {
            throw new ForbiddenException();
        }
    }
}
