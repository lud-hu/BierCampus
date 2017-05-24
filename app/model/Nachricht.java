package model;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bernward on 22.02.17.
 *
 * Modelliert in Ebeans kompatiebler Form die Datenstruktur der Nachricht
 */
@Entity
@Table(name="nachrichten")
public class Nachricht extends Model {

    @Id
    private int nachrichtenId;
    public static Nachricht.Find<Integer,Nachricht> find = new Nachricht.Find<Integer, Nachricht>(){};

    private int absender;
    private int empfaenger;
    private String inhalt;

    /**
     * stets the variable gelesen to the value of
     *
     * @param gelesen
     */
    public void setGelesen(boolean gelesen) {
        this.gelesen = gelesen;
    }

    private boolean gelesen;

    /**
     * Konstruktor zum erstellen einer neuen Nachricht
     *
     * @param empfaenger die ID des Nutzers, der die Nachricht erhaelt
     * @param absender die ID des Nutzers, der die Nachricht abgesendet hat
     * @param inhalt der Inhalt der Nachricht
     */
    public Nachricht(int empfaenger,int absender, String inhalt){
        this.empfaenger = empfaenger;
        this.absender = absender;
        this.inhalt = inhalt;
        this.gelesen = false;
    }

    /**
     * Gets nachrichtenId
     *
     * @return value of nachrichtenId
     */
    public int getNachrichtenId() {
        return nachrichtenId;
    }

    /**
     * Gets absender
     *
     * @return value of absender
     */
    public int getAbsender() {
        return absender;
    }

    /**
     * Gets empfaenger
     *
     * @return value of empfaenger
     */
    public int getEmpfaenger() {
        return empfaenger;
    }

    /**
     * Gets inhalt
     *
     * @return value of inhalt
     */
    public String getInhalt() {
        return inhalt;
    }

    /**
     * Gets gelesen
     *
     * @return value of gelesen
     */
    public boolean isGelesen() {
        return gelesen;
    }

}
