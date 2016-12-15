package Bedrijf;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

/**
 * @author Mohamed El Baze
 * @version 0.1
 * @date 12/9/16
 */
public class BedrijfTest {
    @Test
    public void EvalueerBedrijfToevoegen()

    {
        try {
            HttpResponse<String> response = Unirest.post("http://localhost:8080/api/bedrijven")
                    .header("content-type", "application/json")
                    .header("authorization", "Basic bW86bW8=")
                    .header("cache-control", "no-cache")
                    .header("postman-token", "91227bbb-ddde-bd2b-c052-d2888d77a2e5")
                    .body("{\n \"bedrijfsnaam\": \"asd\",\n \"adres\": \"asad\",\n \"postcode\": \"asd\",\n \"website\": \"asd\",\n \"plaats\": \"asd\",\n \"contactpersoon\": \"asd\",\n \"telefoon\": \"ads\",\n \"email\": \"adsad\"\n}")
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
