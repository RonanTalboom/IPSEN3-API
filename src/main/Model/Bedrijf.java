package main.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import main.View;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;

/**
 * Dit is de bedrijf model class. Deze is verantwoordelijk voor alle gegevens
 * van de bedrijf.
 * @author Mohamed El Baze
 * @version 0.1 Januari 2017
 */
public class Bedrijf {
    /**
     * Dit zijn de standaard waardes van de bedrijven
     * id, bedrijfsnaam, adres, postcode, website,
     * plaats, contactpersoon, telefoon, email, Tags en Actief-status
     */
    @JsonView(View.Public.class)
    private int id;
    @NotEmpty
    @Length(min = 3, max = 30)
    @JsonView(View.Public.class)
    private String bedrijfsnaam;
    @NotEmpty
    @Length(min = 3, max = 100)
    @JsonView(View.Public.class)
    private String adres;
    @NotEmpty
    @Length(min = 6,max = 6)
    @JsonView(View.Public.class)
    private String postcode;
    @NotEmpty
    @Length(min = 3, max = 30)
    @JsonView(View.Public.class)
    private String website;
    @NotEmpty
    @Length(min = 3, max = 30)
    @JsonView(View.Public.class)
    private String plaats;
    @NotEmpty
    @Length(min = 3, max = 100)
    @JsonView(View.Public.class)
    private String contactpersoon;
    @NotEmpty
    @Length(min = 3, max = 100)
    @JsonView(View.Public.class)
    private String telefoon;
    @NotEmpty
    @Length(min = 3, max = 100)
    @JsonView(View.Public.class)
    private String email;
    @JsonIgnore
    private ArrayList<Integer> arrTags = new ArrayList<>();
    @JsonView(View.Public.class)
    private boolean isactief = false;
    /**
     * Methode die de id van de bedrijf returned
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * Methode die de id van de bedrijf set.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Methode die de bedrijfsnaam van de bedrijf returned
     * @return bedrijfsnaam
     */
    public String getBedrijfsnaam() {
        return bedrijfsnaam;
    }
    /**
     * Methode die de bedrijfsnaam van de bedrijf returned
     * @param bedrijfsnaam
     */
    public void setBedrijfsnaam(String bedrijfsnaam) {
        this.bedrijfsnaam = bedrijfsnaam;
    }
    /**
     * Methode die de adres van de bedrijf returned
     * @return adres
     */
    public String getAdres() {
        return adres;
    }
    /**
     * Methode die de adres van de bedrijf set
     * @param adres
     */
    public void setAdres(String adres) {
        this.adres = adres;
    }
    /**
     * Methode die de getPostcode van de bedrijf returned
     * @return getPostcode
     */
    public String getPostcode() {
        return postcode;
    }
    /**
     * Methode die de postcode van de bedrijf returned
     * @param postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    /**
     * Methode die de website van de bedrijf returned
     * @return website
     */
    public String getWebsite() {
        return website;
    }
    /**
     * Methode die de website van de bedrijf returned
     * @param website
     */
    public void setWebsite(String website) {
        this.website = website;
    }
    /**
     * Methode die de plaats van de bedrijf returned
     * @return plaats
     */
    public String getPlaats() {
        return plaats;
    }
    /**
     * Methode die de plaats van de bedrijf returned
     * @param plaats
     */
    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }
    /**
     * Methode die de contactpersoon van de bedrijf returned
     * @return contactpersoon
     */
    public String getContactpersoon() {
        return contactpersoon;
    }
    /**
     * Methode die de contactpersoon van de bedrijf returned
     * @param contactpersoon
     */
    public void setContactpersoon(String contactpersoon) {
        this.contactpersoon = contactpersoon;
    }
    /**
     * Methode die de telefoon van de bedrijf returned
     * @return telefoon
     */
    public String getTelefoon() {
        return telefoon;
    }
    /**
     * Methode die de telefoon van de bedrijf returned
     * @param telefoon
     */
    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }
    /**
     * Methode die de email van de bedrijf returned
     * @return email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Methode die de email van de bedrijf returned
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Methode die de arrTags van de bedrijf returned
     * @return arrTags
     */
    public ArrayList<Integer> getArrTags() {
        return arrTags;
    }
    /**
     * Methode die de arrTags van de bedrijf returned
     * @param id
     */
    public void setArrTags(int id) {
        arrTags.add(id);
    }
    /**
     * Methode de actief/non-actieve status van bedrijf returned
     * @param isactief
     */
    public void isIsactief(boolean isactief) {
        this.isactief = isactief;
    }
}
