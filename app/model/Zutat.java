package model;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bernward on 22.02.17.
 *
 * Modelliert in Ebeans kompatiebler Form die Datenstruktur der Zutat
 */
@Entity
@Table(name="zutat")
public class Zutat extends Model implements Schritt {

    @Id
    private int zutatId;
    public static Zutat.Find<Integer,Zutat> find = new Zutat.Find<Integer, Zutat>(){};

    private String beschreibung;
    private String name;
    private String bild;

    public Zutat(int zutatId, String beschreibung, String name, String bild)
    {
        this.zutatId = zutatId;
        this.beschreibung = beschreibung;
        this.name = name;
        this.bild = bild;
    }
    /**
     * Gets zutatId
     *
     * @return value of zutatId
     */
    public int getZutatId() {
        return zutatId;
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
     * Gets name
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets name
     *
     * @return value of name
     */
    public String getBild() {
        return bild;
    }
}
