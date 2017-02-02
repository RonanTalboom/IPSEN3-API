package main.Model;

import com.fasterxml.jackson.annotation.JsonView;
import main.View;

import java.security.Principal;
import java.util.*;

/**
 *
 */
public class Event implements Principal{


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKlantId() {
        return klantId;
    }

    public void setKlantId(int klantId) {
        this.klantId = klantId;
    }

    public int getBeheerderId() {
        return beheerderId;
    }

    public void setBeheerderId(int beheerderId) {
        this.beheerderId = beheerderId;
    }

    public Date getBeginTijd() {
        return beginTijd;
    }

    public void setBeginTijd(Date beginTijd) {
        this.beginTijd = beginTijd;
    }

    public Date getEindTijd() {
        return eindTijd;
    }

    public void setEindTijd(Date eindTijd) {
        this.eindTijd = eindTijd;
    }

    public String getOnderwerp() {
        return onderwerp;
    }

    public void setOnderwerp(String onderwerp) {
        this.onderwerp = onderwerp;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    @Override
    public String getName() {
        return onderwerp;
    }
}