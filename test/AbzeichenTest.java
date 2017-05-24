
import model.Abzeichen;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by Christian on 27.03.2017.
 */



public class AbzeichenTest {
    @Test
    public void testAbzeichenGetter(){
        Abzeichen abzeichen = new Abzeichen(0, "Neueinsteiger", "Du hast noch nichts freigespielt!", "neueinsteiger.png");
        int abzeichenId = 0;
        String name = "Neueinsteiger";
        String beschreibung = "Du hast noch nichts freigespielt!";
        String bild = "neueinsteiger.png";
        assertEquals(abzeichen.getAbzeichenId(), abzeichenId);
        assertEquals(abzeichen.getName(),name);
        assertEquals(abzeichen.getBeschreibung(),beschreibung);
        assertEquals(abzeichen.getBild(),bild);
    }
}
