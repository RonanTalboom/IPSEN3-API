package main.Model;

import com.fasterxml.jackson.annotation.JsonView;
import main.View;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.security.auth.Subject;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.Date;

/**
 * Dit is de Notitie class. Hier worden gegevens van de notitie opgeslagen
 *
 * @author Shaban Jama, Mohammed El Baze
 * @version 1.0, November 2016
 */
public class Notitie implements Principal{
    /**
     * Dit zijn de standaard waardes van de klant
     * id, titel, beschrijving, datum, bedrijfID,
     * klantID, gebruikerID, klantNaam, bedrijfNaam
     */

    @JsonView(View.Public.class)
    private int id;

    @JsonView(View.Public.class)
    @NotEmpty
    @NotNull
    @NotBlank
    private String titel;

    @JsonView(View.Public.class)
    @NotEmpty
    @NotNull
    @NotBlank
    private String beschrijving;

    @JsonView(View.Public.class)
    private Date datum;

    @JsonView(View.Public.class)
    private int bedrijfID;
    @JsonView(View.Public.class)
    private int klantID;
    @JsonView(View.Public.class)
    private int gebruikerID;
    @JsonView(View.Public.class)
    private Klant klantNaam;
    @JsonView(View.Public.class)
    private Bedrijf bedrijfNaam;

    /**
     * Methode die de id van de notitie returned
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * Methode die de id van de notitie set.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Methode die de titel van de notitie returned
     * @return titel
     */
    public String getTitel() {
        return titel;
    }
    /**
     * Methode die de titel van de notitie set.
     */
    public void setTitel(String titel) {
        this.titel = titel;
    }
    /**
     * Methode die de beschrijving van de notitie returned
     * @return id
     */
    public String getBeschrijving() {
        return beschrijving;
    }
    /**
     * Methode die de beschrijving van de notitie set.
     */
    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }
    /**
     * Methode die de datum van de notitie returned
     * @return id
     */
    public Date getDatum() {
        return datum;
    }
    /**
     * Methode die de datum van de notitie set.
     */
    public void setDatum(Date datum) {
        this.datum = datum;
    }
    /**
     * Methode die de bedrijfID van de notitie returned
     * @return id
     */
    public int getBedrijfID() {
        return bedrijfID;
    }
    /**
     * Methode die de bedrijfId van de notitie set.
     */
    public void setBedrijfID(int bedrijfID) {
        this.bedrijfID = bedrijfID;
    }
    /**
     * Methode die de klantID van de notitie returned
     * @return id
     */
    public int getKlantID() {
        return klantID;
    }
    /**
     * Methode die de klantID van de notitie set.
     */
    public void setKlantID(int klantID) {
        this.klantID = klantID;
    }
    /**
     * Methode die de gebruikerID van de notitie returned
     * @return id
     */
    public int getGebruikerID() {
        return gebruikerID;
    }
    /**
     * Methode die de gebruikerID van de notitie set.
     */
    public void setGebruikerID(int gebruikerID) {
        this.gebruikerID = gebruikerID;
    }
    /**
     * Methode die de klantNaam van de notitie set.
     */
    public void setKlantNaam(Klant klantNaam) {
        this.klantNaam = klantNaam;
    }
    /**
     * Methode die de klantnaam van de notitie returned
     * @return id
     */
    public Klant getKlantNaam() {
        return klantNaam;
    }
    /**
     * Methode die de bedrijfnaam van de notitie returned
     * @return id
     */
    public Bedrijf getBedrijfNaam() {
        return bedrijfNaam;
    }
    /**
     * Methode die de bedrijfNaam van de notitie set.
     */
    public void setBedrijfNaam(Bedrijf bedrijfNaam) {
        this.bedrijfNaam = bedrijfNaam;
    }

    @Override
    public String getName() {
        return titel;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
