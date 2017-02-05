package main.Model;

import com.fasterxml.jackson.annotation.JsonView;
import main.View;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Dit is de Tag class. Hier worden gegevens van een tag opgeslagen
 *
 * @author Shaban Jama
 * @version 1.0, November 2016
 */
public class Tag {

    /**
     * Dit zijn de standaard waardes van de tag
     * id, naam en beschrijving
     */
    @JsonView(View.Public.class)
    private int id;
    @NotEmpty
    @Length(min = 3, max = 20)
    @JsonView(View.Public.class)
    private String naam;
    @JsonView(View.Public.class)
    private String beschrijving;

    /**
     * Methode die de id van de tag returned
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Methode die de id van de tag set.
     * @param id van de tag.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Methode die de id van de tag returned
     * @return naam
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Methode die de naam van de tag set.
     * @param naam van de tag.
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * Methode die de id van de tag returned
     * @return beschrijving
     */
    public String getBeschrijving() {
        return beschrijving;
    }

    /**
     * Methode die de beschrijving van de tag set.
     * @param beschrijving van de tag..
     */
    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

}
