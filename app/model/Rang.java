package model;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bernward on 22.02.17.
 *
 * Modelliert in Ebeans kompatiebler Form die Datenstruktur des Ranges
 */
@Entity
@Table(name="rang")
public class Rang extends Model {

    @Id
    private int rangId;
    public static Rang.Find<Integer,Rang> find = new Rang.Find<Integer, Rang>(){};
    private String bezeichnung;
    private int xpGrenze;

    public Rang (int rangId, String bezeichnung, int xpGrenze){
        this.rangId = rangId;
        this.bezeichnung = bezeichnung;
        this.xpGrenze = xpGrenze;
    }
    /**
     * Gets rangId
     *
     * @return value of rangId
     */
    public int getRangId() {
        return rangId;
    }

    /**
     * Gets bezeichnung
     *
     * @return value of bezeichnung
     */
    public String getBezeichnung() {
        return bezeichnung;
    }

    /**
     * Gets xpGrenze
     *
     * @return value of xpGrenze
     */
    public int getXpGrenze() {
        return xpGrenze;
    }
}
