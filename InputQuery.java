import java.util.*;

public class InputQuery {

    public static class Query {
        public String getQuery() {
            return "";
        }
    }

    // class for text based query
    public static class TextQuery extends Query {
        private String query;

        public TextQuery(String query) {
            this.query = query;
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------\nHow do you want to Search! \nChoose type 'Image' or 'Text :");

        String _chooser = sc.nextLine();

        // compares whether the input type would be text or image
        if (_chooser.equalsIgnoreCase("Text")) {
            System.out.println("Search");
            String query = sc.nextLine();
            Query t = new TextQuery(query); // takes input of user to TextQuery cls for validation
            // equivalent to base *b = new Child [cpp base pointer]
            ClipTestQuery.handleQuery(t);
        } 
        else if (_chooser.equalsIgnoreCase("Image")) {
            System.out.println("Please enter the image path");
            String query = sc.nextLine();
            Query t = new ImageQuery(query);
            ClipImageTestQuery.handleQuery(t);
        } 
        else {
            System.out.println("Invalid Input! try again\n");
        }
        sc.close();
    }
}
