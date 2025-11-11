import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

class NutritionFacts {
    private int carbohydrates_g;
    private int protein_g;
    private int fat_g;
    private int fiber_g;
    private List<String> vitamins;
    private List<String> minerals;

    @Override
    public String toString() {
        return String.format(
                "[Carbs: %dg, Protein: %dg, Fat: %dg, Fiber: %dg, Vitamins: %s, Minerals: %s]",
                carbohydrates_g, protein_g, fat_g, fiber_g, vitamins, minerals);
    }
}

class Dish {
    private int id;
    private String name;
    private String course;
    private String diet;
    private List<String> ingredients;
    private String flavor_profile;
    private NutritionFacts nutrition_facts;
    private String recipe;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getDiet() {
        return diet;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getFlavorProfile() {
        return flavor_profile;
    }

    public NutritionFacts getNutritionFacts() {
        return nutrition_facts;
    }

    public String getRecipe() {
        return recipe;
    }
}

class DisplayDishes {
    public static List<Dish> loadDishes(String filePath) {
        try {
            Gson gson = new Gson();
            Type dishListType = new TypeToken<List<Dish>>() {
            }.getType();
            return gson.fromJson(new FileReader(filePath), dishListType);
        } catch (Exception e) {
            System.out.println("❌ Error loading dishes: " + e.getMessage());
            return null;
        }
    }
}

class DishById {
    public static void printDishById(List<Dish> dishes, int id) {
        if (dishes == null) {
            System.out.println("No data loaded!");
            return;
        }

        Dish found = null;
        for (Dish dish : dishes) {
            if (dish.getId() == id) {
                found = dish;
                break;
            }
        }

        if (found == null) {
            System.out.println("No dish found with ID " + id);
            return;
        }

        System.out.println("===========================================");
        System.out.printf("%-25s | ID: %d%n", found.getName(), found.getId());
        System.out.println("===========================================");
        System.out.println("Course: " + found.getCourse());
        System.out.println("Diet: " + found.getDiet());
        System.out.println("\nIngredients:");
        for (String ingredient : found.getIngredients()) {
            System.out.println("  - " + ingredient);
        }

        System.out.println("\nFlavor Profile: " + found.getFlavorProfile());
        System.out.println("\nNutrition Facts:");
        System.out.println("  " + found.getNutritionFacts());

        System.out.println("\nRecipe:");
        System.out.println("  " + found.getRecipe());

        System.out.println("===========================================");
    }
}

public class Main {
    public static void main(String[] args) {
        String filePath = "OOPS_DATA.json";
        List<Dish> dishes = DisplayDishes.loadDishes(filePath);

        // if (args.length == 0) {
        //     System.out.println("Usage: java Main <dish_id>");
        //     return;
        // }

        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------\nWhat do you want to do?");

        System.out.println("1. Text\n2. Image\n3. Calorie Calculator");
        int chooser = sc.nextInt();
        sc.nextLine(); // consume newline

        try {
            int id = InputQuery.INPUT("", chooser); // Example usage; replace "" and 1 with actual inputs
            DishById.printDishById(dishes, id);
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid ID. Please enter a number.");
        }
    }
}

// cd "c:\Users\adity\OneDrive\Desktop\project\" ; if ($?) { javac -cp
// gson-2.10.1.jar Main.java } ; if ($?) { java -cp ".;gson-2.10.1.jar" Main
// $DishID }
// use above command to run -> Change according to directory btw