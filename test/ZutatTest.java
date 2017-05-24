import model.Zutat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Christian on 27.03.2017.
 */
public class ZutatTest {

    @Test
    public void testZutatGetter() {
        Zutat zutat = new Zutat(1,"Mais schmeckt gut","Mais","Mais.jpg");
        int zutatId = 1;
        String beschreibung = "Mais schmeckt gut";
        String name = "Mais";
        String bild = "Mais.jpg";
        assertEquals(zutat.getName(),name);
        assertEquals(zutat.getBeschreibung(), beschreibung);
        assertEquals(zutat.getZutatId(), zutatId);
        assertEquals(zutat.getBild(), bild);
    }
}
