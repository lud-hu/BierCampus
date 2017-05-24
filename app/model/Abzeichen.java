package model;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bernward on 22.02.17.
 *
 * Modelliert in Ebeans kompatiebler Form die Datenstruktur des Abzeichens
 */
@Entity
@Table(name="abzeichen")
public class Abzeichen extends Model {

    @Id
    private int abzeichenId;
    public static Abzeichen.Find<Integer,Abzeichen> find = new Abzeichen.Find<Integer, Abzeichen>(){};
    private String name;
    private String beschreibung;
    private String bild;

    public Abzeichen(int abzeichenId, String name, String beschreibung, String bild) {
        this.abzeichenId = abzeichenId;
        this.name = name;
        this.beschreibung = beschreibung;
        this.bild = bild;
    }

    public static Abzeichen newAnfaengerAbzeichen(){
        Abzeichen abzeichen = new Abzeichen(0, "Neueinsteiger", "Du hast noch nichts freigespielt!", "neueinsteiger.png");

        return abzeichen;
    }

    /**
     * Gets abzeichenId
     *
     * @return value of abzeichenId
     */
    public int getAbzeichenId() {
        return abzeichenId;
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
     * Gets beschreibung
     *
     * @return value of beschreibung
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * Gets bild
     *
     * @return value of bild
     */
    public String getBild() {
        return bild;
    }
}
