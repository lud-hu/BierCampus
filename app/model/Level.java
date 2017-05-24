package model;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by bernward on 22.02.17.
 *
 * Modelliert in Ebeans kompatiebler Form die Datenstruktur des Levels
 */
@Entity
@Table(name="level")
public class Level extends Model {

    @Id
    private int levelId;
    public static Level.Find<Integer,Level> find = new Level.Find<Integer, Level>(){};

    private String levelName;

    @OneToMany
    private Collection<Schritt> schritte;
    private int erhalteneEfahrung;

    @OneToOne
    private Abzeichen erhaltenesAbzeichen;

    public Level(int levelId, String levelName) {
        this.levelId = levelId;
        this.levelName = levelName;
    }

    public static Level newAnfaengerLevel(){
        return new Level(0, "noch nicht gespielt");
    }

    /**
     * Gets levelId
     *
     * @return value of levelId
     */
    public int getLevelId() {
        return levelId;
    }

    /**
     * Gets levelName
     *
     * @return value of levelName
     */
    public String getLevelName() {
        return getLevelId() + " - " + levelName;
    }

    /**
     * Gets schritte
     *
     * @return value of schritte
     */
    public Collection<Schritt> getSchritte() {
        return schritte;
    }

    /**
     * Gets erhalteneEfahrung
     *
     * @return value of erhalteneEfahrung
     */
    public int getErhalteneEfahrung() {
        return erhalteneEfahrung;
    }

    /**
     * Gets erhaltenesAbzeichen
     *
     * @return value of erhaltenesAbzeichen
     */
    public Abzeichen getErhaltenesAbzeichen() {
        return erhaltenesAbzeichen;
    }

    /**
     *
     * gibt eine Collection an Schritten für das angegebene Level zurück
     *
     * @param levelId
     * @return Collection<Schritt>
     */
    public static Collection<Schritt> getSchritteForLevel(int levelId){
        //sortierte Liste mit allen Schritten fürs angegebene Level (ORM ftw!!!)
        List<SchritteVerzeichnis> schritteForLevel = SchritteVerzeichnis.find
                                                            .where().eq("level_level_id", levelId)
                                                            .orderBy("schritt_id")
                                                            .findList();

        //hier kommen dann die fertigen Schritt-Objekte rein (Zutat oder Quizfrage)
        List<Schritt> schritte = new ArrayList<>();

        //sucht sich für jedes SchritteVerzeichnis-Objekt das entsprechende Schritt-Objekt und fügt es an die Liste an
        for (int i = 0; i < schritteForLevel.size(); i++){
            if (schritteForLevel.get(i).getZutat() != null){
                //wenn Schritt eine Zutat ist: Zutatenobjekt holen
                schritte.add(schritteForLevel.get(i).getZutat());

            } else if (schritteForLevel.get(i).getQuizFrage() != null){
                //wenn Schritt eine Quizfrage ist: Quizfragenobjekt holen
                schritte.add(schritteForLevel.get(i).getQuizFrage());
            }
        }

        return schritte;

    }
}
