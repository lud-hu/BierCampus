import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import model.*;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;

import static play.test.Helpers.*;
import static org.junit.Assert.*;


/**
 *
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 *
 */
public class ApplicationTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertEquals(2, a);
    }

    @Test
    public void freundeSucheRender() {
        List<User> users = new ArrayList<User>();
        User peter = new User("Peter","peter@web.de","123456");
        User hans = new User("Hans","hans@web.de","123456");
        users.add(peter);
        users.add(hans);
        Content html = views.html.freundeSuche.render(users);
        assertEquals("text/html", html.contentType());
        assertTrue(html.body().contains("Peter"));
        assertTrue(html.body().contains("Hans"));
    }

}
