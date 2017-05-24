package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import model.Rang;
import model.User;
import play.mvc.Controller;
import play.mvc.Result;

import java.math.BigDecimal;

/**
 * Created by bernward on 22.02.17.
 * In diesem Controller werden alle Requests des Biergewinnts abgehandelt die nicht im HomeController verarbeitet werden
 */

public class BierGewinntController  extends Controller{

    /**
     * Speichert Erfahrung für den aktuellen Nutzer bei gewonnenem Spiel
     *
     * @return Übergibt die Nachricht für die Snackbar
     */
    public Result speicherErfahrung() {
        JsonNode json = request().body().asJson();
        String schwierigkeit = json.toString();
        int zusatzErfahrung;
        BigDecimal zusatzPromille;
        // Wenn die schwierigkeit leicht war, dann erhält der Nutzer weniger Erfahrung und Promille
        if(schwierigkeit == "0")
        {
            zusatzErfahrung = 2;
            zusatzPromille = new BigDecimal(0.1);
        }
        else
        {
            zusatzErfahrung = 15;
            zusatzPromille = new BigDecimal(0.5);
        }
        // aktueller Nutzer und sein Rang
        User user = User.find.byId(Integer.parseInt(session("userID")));
        Rang aktuellerRang = user.getRang();
        //Erfahrung und Promille aktualisieren
        user.setErfahrung(user.getErfahrung()+zusatzErfahrung);
        user.setPromille(user.getPromille().add(zusatzPromille));
        BigDecimal truncatedPromille = zusatzPromille.setScale(1, BigDecimal.ROUND_DOWN);
        // Neuer Rang des users
        Rang neuerRang = user.getRang();
        String nachricht = "";
        String spielerName = user.getName();
        if(user.getPromille().compareTo(new BigDecimal(9.9))<0) {
            nachricht = "Glückwunsch " + spielerName + "! Du hast gewonnen und dabei " + zusatzErfahrung + " XP erhalten. Dein Promillepegel ist dabei um " + truncatedPromille + "‰ gestiegen!";
            // Wenn der alte und der neue Rang sich unterscheiden, dann füge das zu dem String nachricht hinzu
            if (!aktuellerRang.getBezeichnung().equals(neuerRang.getBezeichnung())) {
                nachricht = nachricht + "</br>Super, du bist auch ein Rang aufgestiegen! Du bist jetzt offiziell " + neuerRang.getBezeichnung() + "!";
            }
        }
        else {
            nachricht = "Ich weiß nicht wie du das in deinem Zustand geschafft hast, aber du hast gewonnen " + spielerName + ". </br>" +
                    "Deinen Promillepegel lassen wir in deinem eigenen Interesse jedoch nicht weiter steigen.";
        }
        // User in der Datenbank aktualisieren
        Ebean.update(user);
        // Nachricht zurückgeben
        return ok(nachricht);
    }

    /**
     * @return Gibt das aktuelle Abzeichen zurück, mit diesem wird das BierGewinnt gespielt
     */
    public Result bekommeAbzeichen() {
        User user1 = User.find.byId(Integer.parseInt(session("userID")));
        //nicht das eigentliche Abzeichen nehmen, sondern das für BierGewinnt
        String bild = "abzeichen" + user1.getGewaehltesAbzeichen().getAbzeichenId() + ".png";
        bild = "/assets/images/" +bild;
        return ok(bild);
    }

    /**
     * @return Username für direkte Ansprache in Snackbar holen.
     */
    public Result bekommeSpielername() {
        int userId = Integer.parseInt(session("userID"));
        String username = User.find.byId(userId).getName();
        return ok(username);
    }
}
