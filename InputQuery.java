import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class InputQuery {

    public static class Query {
        public String getQuery() {
            return "";
        }
    }

    public static String sanitizeQuery(String query) {
        if (query == null)
            return "";
        // 1 Remove special characters except '?' and '/'
        // Keep letters, digits, spaces, '?', and '/'
        query = query.replaceAll("[^a-zA-Z0-9 ?/]", "");
        // 2 Trim leading/trailing spaces and collapse multiple spaces into one
        query = query.trim().replaceAll("\\s{2,}", " ");
        return query;
    }

    // class for text based query
    public static class TextQuery extends Query {
        private String query;

        public TextQuery(String query) {
            this.query = sanitizeQuery(query);
            // string validations
            // 1. removes special chars except '?'
            // 2. trims extra spaces
        }

        @Override
        public String getQuery() {
            // returns it to ClipTestQuery
            return query;
        }
    }

    // class for image based query
    public static class ImageQuery extends Query {
        private String query;

        public ImageQuery(String query) {
            this.query = query;
        }

        @Override
        public String getQuery() {
            // returns it to ClipTestQuery
            return query;
        }
    }

    public static int INPUT(String query, int chooser) {
        int foodIdInt = -1;
        // Scanner sc = new Scanner(System.in);
        // System.out.println("-----------------------------\nHow do you want to Search!");

        // System.out.println("1. Text\n2. Image\n3. Calorie Calculator");
        // int chooser = sc.nextInt();
        // sc.nextLine(); // consume newline

        // compares whether the input type would be text or image
        switch (chooser) {
            case 1:
                // System.out.println("Search");
                // String query = sc.nextLine();
                Query t = new TextQuery(query); // takes input of user to TextQuery cls for validation
                // equivalent to base *b = new Child [cpp base pointer]
                foodIdInt = ClipTestQuery.handleQuery(t);
                // to FileReaderWriter class
                // String foodOutput = function(foodIdInt);
                break;

            case 2:
                // System.out.println("Please enter the image path");
                // query = sc.nextLine();
                t = new ImageQuery(query);
                foodIdInt = ClipImageTestQuery.handleQuery(t);
                // to FileReaderWriter class
                // String foodOutput = function(foodIdInt);
                break;

            // case 3:
            //     // Call Calorie Calculator
            //     Calorie.main(args);
            //     break;
            default:
                System.out.println("Invalid Input! try again\n");

                break;
        }
        return foodIdInt;
        // sc.close();
    }
}