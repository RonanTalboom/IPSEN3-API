package Beheerder;

import static org.junit.Assert.assertEquals;

import com.google.common.net.MediaType;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.tools.internal.ws.processor.model.Response;
import org.junit.Test;
import org.junit.runner.Request;

/**
 * Created by ronantalboom on 08/12/2016.
 */
public class BeheerderTest {
    @Test
    public void EvalueerBeheerderToevoegen()

    {
        try {
            HttpResponse<String> response = Unirest.post("http://localhost:8080/api/beheerders")
                    .header("content-type", "application/json")
                    .header("authorization", "Basic cm9uYW5AaG90bWFpbCxjb206cm9uYW4=")
                    .header("cache-control", "no-cache")
                    .header("postman-token", "91227bbb-ddde-bd2b-c052-d2888d77a2e5")
                    .body("{\n  \"voornaam\": \"Ronan2\",\n  \"achternaam\": \"Talboom2\",\n  \"adres\": \"Noorder E\",\n  \"postcode\": \"2012GB\",\n  \"woonplaats\": \"Haarlem\",\n  \"telefoon\": \"0612345678\",\n  \"email\": \"ronan2@hotmail.com\",\n  \"wachtwoord\": \"ronan\",\n  \"rechten_id\": 1,\n  \"name\": \"n\",\n  \"actief\": true\n}")
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void EvalueerBeheerderAanpassen() throws UnirestException {
        HttpResponse<String> response = Unirest.put("http://localhost:8080/api/beheerders/3")
                .header("content-type", "application/json")
                .header("authorization", "Basic cm9uYW5AaG90bWFpbCxjb206cm9uYW4=")
                .header("cache-control", "no-cache")
                .header("postman-token", "a4f158bd-6dd6-df0d-b3be-8c8cef35c84c")
                .body("{\n  \"voornaam\": \"Ronan2\",\n  \"achternaam\": \"Talboom\",\n  \"adres\": \"Noorder E\",\n  \"postcode\": \"2012GB\",\n  \"woonplaats\": \"Haarlem\",\n  \"telefoon\": \"0612345678\",\n  \"email\": \"ronan@hotmail.com\",\n  \"wachtwoord\": \"ronan\",\n  \"rechten_id\": 1,\n  \"actief\": true\n}")
                .asString();

    }

    @Test
    public void EvalueerBeheerderVerwijderen() throws UnirestException{
        HttpResponse<String> response = Unirest.delete("http://localhost:8080/api/beheerders/3")
                .header("authorization", "Basic cm9uYW5AaG90bWFpbCxjb206cm9uYW4=")
                .header("cache-control", "no-cache")
                .header("postman-token", "89ef308c-d32b-146b-a220-36c53fa044cd")
                .asString();
    }


    @Test
    public void EvalueerBeheerderOphalen() throws UnirestException{
        HttpResponse<String> response = Unirest.get("http://localhost:8080/api/beheerders/3")
                .header("authorization", "Basic cm9uYW5AaG90bWFpbCxjb206cm9uYW4=")
                .header("cache-control", "no-cache")
                .header("postman-token", "cfd745e6-40cd-57b3-f1b4-020bdbb76d38")
                .asString();

    }
    @Test
    public void EvalueerBeheerdersOphalen() throws UnirestException {
        HttpResponse<String> response = Unirest.get("http://localhost:8080/api/beheerders")
                .header("authorization", "Basic cm9uYW5AaG90bWFpbCxjb206cm9uYW4=")
                .header("cache-control", "no-cache")
                .header("postman-token", "61bc84c2-f9e4-4744-a908-433378805a69")
                .asString();

    }

}
