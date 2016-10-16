package jbbmobile.example.com.elementparser;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.Pair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TSVReader {

    public static ArrayList<Element> getElements(Context context, int fileId){
        InputStream tsv = readFile(context, fileId);

        ArrayList<Element> elements = new ArrayList<>();
        Scanner scanner = new Scanner(tsv);
        scanner.useDelimiter("\t|\n");

        while(scanner.hasNext()){
            String id = scanner.next().trim();
            String x = scanner.next().trim();
            String y = scanner.next().trim();
            String name = scanner.next();
            String description = scanner.next();
            String imageDescription = scanner.next().trim();

            Log.i("TSVReader", "Element " + id + ": \n\t[" + name + "]\n\t[" + x + "] [" + y + "]\n\t[" + description + "]\n");

            if(x.length() == 0 || y.length() == 0)
                x = y = "0";

            Pair<Double, Double> coordinates = new Pair(Double.parseDouble(x), Double.parseDouble(y));

            elements.add(new Element(Integer.parseInt(id), coordinates, name, description));
        }

        return elements;
    }

    private static InputStream readFile(Context context, int fileId){
        Resources resources = context.getResources();
        InputStream file;
        file = resources.openRawResource(fileId);

        return file;
    }
}
