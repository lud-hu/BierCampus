package controllers;


import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import model.*;
import play.mvc.Controller;
import play.mvc.Result;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bernward on 22.02.17.
 *
 * In diesem Controller werden alle Requests des Hauptspiels abgehandelt die nicht im HomeController verarbeitet werden
 */
public class SpielController extends Controller {
    /**
     * die Reihenfolge in der die Zutaten in den Kessel zu tun sind
     *
     * @return die reihenfolge der Zutaten als Plain-Text
     */
    public Result bekommeReihenfolge() {
        // User aus der Session
        User user1 = User.find.byId(Integer.parseInt(session("userID")));
        // Bestandenes Level +1
        Level level = Level.find.byId(user1.getBestandenesLevel().getLevelId()+1);
        // Liste der Schritte für das Level
        List<Schritt> schritte = (List) Level.getSchritteForLevel(level.getLevelId());

        List<String> zutaten = new ArrayList();
        // Baut den String zusammen für die Schritte des Levls, der an die view zurückgegeben wird
        for (int i = 0; i < schritte.size(); i++){
            if (schritte.get(i).getClass().equals(Zutat.class)){
                Zutat zutat =  (Zutat) schritte.get(i);
                zutaten.add(zutat.getName() + "+");
                zutaten.add(zutat.getBeschreibung()+"}");
            } else if (schritte.get(i).getClass().equals(QuizFrage.class)){
                QuizFrage frage =  (QuizFrage) schritte.get(i);
                zutaten.add(frage.getFrage() + "+");
                // Alle Antwortmöglichkeiten werden zufällig zum String hinzugefügt, damit die Validierung über den Controller erfolgt
                if(frage.getFalscheAntworten() != null) {
                    List<String> f = (List<String>) frage.getFalscheAntworten();
                    f.add(frage.getRichtigeAntwort());
                    Collections.shuffle(f);
                    for (int j = 0; j < f.size(); j++) {
                        zutaten.add(f.get(j) + "+");
                    }
                }
                zutaten.add(Integer.toString(frage.getFragenId())+ "+");
                zutaten.add("1}");
            }
        }
        String data = "";
        for (int i = 0; i<zutaten.size(); i++) {
            data = data + zutaten.get(i);
        }
        return ok(data);
    }

    /**
     * ueberprueft ob die Antwort korrekt war
     *
     * @return HTTP-Return, ob die Antwort korrekt war
     */
    public Result checkeAntwort() {
        // Splittet den String in die entsprechenden Variablen
        JsonNode json = request().body().asJson();
        String valuesInJson = json.toString();
        valuesInJson = valuesInJson.substring(1, valuesInJson.length() - 1);
        String[] data = valuesInJson.split("(,)");
        data[0] = data[0].substring(1,data[0].length()-1);
        data[1] = data[1].substring(1,data[1].length()-1);
        data[2] = data[2].substring(1,data[2].length()-1);
        int levelid = Integer.parseInt(data[0]);
        int fragenid = Integer.parseInt(data[1]);
        String antwort = data[2];
        Level level = Level.find.byId(levelid);
        QuizFrage frage = QuizFrage.find.byId(fragenid);
        String korrekteAntwort = frage.getRichtigeAntwort();
        String loesung = "";
        // Wenn die Antwort korrekt war, dann zeuge die Auflösung
        if(frage.getRichtigeAntwort().equals(antwort))
        {
            loesung = frage.getAufloesung();
            return ok(loesung);
        }
        // Falls nicht gebe einen bad request zurück
        else
        {
            return badRequest();
        }
    }

    /**
     * sepichern der erhaltenen Erfahrung in der Daten
     *
     * @return die Bestaetigung, dass die Erfahrung gespeichert wurde als Plain-Text
     */
    public Result speicherErfahrung() {
        User user = User.find.byId(Integer.parseInt(session("userID")));
        Level level = Level.find.byId(user.getBestandenesLevel().getLevelId()+1);
        Rang aktuellerRang = user.getRang();
        int levelErfahrung = level.getErhalteneEfahrung();
        user.setErfahrung(user.getErfahrung()+levelErfahrung);
        Rang neuerRang = user.getRang();
        // Baut den Text zusammen, der zurückgegeben wird
        String nachricht = "Du hast das Level \"" + level.getLevelName()+ "\" bestanden und " + levelErfahrung +" Erfahrungspunkte erhalten!\nDu kannst jetzt das Abzeichen \"" + level.getErhaltenesAbzeichen().getName() +"\" in deinem Profil auswählen. Deine Promille sind um 1.0 gestiegen.";
        // Wenn der alte und der neue Rang sich unterscheiden, dann füge das zu dem String nachricht hinzu
        if(!aktuellerRang.getBezeichnung().equals(neuerRang.getBezeichnung()))
        {
            nachricht = nachricht + "\nSuper, du bist auch ein Rang aufgestiegen! Du bist jetzt " + neuerRang.getBezeichnung() + "!";
        }
        BigDecimal promille = new BigDecimal(1);
        user.setPromille(user.getPromille().add(promille));
        user.setBestandenesLevel(level);
        Ebean.update(user);
        return ok(nachricht);
    }

    /**
     * gibt zufällig gemischte Liste aller Zutaten für das jeweilige Level zurück
     * @param levelId
     * @return
     */
    public static List<Zutat> getShuffledZutatenList(int levelId){

        // Liste der Schritte für das Level
        List<Schritt> schritte = (List) Level.getSchritteForLevel(levelId);
        List<Zutat> zutaten = new ArrayList<Zutat>();
        Zutat zutat;
        //packe alles was Zutaten sind in die Zutatenliste
        for (int i = 0; i < schritte.size(); i++) {
            if (schritte.get(i).getClass().equals(Zutat.class)) {
                zutat = (Zutat) schritte.get(i);
                zutaten.add(zutat);
            }
        }

        //Reihenfolge durchmischen
        Collections.shuffle(zutaten);

        return zutaten;
    }
}
