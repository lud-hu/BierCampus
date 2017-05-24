package controllers;

import com.avaje.ebean.Ebean;
import model.Abzeichen;
import model.User;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by bernward on 22.02.17.
 *
 * In diesem Controller werden alle Requests des Profils abgehandelt die nicht im HomeController verarbeitet werden
 */
public class ProfilController extends Controller {
    /**
     * speichtert das neue ausgewaehlte Abzeichen in der Datenbank
     *
     * @param id die Id des augewaehlten Abzeichnes
     */
    public Result speichereAbzeichen(int id){
        if(id < 0 || id > Ebean.find(Abzeichen.class).findList().size()){
            return badRequest("Dieses Abzeichen existiert nicht");
        }
        //Der gerade eingeloggte Nutzer
        User eingeloggterNutzer = User.find.byId(Integer.parseInt(session("userID")));
        //das ausgewaelte Bild
        Abzeichen ausgewaehltesAbzeichen = Abzeichen.find.byId(id);
        //Abspeichern des ausgewaehlten Abzeichens in der Datenbank
        eingeloggterNutzer.setGewaehltesAbzeichen(ausgewaehltesAbzeichen);
        Ebean.save(eingeloggterNutzer);
        //Leitet den Nutzer auf sein Profil zurueck, damit er sein neues schoenes Abzeichen begutachten kann
        return redirect(routes.HomeController.profil());
    }

    /**
     * die für den Nutzer auswaehlbaren Abzeichen
     *
     * @param userId die ID des Nutzers
     * @return die Liste der auswaehlbaren Abzeichen
     */
    public static List<Abzeichen> getWaehlbareAbzeichenForUser(int userId){
        User eingeloggterNutzer = User.find.byId(userId);

        //Alle im Moment existierenden Abzeichen, die der Nutzer schon freigespielt hat
        List<Abzeichen> waehlbareAbzeichen = Ebean.find(Abzeichen.class)
                .where()
                .le("abzeichen_id", eingeloggterNutzer.getBestandenesLevel().getLevelId())
                .findList();

        //Entfernt das Abzeichen das der Nutzer gerade asugewaehlt hat, sofern überhaupt was in der Liste ist.
        if (waehlbareAbzeichen.size() > 0) waehlbareAbzeichen.remove(eingeloggterNutzer.getGewaehltesAbzeichen());

        return waehlbareAbzeichen;
    }
}
