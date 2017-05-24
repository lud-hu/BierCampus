package model;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * Helper-Klasse für Schritte eines Levels
 * Verzeichnet in welchem Level bei welchem Schritt welche Zutat oder Quizfrage verwendet werden soll
 *
 * Created by Ludwig on 23.02.2017.
 */
@Entity
@Table(name="schritteverzeichnis")
public class SchritteVerzeichnis extends Model {

    @Id
    private int schrittVerzeichnisId;
    public static Model.Find<Integer,SchritteVerzeichnis> find = new Model.Find<Integer, SchritteVerzeichnis>(){};

    private int schrittId;

    @ManyToOne
    private Level level;

    @ManyToOne
    private Zutat zutat;

    @ManyToOne
    private QuizFrage quizFrage;

    public SchritteVerzeichnis(int schrittId, Level level, Zutat zutat, QuizFrage quizFrage) {
        this.schrittId = schrittId;
        this.level = level;
        this.zutat = zutat;
        this.quizFrage = quizFrage;
    }

    public int getSchrittVerzeichnisId() {
        return schrittVerzeichnisId;
    }

    public int getSchrittId() {
        return schrittId;
    }

    public Level getLevel() {
        return level;
    }

    public Zutat getZutat() {
        return zutat;
    }

    public QuizFrage getQuizFrage() {
        return quizFrage;
    }
}
