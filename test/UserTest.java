import model.Abzeichen;
import model.Level;
import model.Rang;
import model.User;
import org.intellij.lang.annotations.Flow;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Christian on 27.03.2017.
 */
public class UserTest {
    
    @Test
    public void testUserGetter() {
        User user = new User("Peter","peter@web.de","123456");
        String name = "Peter";
        String email = "peter@web.de";
        String passwort = "123456";
        assertEquals(user.getName(),name);
        assertEquals(user.getEmail(),email);
        assertEquals(user.getPasswort(),passwort);
    }

    @Test
    public void testUserSetErfahrung() {
        User user = new User("Peter","peter@web.de","123456");
        user.setErfahrung(100);
        int erfahrung = 100;
        assertEquals(user.getErfahrung(),erfahrung);
    }

    @Test
    public void testUserSetPromille() {
        User user = new User("Peter","peter@web.de","123456");
        BigDecimal promille = new BigDecimal(9.9);
        user.setPromille(promille);
        assertEquals(user.getPromille(),promille);
    }

    @Test
    public void testUserSetLevel() {
        User user = new User("Peter","peter@web.de","123456");
        Level level = new Level(0, "noch nicht gespielt");
        user.setBestandenesLevel(level);
        assertEquals(user.getBestandenesLevel(),level);
    }

    @Test
    public void testUserSetGewaehltesAbzeichen() {
        User user = new User("Peter","peter@web.de","123456");
        Abzeichen abzeichen = new Abzeichen(1,"Pils","Das ist das beste Abzeichen", "Pils.png");
        user.setGewaehltesAbzeichen(abzeichen);
        assertEquals(user.getGewaehltesAbzeichen(),abzeichen);
    }
}
