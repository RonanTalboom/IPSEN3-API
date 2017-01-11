package main.Model;

import java.util.*;

/**
 *
 */
public class Event {


    private int id;
    private int klantId;
    private int beheerderId;
    private Date beginTijd;
    private Date eindTijd;
    private String onderwerp;
    private String beschrijving;
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
}