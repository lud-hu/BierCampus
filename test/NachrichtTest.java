import model.Nachricht;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Christian on 27.03.2017.
 */
public class NachrichtTest {
    @Test
    public void testNachrichtGetter() {
        Nachricht nachricht = new Nachricht(1,2,"Hallo! :)");
        int empfaenger = 1;
        int absender = 2;
        String inhalt = "Hallo! :)";
        assertEquals(nachricht.getAbsender(),absender);
        assertEquals(nachricht.getEmpfaenger(),empfaenger);
        assertEquals(nachricht.getInhalt(),inhalt);
    }
    @Test
    public void testNachrichtSetGelesen(){
        Nachricht nachricht = new Nachricht(1,2,"Hallo! :)");
        boolean gelesen = false;
        nachricht.setGelesen(gelesen);
        assertEquals(nachricht.isGelesen(),gelesen);
    }
}
