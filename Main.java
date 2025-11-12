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

    // Sanitize user query for text-based inputs
    public static String sanitizeQuery(String query) {
        if (query == null)
            return "";
        query = query.replaceAll("[^a-zA-Z0-9 ?/]", "");
        query = query.trim().replaceAll("\\s{2,}", " ");
        return query;
    }

    // Redirect query to respective handler
    public static int INPUT(String query, int chooser) {
        int foodIdInt = -1;
        switch (chooser) {
            case 1:
                foodIdInt = ClipTestQuery.handleQuery(query);
                break;
            case 2:
                foodIdInt = ClipImageTestQuery.handleQuery(query);
                break;
            case 3:
                try {
                    Calorie.main(new String[] {});
                } catch (Exception e) {
                    System.out.println("❌ Error running Calorie Calculator: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Invalid choice!");
        }
        return foodIdInt;
    }

    public static void main(String[] args) {
        String filePath = "OOPS_DATA.json";
        List<Dish> dishes = DisplayDishes.loadDishes(filePath);

        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------\nWhat do you want to do?");
        System.out
                .println("Enter the choice num \n1. Text based query\n2. Image based query\n3. Run Calorie Calculator");

        int chooser = sc.nextInt();
        sc.nextLine(); // consume newline

        String userQuery = "";
        if (chooser == 1) {
            System.out.print("Enter your query: ");
            userQuery = sanitizeQuery(sc.nextLine());
        } else if (chooser == 2) {
            System.out.print("Enter image path: ");
            userQuery = sc.nextLine();
        } else if (chooser == 3) {
            try {
                Calorie.main(new String[] {});
            } catch (Exception e) {
                System.out.println("❌ Error running Calorie Calculator: " + e.getMessage());
            }
            sc.close();
            return;
        } else {
            System.out.println("❌ Invalid choice or feature not implemented yet.");
            sc.close();
            return;
        }

        try {
            int id = INPUT(userQuery, chooser);
            if (id != -1)
                DishById.printDishById(dishes, id);
            else
                System.out.println("⚠ Could not fetch food ID from API.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid ID. Please enter a number.");
        }

        sc.close();
    }
}

