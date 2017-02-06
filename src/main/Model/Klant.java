package main.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import main.View;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Dit is de klant class. Hier worden gegevens van de klant opgeslagen
 * Created by Mike on 12-10-2016.
 */
public class Klant { //implements principal?? que pasa??
    /**
     * Dit zijn de standaard waardes van de klant
     * id, voornaam, achternaam, adres, postcode,
     * woonplaats, geboortedatum, telefoon, linkedin,arrTags,arrBedrijven,
     * email,isActief
     */
    @JsonView(View.Public.class)
    private int id;
    @NotEmpty
    @Length(min = 3, max = 30)
    @JsonView(View.Public.class)
    private String voornaam;
    @NotEmpty
    @Length(min = 3, max = 30)
    @JsonView(View.Public.class)
    private String achternaam;
    @NotEmpty
    @Length(min = 3, max = 30)
    @JsonView(View.Public.class)
    private String adres;
    @NotEmpty
    @Length(min = 6,max = 6)
    @JsonView(View.Public.class)
    private String postcode;
    @NotEmpty
    @Length(min = 3, max = 30)
    @JsonView(View.Public.class)
    private String woonplaats;
    @NotEmpty
    @Length(min = 3, max = 30)
    @JsonView(View.Public.class)
    private Date geboortedatum;
    @NotEmpty
    @Length(min = 3, max = 100)
    @JsonView(View.Public.class)
    private String telefoon;
    @NotEmpty
    @Length(min = 3, max = 30)
    @JsonView(View.Public.class)
    private String linkedin;
    @NotEmpty
    @Length(min = 3, max = 100)
    @JsonView(View.Public.class)
    private String email;
    @JsonView(View.Public.class)
    private ArrayList<String> arrTags = new ArrayList<>();
    @JsonIgnore
    private ArrayList arrBedrijven = new ArrayList();
    @JsonView(View.Public.class)
    private boolean isactief= false;


    /**
     * Methode die de isactief van de klant set
     */

    public void setIsactief(boolean isactief) {
        this.isactief = isactief;
    }

    /**
     * Methode die de geboortedatum van de klant set
     */
    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }
    /**
     * Methode die de geboortedatum van de klant returned
     * @return geboortedatum
     */
    public Date getGeboortedatum() {
        return geboortedatum;
    }
    /**
     * Methode die de id van de klant returned
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * Methode die de id van de klant set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Methode die de voornaam van de klant returned
     * @return voornaam
     */
    public String getVoornaam() {
        return voornaam;
    }
    /**
     * Methode die de voornaam van de klant set
     */
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }
    /**
     * Methode die de achternaam van de klant returned
     * @return achternaam
     */
    public String getAchternaam() {
        return achternaam;
    }
    /**
     * Methode die de achternaam van de klant set
     */
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }
    /**
     * Methode die de adres van de klant returned
     * @return adres
     */
    public String getAdres() {
        return adres;
    }
    /**
     * Methode die de adres van de klant set
     */
    public void setAdres(String adres) {
        this.adres = adres;
    }
    /**
     * Methode die de postcode van de klant returned
     * @return postcode
     */
    public String getPostcode() {
        return postcode;
    }
    /**
     * Methode die de postcode van de klant set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    /**
     * Methode die de woonplaats van de klant returned
     * @return woonplaats
     */
    public String getWoonplaats() {
        return woonplaats;
    }
    /**
     * Methode die de woonplaats van de klant set
     */
    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }
    /**
     * Methode die de telefoon van de klant returned
     * @return telefoon
     */
    public String getTelefoon() {
        return telefoon;
    }
    /**
     * Methode die de telefoon van de klant set
     */
    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }
    /**
     * Methode die de LinkedIn van de klant returned
     * @return LinkedIn
     */
    public String getLinkedin() {
        return linkedin;
    }
    /**
     * Methode die de linkedin van de klant set
     */
    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
    /**
     * Methode die de Tags van de klant returned
     * @return arrTags
     */
    public ArrayList<String> getArrTags() {
        return arrTags;
    }
    /**
     * Methode die de tags van de klant set
     */
    public void setArrTags(String naam) {
        arrTags.add(naam);
    }
    /**
     * Methode die de bedrijven van de klant returned
     * @return bedrijven
     */
    public ArrayList<Bedrijf> getArrBedrijven() {
        return arrBedrijven;
    }
    /**
     * Methode die de bedrijven van de klant set
     */
    public void addArrBedrijven(Bedrijf bedrijf) {
        arrBedrijven.add(bedrijf);
    }
    /**
     * Methode die de email van de klant returned
     * @return email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Methode die de email van de klant set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
