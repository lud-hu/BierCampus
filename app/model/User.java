package model;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by bernward on 22.02.17.
 *
 * Modelliert in Ebeans kompatiebler Form die Datenstruktur den Nutzer
 */
@Entity
@Table(name = "user")
public class User extends Model {

    //Daten zur Identifikation
    @Id
    @Column(name = "userId")
    private int userID;
    public static Model.Find<Integer, User> find = new Model.Find<Integer, User>() {
    };

    private String name;

    private String email;

    private String passwort;

    //Daten fuer soziale Interaktion
    @OneToMany
    private Collection<Integer> freunde;

    @OneToMany
    private Collection<Nachricht> nachrichten;

    //Daten fuer Spielmechanik
    private int erfahrung;

    @Column(precision = 2, scale = 1)
    private BigDecimal promille;

    @ManyToOne
    private Abzeichen gewaehltesAbzeichen;

    @ManyToOne
    private Level bestandenesLevel;

    @ManyToOne
    private Rang rang;

    private Date lastLogin;

    /**
     * creates new User from scratch
     * sets default values for erfahrung, promille, gewaehltesAbzeichen, bestandenesLevel, rang
     *
     * @param name
     * @param email
     * @param passwort
     */
    public User(String name, String email, String passwort) {
        this.name = name;
        this.email = email;
        this.setPasswort(passwort);
        this.erfahrung = 0;
        this.promille = new BigDecimal(0.0);
        this.gewaehltesAbzeichen = null;
        this.bestandenesLevel = null;
        this.rang = null;
        this.lastLogin = new Date();
    }

    /*
     * fuegt freunde einen
     * @param neuenFreund hinzu
     */
    public void addFreund(int neuerFreund) {
        freunde.add(neuerFreund);
    }

    /*
     * fuegt zu den nachrichten eine
     * @param neueNachricht hinzu
     */
    public void addNachricht(Nachricht neueNachricht) {
        nachrichten.add(neueNachricht);
    }

    /**
     * Gets userID
     *
     * @return value of userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Gets name
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets email
     *
     * @return value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets passwort
     *
     * @return value of passwort
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     * Gets freunde
     *
     * @return value of freunde
     */
    public Collection<Integer> getFreunde() {
        return freunde;
    }

    /**
     * Gets nachrichten
     *
     * @return value of nachrichten
     */
    public Collection<Nachricht> getNachrichten() {
        return nachrichten;
    }

    /**
     * Gets erfahrung
     *
     * @return value of erfahrung
     */
    public int getErfahrung() {
        return erfahrung;
    }

    /**
     * Gets promille
     *
     * @return value of promille
     */
    public BigDecimal getPromille() {
        return promille;
    }

    /**
     * Helper für die ProfilDarstellung
     * (Profildarstellung baut Bildpfad aus der ersten Stelle des Promills)
     *
     * @return erste Stelle der Variable promille als int
     */
    public int getFirstDigitOfPromille(){
        BigDecimal truncated = promille.setScale(0, BigDecimal.ROUND_DOWN);
        return Integer.valueOf(truncated.intValue());
    }

    /**
     * Gets gewaehltesAbzeichen
     *
     * @return value of gewaehltesAbzeichen
     */
    public Abzeichen getGewaehltesAbzeichen() {

        if (gewaehltesAbzeichen == null) return Abzeichen.newAnfaengerAbzeichen();
        return gewaehltesAbzeichen;
    }

    /**
     * Gets bestandenesLevel
     *
     * @return value of bestandenesLevel
     */
    public Level getBestandenesLevel() {

        if (bestandenesLevel == null) return Level.newAnfaengerLevel();
        return bestandenesLevel;
    }

    /**
     * Gets rang
     *
     * @return value of rang
     */
    public Rang getRang() {
        List<Rang> raenge = Rang.find.orderBy("xp_grenze desc").findList();

        for (int i = 0; i < raenge.size(); i++) {
            //Wenn meine xp größer als die des Levels, dann Level zurück geben
            if (raenge.get(i).getXpGrenze() <= getErfahrung()) return raenge.get(i);
        }

        return rang;
    }

    /**
     * Gets lastLogin date
     *
     * @return
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     * stets the variable erfahrung to the value of
     *
     * @param erfahrung
     */
    public void setErfahrung(int erfahrung) {
        this.erfahrung = erfahrung;
    }

    /**
     * stets the variable promille to the value of
     *
     * @param promille
     */
    public void setPromille(BigDecimal promille) {

        BigDecimal temp = promille;

        //Schaut ob die Promille 9.9 übersteigen würde (9.9 ist die Obergrenze)
        //setzt in dem Fall Promille auf 9.9 (wird also nicht erhöht)
        if (temp.compareTo(new BigDecimal(9.9)) > 0 ){
            this.promille = new BigDecimal(9.9);
        } else {
            this.promille = promille;
        }
    }

    /**
     * stets the variable gewaehltesAbzeichen to the value of
     *
     * @param gewaehltesAbzeichen
     */
    public void setGewaehltesAbzeichen(Abzeichen gewaehltesAbzeichen) {
        this.gewaehltesAbzeichen = gewaehltesAbzeichen;
    }

    /**
     * stets the variable bestandenesLevel to the value of
     *
     * @param bestandenesLevel
     */
    public void setBestandenesLevel(Level bestandenesLevel) {
        this.bestandenesLevel = bestandenesLevel;
    }

    /**
     * stets the variable rang to the value of
     *
     * @param rang
     */
    public void setRang(Rang rang) {
        this.rang = rang;
    }

    /**
     * sets last Login date
     *
     * @param lastLogin
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * Erzeugt gehashtes Passwort und setzt es für den User.
     *
     * @param passwort
     *            in Klartext
     */
    public void setPasswort(String passwort) {
        if (passwort == null) throw new IllegalArgumentException("passwort leer");

        this.passwort = HashHelper.createPassword(passwort);
    }

    /**
     * gibt die Zeitdifferenz zwischen dem letzten Login und der aktuellen Zeit
     * in SEkunden zurück
     *
     * @return dif (aktuelle Zeit - letzer Login) in sec
     */
    public long getSecsSinceLastLogin() {
        Date currentTime = new Date();
        long loginDiff = currentTime.getTime() - getLastLogin().getTime();
        //berechnet Sekunden:
        loginDiff = loginDiff / 1000;

        return loginDiff;
    }

    /**
     * brechnet die Promillezahl die der Nutzer seit dem letzten einloggen verloren hat
     * neu: (0,1 Promille pro Stunde)
     * auskommentiert: (erst nach einem Tag 0,1 pro Stunde)
     *
     * @return die Promille die der Nutzer seit dem letzten einlogen verloren hat
     */
    public BigDecimal getPromilleVerlust() {
        ////erst ab einem Tag verfallen die Promille
        //if (getSecsSinceLastLogin() < 24 * 60 * 60) {
        //    //es muss nichts abgezogen werden
        //    return new BigDecimal("0");
        //} else {
            //berechnet die Stunden ab einem tag in dennen der User nicht eingeloggt war
            //long abzuziehendeStunden = (getSecsSinceLastLogin()-24 * 60 *60)/(60 * 60);
            long abzuziehendeStunden = (getSecsSinceLastLogin()/(60 * 60));
            //brechente die abzuziehende Promillenazhal
            return new BigDecimal(abzuziehendeStunden/10);
        //}
    }
}
