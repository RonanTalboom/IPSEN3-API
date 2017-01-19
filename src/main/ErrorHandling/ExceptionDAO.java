package main.ErrorHandling;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.soap.AddressingFeature;

/**
 * Created by ronantalboom on 15/12/2016.
 */
public class ExceptionDAO extends WebApplicationException {

    /**
     * Create a HTTP 404 (Not Found) exception.
     */
    public ExceptionDAO() {
        super(Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON_TYPE).build());
    }

    /**
     * Create a HTTP 404 (Not Found) exception.
     *
     * @param message the String that is the entity of the 404 response.
     */
    public ExceptionDAO(String message) {
        super(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity("DAOException : " + message)
                .build());
    }

}
