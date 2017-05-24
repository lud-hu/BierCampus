package controllers;

import com.avaje.ebean.Ebean;
import model.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * In diesem Controller stehen die Ursprungsseiten die der Nutzer nicht ueber HTTP-Requests aufruft
 */
public class HomeController extends Controller {
    /**
     * Diese Hilfsmethode gibt die Links in der MenueBar vor
     *
     * @return die verfuegbaren Seiten aus, in der Reihenfolge wie sie im Menue angezeigt werden
     * <p>
     * ist nicht schoen geloest, weil hardcoded und hohes coupling, aber habe bis jetzt keinen besseren Weg gefunden
     */
    private List<String> getSeiten() {
        List<String> seiten = new ArrayList<String>();
        //reihenfolge ist: Was zuerst in der Liste steht wir zuerst angezeigt
        seiten.add("spiel");
        seiten.add("biergewinnt");
        seiten.add("highscore");
        seiten.add("nachrichten");
        seiten.add("freunde");
        seiten.add("profil");
        seiten.add("logout");
        return seiten;
    }

    /**
     * wertet die Session aus
     *
     * @return Gibt zurück ob es gerade eine gültige Session gibt
     */
    private boolean isSessionAvailable() {
        if (session("userID") == null | session("userID") == "") {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Hier werden die Texte der MenueBar erstellt
     *
     * @return Gibt die Texte in der MenueBar zurueck in der korrekten Reihenfolge
     */
    public static List<String> getSeitenNamen() {
        List<String> seiten = new ArrayList<String>();
        //Username für Menüleiste holen
        int userId = Integer.parseInt(session("userID"));
        String username = User.find.byId(userId).getName();

        //Anzahl ungelesener Nachrichten für User holen
        int unreadMessages = NachrichtenController.getUnreadCountForUser(userId);

        //reihenfolge ist: Was zuerst in der Liste steht wir zuerst angezeigt
        seiten.add("Brauerei");
        seiten.add("Bier Gewinnt");
        seiten.add("Highscore");

        if (unreadMessages > 0) seiten.add("Nachrichten (" + unreadMessages + ")");
        else seiten.add("Nachrichten");

        seiten.add("Freunde");
        seiten.add(username);
        seiten.add("Logout");
        return seiten;
    }

    /*
     * die Seite zum Registrieren wird geladen
     */
    public Result register() {
        return ok(register.render(Form.form(Register.class)));
    }

    /*
     * musste leider hier hingecodet werden
     * diese Methode prozessiert die Daten die der Nutzer zum registieren angegeben hat
     */
    public Result registrieren() {
        Form<Register> registerForm = Form.form(Register.class).bindFromRequest();
        Register daten = registerForm.get();
        if (registerForm.hasErrors()) {
            return badRequest(register.render(Form.form(Register.class)));
        } else {
            User neuerNutzer = new User(daten.getUsername(), daten.getEmail(), daten.getPassword());
            List<User> Nutzer = Ebean.find(User.class).where().eq("name", daten.getUsername()).findList();
            if (!Nutzer.isEmpty()) {
                return badRequest("Der Nutzer existiert schon in der Datenbank");
            } else {
                Ebean.save(neuerNutzer);
                Integer neuerNutzerID = neuerNutzer.getUserID();
                session().clear();
                session("userID", Integer.toString(neuerNutzerID));
                return redirect(routes.HomeController.spiel());
            }
        }
    }

    /**
     * Die Seite zum einloggen wird geladen
     */
    public Result login() {

        if (isSessionAvailable()) return redirect(routes.HomeController.spiel());

        //login nach diesem Tut implementiert:
        //https://www.playframework.com/documentation/2.2.x/JavaGuide4
        return ok(login.render(Form.form(Login.class)));
    }

    /*
     * musste leider hier hingekodet werden
     * diese Methode prozessiert die Daten die der Nutzer zum einloggen angegeben hat
     */
    public Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        Login form = loginForm.get();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(Form.form(Login.class)));
        } else {
            //SUCHT IN DER DATENBANK NACH USER MIT GESUCHTEM USERNAME UND PASSWORT
            List<User> Users = Ebean.find(User.class)
                    .where().eq("email", form.getEmail())
                    .findList();

            if (!Users.isEmpty()) {

                User myUser = Users.get(0);

                //Wenn das Passwort übereinstimmt
                if (HashHelper.checkPassword(form.getPassword(), myUser.getPasswort())) {
                    //WENN USER VORHANDEN
                    session().clear();
                    session("userID", Integer.toString(myUser.getUserID()));

                    //Promillezahl anpassen an das was verloren wurde
                    BigDecimal promilleVerlust = myUser.getPromilleVerlust();
                    //verhinert negative Promillezahlen
                    if (promilleVerlust.compareTo(myUser.getPromille()) > 0) {
                        myUser.setPromille(new BigDecimal("0"));
                    } else {
                        myUser.setPromille(myUser.getPromille().subtract(promilleVerlust));
                    }

                    //last logged in Zeit im User ändern:
                    myUser.setLastLogin(new Date());
                    Ebean.update(myUser);

                    return redirect(
                            //Leitet den Nutzer zum Spiel weiter
                            routes.HomeController.spiel()
                    );
                } else {
                    return badRequest("Passwort falsch");
                }
            } else {
                return badRequest("User nicht in der Datenbank gefunden");
            }
        }
    }

    /**
     * Die Seite fuer das BierGewinnt wird geladen
     */
    public Result bierGewinnt() {

        if (!isSessionAvailable()) return redirect(routes.HomeController.login());

        String titel = "Bier Gewinnt!";
        String dateiPfad = "biergewinnt";
        int groesseSpalte = 8;
        int groesseZeile = 7;
        return ok(bierGewinnt.render(titel, dateiPfad, getSeiten(), groesseSpalte, groesseZeile));
    }

    /**
     * Die Seite mit den Freunden wird geladen
     */
    public Result freunde() {

        if (!isSessionAvailable()) return redirect(routes.HomeController.login());

        String titel = "Deine Freunde";
        String dateiPfad = "freunde";

        return ok(freunde.render(titel, dateiPfad, getSeiten(), FreundeController.getFreundeDesUsers(Integer.parseInt(session("userID")))));
    }

    /**
     * Die Seite die den Hoghscore anzeigt wird geladen
     */
    public Result highscore() {

        if (!isSessionAvailable()) return redirect(routes.HomeController.login());

        String titel = "Der Highscore";
        String dateiPfad = "highscore";

        return ok(highscore.render(titel, dateiPfad, getSeiten(), HighscoreController.getHighscoreList()));
    }

    /**
     * Die Seite die Nachrichten anzeigen wird geladen
     */
    public Result nachrichten() {

        if (!isSessionAvailable()) return redirect(routes.HomeController.login());

        String titel = "Deine Nachrichten";
        String dateiPfad = "nachrichten";

        return ok(nachrichten.render(titel, dateiPfad, getSeiten(), NachrichtenController.getNachrichtenForUser(Integer.parseInt(session("userID")))));
    }

    /**
     * Die Seite die das Profil anzeigt wird geladen
     */
    public Result profil() {

        if (!isSessionAvailable()) return redirect(routes.HomeController.login());

        String titel = "Dein Profil";
        String dateiPfad = "profil";
        //Der gerade eingeloggte Nutzer
        User eingeloggterNutzer = User.find.byId(Integer.parseInt(session("userID")));

        return ok(profil.render(titel, dateiPfad, getSeiten(), eingeloggterNutzer, ProfilController.getWaehlbareAbzeichenForUser(Integer.parseInt(session("userID")))));

    }

    /**
     * Die Seite die das Spiel anzeigt wird geladen
     */
    public Result spiel() {

        if (!isSessionAvailable()) return redirect(routes.HomeController.login());

        String titel = "BierCampus2";
        String dateiPfad = "spiel";

        User user1 = User.find.byId(Integer.parseInt(session("userID")));
        // Bestandenes Level +1
        Level level = Level.find.byId(user1.getBestandenesLevel().getLevelId() + 1);

        if (Ebean.find(Level.class).findList().size() == user1.getBestandenesLevel().getLevelId()) {
            return redirect(routes.HomeController.infomeldung("Fertig!", "Hey du hast schon alles durchgespielt, super! Sei gespannt auf die nächsten Level. Prost! ;)"));
            //return badRequest("Du bist fertig mit dem Spiel :)");
        }

        List<Zutat> zutaten = SpielController.getShuffledZutatenList(level.getLevelId());

        return ok(spiel.render(titel, dateiPfad, getSeiten(), zutaten, level.getLevelName()));
    }

    /*
     * Der Logout des Spielers wir durchgeführt
     */
    public Result logout() {
        session().clear();
        return redirect(routes.HomeController.login());
    }

    public Result infomeldung(String titel, String message) {
        return ok(infomeldung.render(titel, getSeiten(), message));
    }

    /*
     * musste leider hier hingecodet werden
     * diese Methode laedt das Form in das der Nutzer seine Nachricht eingetragen hat
     */
    public Result nachrichtForm() {
        return ok(nachrichtSchreiben.render(Form.form(NutzerNachricht.class)));
    }

    /*
     * musste leider hier hingecodet werden
     * diese Methode prozessiert die Daten die der Nutzer zum absenden einer Nachricht angegeben hat
     */
    public Result nachrichtAbsenden() {
        Form<NutzerNachricht> nachrichtForm = Form.form(NutzerNachricht.class).bindFromRequest();
        NutzerNachricht erhalteneNachricht = nachrichtForm.get();
        if (nachrichtForm.hasErrors()) {
            return badRequest(nachrichtSchreiben.render(Form.form(NutzerNachricht.class)));
        } else {
            List<User> hilfsliste = Ebean.find(User.class).where().eq("name", erhalteneNachricht.getEmpfaenger()).findList();
            if (hilfsliste.isEmpty()) {
                return redirect(routes.HomeController.infomeldung("User nicht da...", "Mist, diesen User gibt es nicht. Bitte versuche es nochmal."));
                //return badRequest("Diesen Empfaenger gibt es nicht");
            } else {
                Nachricht zwischenspeicher = new Nachricht(hilfsliste.get(0).getUserID(), Integer.parseInt(session("userID")), erhalteneNachricht.getInhalt());
                Ebean.save(zwischenspeicher);
            }
        }
        return redirect(routes.HomeController.nachrichten());
    }

    //Ist halt hohes Coupling aber anders hat der Code nicht funktioniert.  Play Framework...
    public static class Login {
        //Variablen UNBEDINGT Public lassen!!! Keine Ahnung warum, aber sonst funktionierts nicht. Play Framework...
        public String email;
        public String password;

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

    public static class Register {
        public String username;
        public String email;
        public String password;

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

    public static class NutzerNachricht {
        public String empfaenger;
        public String inhalt;

        public String getEmpfaenger() {
            return empfaenger;
        }

        public String getInhalt() {
            return inhalt;
        }
    }
}