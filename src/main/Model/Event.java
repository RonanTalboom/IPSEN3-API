package main.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import main.View;

import java.security.Principal;
import java.util.*;

/**
 * Dit is het Model van een Event. Hierin wordt alle data voor een enkel event object opgeslagen.
 *
 * @author Ruben van Til
 * @version 1.0 Februari 2017
 */
public class Event implements Principal {

    /**
     * Dit zijn de waardes van een Event:
     * id, klantId, beheerderId, beginTijd, eindTijd, onderwerp, beschrijving, locatie
     */
    @JsonView(View.Public.class)
    private int id;
    @JsonView(View.Public.class)
    private int klantId;
    @JsonView(View.Public.class)
    private int beheerderId;
    @JsonView(View.Public.class)
    private Date beginTijd;
    @JsonView(View.Public.class)
    private Date eindTijd;
    @JsonView(View.Public.class)
    private String onderwerp;
    @JsonView(View.Public.class)
    private String beschrijving;
    @JsonView(View.Public.class)
    private String locatie;

    /**
     * getter voor id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * setter voor id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter voor klantId
     *
     * @return klantId
     */
    public int getKlantId() {
        return klantId;
    }

    /**
     * setter voor klantId
     *
     * @param klantId
     */
    public void setKlantId(int klantId) {
        this.klantId = klantId;
    }

    /**
     * getter voor beheerderId
     *
     * @return beheerderId
     */
    public int getBeheerderId() {
        return beheerderId;
    }

    /**
     * setter voor beheerderId
     *
     * @param beheerderId
     */
    public void setBeheerderId(int beheerderId) {
        this.beheerderId = beheerderId;
    }

    /**
     * getter voor beginTijd
     *
     * @return beginTijd
     */
    public Date getBeginTijd() {
        return beginTijd;
    }

    /**
     * setter voor beginTijd
     *
     * @param beginTijd
     */
    public void setBeginTijd(Date beginTijd) {
        this.beginTijd = beginTijd;
    }

    /**
     * getter voor eindTijd
     *
     * @return eindTijd
     */
    public Date getEindTijd() {
        return eindTijd;
    }

    /**
     * setter voor eindTijd
     *
     * @param eindTijd
     */
    public void setEindTijd(Date eindTijd) {
        this.eindTijd = eindTijd;
    }

    /**
     * getter voor onderwerp
     *
     * @return onderwerp
     */
    public String getOnderwerp() {
        return onderwerp;
    }

    /**
     * setter voor Onderwerp
     *
     * @param onderwerp
     */
    public void setOnderwerp(String onderwerp) {
        this.onderwerp = onderwerp;
    }

    /**
     * getter voor beschrijving
     *
     * @return beschrijving
     */
    public String getBeschrijving() {
        return beschrijving;
    }

    /**
     * setter voor beschrijving
     *
     * @param beschrijving
     */
    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    /**
     * getter voor locatie
     *
     * @return locatie
     */
    public String getLocatie() {
        return locatie;
    }

    /**
     * setter voor locatie
     *
     * @param locatie
     */
    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    @Override
    @JsonIgnore
    public String getName() {
        return onderwerp;
    }
}