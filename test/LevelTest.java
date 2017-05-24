import model.Level;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Christian on 27.03.2017.
 */
public class LevelTest {
    @Test
    public void testLevelGetter() {
        Level level = new Level(0, "noch nicht gespielt");
        int levelId = 0;
        String levelName = "noch nicht gespielt";
        assertEquals(level.getLevelId(),levelId);
        assertEquals(level.getLevelName(),levelId + " - " + levelName);
    }
}