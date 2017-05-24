package model;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by bernward on 22.02.17.
 *
 * Modelliert in Ebeans kompatiebler Form die Datenstruktur der Quizfrage
 */
@Entity
@Table(name="quizfrage")
public class QuizFrage extends Model implements Schritt{

    @Id
    private int fragenId;
    public static QuizFrage.Find<Integer,QuizFrage> find = new QuizFrage.Find<Integer, QuizFrage>(){};

    private String name;
    private String frage;
    private String falscheAntwort1;
    private String falscheAntwort2;
    private String falscheAntwort3;
    //im Konstruktor die falschen Antworten in eine Collection packen:
    private Collection<String> falscheAntworten;
    private String richtigeAntwort;
    private String aufloesung;

    public QuizFrage(String name, String frage, String falscheAntwort1, String falscheAntwort2, String falscheAntwort3, String richtigeAntwort, String aufloesung)
    {
        this.name = name;
        this.frage = frage;
        this.falscheAntwort1 = falscheAntwort1;
        this.falscheAntwort2 = falscheAntwort2;
        this.falscheAntwort3 = falscheAntwort3;
        this.richtigeAntwort = richtigeAntwort;
        this.aufloesung = aufloesung;
    }
    /**
     * Gets fragenId
     *
     * @return value of fragenId
     */
    public int getFragenId() {
        return fragenId;
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
     * Gets frage
     *
     * @return value of frage
     */
    public String getFrage() {
        return frage;
    }

    /**
     * Gets falscheAntworten
     *
     * @return value of falscheAntworten
     */
    public Collection<String> getFalscheAntworten() {

        List<String> falscheAntworten = new ArrayList<>();

        falscheAntworten.add(falscheAntwort1);
        falscheAntworten.add(falscheAntwort2);
        falscheAntworten.add(falscheAntwort3);

        return falscheAntworten;
    }

    /**
     * Gets richtigeAntwort
     *
     * @return value of richtigeAntwort
     */
    public String getRichtigeAntwort() {
        return richtigeAntwort;
    }

    /**
     * Gets aufloesung
     *
     * @return value of aufloesung
     */
    public String getAufloesung() {
        return aufloesung;
    }
}
