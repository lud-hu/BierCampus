import model.QuizFrage;
import org.junit.Test;

import java.util.ArrayList;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Christian on 27.03.2017.
 */
public class QuizFrageTest {
    @Test
    public void testQuizFrageGetter() {
        QuizFrage quizFrage = new QuizFrage("Frage 1", "Ist das die richtige Frage?", "Nein1", "Nein2", "Nein3", "Ja", "Das hast du richtig beantwortet");
        String name = "Frage 1";
        String frage = "Ist das die richtige Frage?";
        String falscheAntwort1 = "Nein1";
        String falscheAntwort2 = "Nein2";
        String falscheAntwort3 = "Nein3";
        String richtigeAntwort = "Ja";
        String aufloesung = "Das hast du richtig beantwortet";
        List<String> falscheAntworten = new ArrayList<>();
        falscheAntworten.add(falscheAntwort1);
        falscheAntworten.add(falscheAntwort2);
        falscheAntworten.add(falscheAntwort3);
        assertEquals(quizFrage.getFalscheAntworten(),falscheAntworten);
        assertEquals(quizFrage.getName(),name);
        assertEquals(quizFrage.getAufloesung(),aufloesung);
        assertEquals(quizFrage.getFrage(),frage);
        assertEquals(quizFrage.getRichtigeAntwort(),richtigeAntwort);
    }
}
