package model;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * Helper Klasse für Freunde
 * verzeichnet wer mit wem befreundet ist
 * Ein Eintrag mit userA und userB -> userA und userB sind befreundet
 *
 * Created by Ludwig on 09.03.2017.
 */
@Entity
@Table(name="freundeverzeichnis")
public class FreundeVerzeichnis extends Model {

    @Id
    private int freundeVerzeichnisId;

    @ManyToOne
    private User userA;

    @ManyToOne
    private User userB;

    public FreundeVerzeichnis(User userA, User userB) {
        this.userA = userA;
        this.userB = userB;
    }

    public User getFreundByID(int userID){
        if(userA.getUserID() == userID){
            return userB;
        }else if(userB.getUserID() == userID){
            return userA;
        } else {
            return null;
        }
    }
}
