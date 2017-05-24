package controllers;

import com.avaje.ebean.Ebean;
import model.FreundeVerzeichnis;
import model.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.freundeSuche;
import views.html.profilDarstellung;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernward on 22.02.17.
 *
 * In diesem Controller werden alle Requests abgehandelt die nicht im HomeController verarbeitet werden
 */
public class FreundeController extends Controller {
    /*
     * Zeigt das Profil des ausgewaehlten Freundes an
     */
    public Result zeigeProfilAn(){
        //liest die UserID aus dem Request aus
        int freundID = Integer.parseInt(request().getQueryString("freundID"));
        //liest das Userobjekt aus der datenbank
        User ausgewaehlterFreund = User.find.byId(freundID);
        //Rendert das HTML-Snippet mit dem Profil
        return ok(profilDarstellung.render(ausgewaehlterFreund));
    }

    /*
     * Fuegt einen neuen Freund hinzu
     */
    public Result fuegeFreundHinzu(){
        //liest die UserID aus dem Request aus
        int userIDA = Integer.parseInt(request().getQueryString("nutzerID"));
        //liest den User aus der Session aus
        int userIDB = Integer.parseInt(session("userID"));
        //speichtert die neue Freundschaft in der Datenbank ab
        FreundeVerzeichnis freundschaft = new FreundeVerzeichnis(User.find.byId(userIDA), User.find.byId(userIDB));
        Ebean.save(freundschaft);
        //gibt dem Client Feedback
        return redirect(routes.HomeController.freunde());
    }

    /*
     * Zeigt die Suchmaske an in der der Nutzer neue Freunde suchen kann
     */
    public Result sucheFreunde(){
        //Zwischenspeicher fuer alle Freundschaften
        List<FreundeVerzeichnis> alleFreunschaften = Ebean.find(FreundeVerzeichnis.class).findList();
        //sucht alle Nutzer mit denen der eingeloggte Nutzer noch nicht befreundet ist, auch hier ist der Performancegott wieder einmal nicht zufrieden
        List<User> nichtFreunde = Ebean.find(User.class).findList();
        nichtFreunde.remove(User.find.byId(Integer.parseInt(session("userID"))));
        for(FreundeVerzeichnis freundschaft : alleFreunschaften){
            if(freundschaft.getFreundByID(Integer.parseInt(session("userID"))) != null){
                nichtFreunde.remove(freundschaft.getFreundByID(Integer.parseInt(session("userID"))));
            }
        }
        //Redndert das HTML-Snippet mit der Suchmaske
        return ok(freundeSuche.render(nichtFreunde));
    }

    /**
     * Sucht alle Freunde des nutzers
     *
     * @param userId die Id des Nutzers , dessen Freunde gesucht sind
     * @return eine Liste der Freunde des Nutzers
     */
    public static List<User> getFreundeDesUsers(int userId){

        //Die Freunde des Nutzers kommen in diese Liste
        List<User> freundeDesUsers = new ArrayList<User>();

        //Zwischenspreicher mit dem Verzeichniss aller Freundesverbindungen des Users
        List<FreundeVerzeichnis> verzeichnisPuffer = Ebean.find(FreundeVerzeichnis.class)
                .where()
                .or()
                    .eq("user_a_userid", userId)
                    .eq("user_b_userid", userId)
                .endJunction()
                .findList();


        //Schreibt die Nutzer mit denen der eingeloggte User befreundet ist in die Liste der Freunde
        for (FreundeVerzeichnis f : verzeichnisPuffer) {
            if (f.getFreundByID(Integer.parseInt(session("userID"))) != null) {
                freundeDesUsers.add(f.getFreundByID(Integer.parseInt(session("userID"))));
            }
        }

        return freundeDesUsers;
    }
}
