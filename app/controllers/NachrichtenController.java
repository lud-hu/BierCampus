package controllers;

import com.avaje.ebean.Ebean;
import model.Nachricht;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.nachrichtLesen;

import java.util.List;

/**
 * Created by bernward on 22.02.17.
 *
 * In diesem Controller werden alle Requests der Nachrichten abgehandelt die nicht im HomeController verarbeitet werden
 */
public class NachrichtenController extends Controller {

    /*
     * laedt den Inhalt einer Nachricht als HTML
     */
    public Result nachrichtLesen(){
        //Liest die NachrichtenID der anzuzeigenden Nachricht aus dem Request aus
        int nachrichtenId = Integer.parseInt(request().getQueryString("nachrichtenID"));
        Nachricht ausgewaehlteNachricht = Nachricht.find.byId(nachrichtenId);
        //Markiert die Nachricht als gelesen und speichert es in die Datenbank ab
        ausgewaehlteNachricht.setGelesen(true);
        Ebean.update(ausgewaehlteNachricht);
        //Gibt die Nachricht formatiert zurück
        return ok(nachrichtLesen.render(ausgewaehlteNachricht));
    }

    /**
     * gibt Liste mit allen Nachrichten an User mit userId zurück
     *
     * @param userId die Id des Nutzers
     * @return die Nachrichten des Nutzers
     */
    public static List<Nachricht> getNachrichtenForUser(int userId){
        return Ebean.find(Nachricht.class).where().eq("empfaenger", userId).findList();
    }

    /**
     * gibt die Anzahl ungelesener Nachrichten für einen user zurück
     *
     * @param userId die ID des Nutzers
     * @return die Anzahl der ungelsesen Nachrichten
     */
    public static int getUnreadCountForUser(int userId){
        List<Nachricht> nachrichten = Ebean.find(Nachricht.class)
                .where()
                .and()
                .eq("empfaenger", userId)
                .eq("gelesen", "0")
                .endJunction()
                .findList();

        return nachrichten.size();
    }
}
