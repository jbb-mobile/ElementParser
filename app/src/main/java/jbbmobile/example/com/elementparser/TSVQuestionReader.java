package jbbmobile.example.com.elementparser;


import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.Pair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TSVQuestionReader {
    private static int alternativesQuantity;

    public static ArrayList<Question> getQuestion(Context context, int fileId){
        InputStream tsv = readFile(context, fileId);

        ArrayList<Question> questions = new ArrayList<>();
        Scanner scanner = new Scanner(tsv);
        scanner.useDelimiter("\t|\n");
        alternativesQuantity = Integer.parseInt(scanner.next());

        while(scanner.hasNext()){
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

            questions.add(new Question(Integer.parseInt(idQuestion), description, alternatives, correctAnswer));
        }

        return questions;
    }

    private static InputStream readFile(Context context, int fileId){
        Resources resources = context.getResources();
        InputStream file;
        file = resources.openRawResource(fileId);

        return file;
    }
}
