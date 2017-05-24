import model.Rang;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Christian on 27.03.2017.
 */
public class RangTest {

    @Test
    public void testRangGetter() {
        Rang rang = new Rang(1,"Könner",100);
        int rangId = 1;
        String bezeichnung = "Könner";
        int xpGrenze = 100;
        assertEquals(rang.getBezeichnung(), bezeichnung);
        assertEquals(rang.getRangId(), rangId);
        assertEquals(rang.getXpGrenze(), xpGrenze);
    }
}
