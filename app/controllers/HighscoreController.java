package controllers;

import model.User;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by bernward on 22.02.17.
 *
 * In diesem Controller werden alle Requests des Highscores abgehandelt die nicht im HomeController verarbeitet werden
 */
public class HighscoreController extends Controller {

    /**
     * gibt eine sortierte Liste der besten Nutzer zurück
     *
     * @return die besten Nutzer, maximal 10
     */
    public static List<User> getHighscoreList(){
        List<User> nutzerListe = User.find
                .where().ne("bestandenes_level_level_id", null)
                .orderBy("erfahrung desc")
                .findList();
        int highscoregroesse = 10;
        if(nutzerListe.size() < highscoregroesse){
            highscoregroesse = nutzerListe.size();
        }
        return nutzerListe.subList(0, highscoregroesse);
    }

}
