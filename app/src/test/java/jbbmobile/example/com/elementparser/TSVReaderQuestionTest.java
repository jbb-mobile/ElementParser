package jbbmobile.example.com.elementparser;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static junit.framework.Assert.assertEquals;


public class TSVReaderQuestionTest {
    private InputStream file;
    private int fileId;
    private Context context;

    @Before
    public void setUp() {
        Context context;
        context = InstrumentationRegistry.getTargetContext();
        this.context = context;

        Resources resources = context.getResources();
        fileId = R.raw.tabela_questoes;
        file = resources.openRawResource(fileId);
    }

    @Test
    public void testIfFileIsNotEmpty() throws Exception {
        assert file.available() > 0;
    }

    @Test
    public void testIfTheFirstLineHasTheNumberOfAlternatives() throws Exception {
        int alternativesQuantity;

        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\t|\n");
        alternativesQuantity = Integer.parseInt(scanner.next());

        //The number of questions alternatives won't be less than 2 and the first question will have the id equals 1
        assert alternativesQuantity > 1;
    }

    @Test
    public void testIfQuestionsAreCorrectRead() throws Exception {
        int alternativesQuantity;
        Question question;

        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\t|\n");
        alternativesQuantity = Integer.parseInt(scanner.next());

        String idQuestion = scanner.next();
        String description = scanner.next().trim();
        String a = scanner.next().trim();
        String b = scanner.next().trim();
        String c = scanner.next().trim();
        String d = scanner.next().trim();
        String e = scanner.next().trim();
        String correctAnswer = scanner.next().trim();

        Map<String, String> alternatives = new HashMap<String, String>();
        alternatives.put("a", a);
        alternatives.put("b", b);
        alternatives.put("c", c);
        alternatives.put("d", d);
        alternatives.put("e", e);

        question = new Question(Integer.parseInt(idQuestion), description, alternatives, correctAnswer);

        assertEquals(question.getId(), 1);
    }

}
