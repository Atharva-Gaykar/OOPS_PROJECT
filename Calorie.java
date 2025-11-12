import java.util.*;

class CalorieCalculator {

    public static double calculateBMR(double height, double weight, int age, String gender) {
        gender = gender.toLowerCase();

        if (gender.equals("male")) {
            return 66.5 + (13.7 * weight) + (5 * height) - (6.8 * age);
        } else if (gender.equals("female")) {
            return 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
        } else {
            throw new IllegalArgumentException("Invalid gender");
        }
    }

    public static double getActivityFactor(String level) {
        switch (level.toLowerCase()) {
            case "sedentary": return 1.2;
            case "light": return 1.375;
            case "moderate": return 1.55;
            case "active": return 1.725;
            case "very_active": return 1.9;
            default: throw new IllegalArgumentException("Invalid activity level");
        }
    }

    public static double calculateDailyCalories(double bmr, String activityLevel) {
        return bmr * getActivityFactor(activityLevel);
    }

    public static double calculateFoodAllowance(double dailyCalories, double calPerGram) {
        return dailyCalories / calPerGram;
    }
}

public class Calorie {

    
    private static final Map<String, Double> foodCaloriesMap = new HashMap<>();

    static {
       
foodCaloriesMap.put("burger", 6.2);
        foodCaloriesMap.put("butter naan", 3.6);
        foodCaloriesMap.put("chapati", 3.4);
        foodCaloriesMap.put("chai (masala tea)", 1.2);
        foodCaloriesMap.put("chole bhature", 5.8);
        foodCaloriesMap.put("dal makhani", 4.9);
        foodCaloriesMap.put("dhokla", 2.7);
        foodCaloriesMap.put("fried rice", 5.1);
        foodCaloriesMap.put("idli", 2.3);
        foodCaloriesMap.put("jalebi", 6.5);
        foodCaloriesMap.put("kaathi rolls", 4.9);
        foodCaloriesMap.put("kadai paneer", 5.4);
        foodCaloriesMap.put("kulfi", 4.9);
        foodCaloriesMap.put("masala dosa", 4.6);
        foodCaloriesMap.put("momos", 3.8);
        foodCaloriesMap.put("paani puri", 3.2);
        foodCaloriesMap.put("pakode (pakora)", 4.8);
        foodCaloriesMap.put("pav bhaji", 4.5);
        foodCaloriesMap.put("pizza", 5.2);
        foodCaloriesMap.put("samosa", 5.0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double height = readDouble(sc, "Enter height (cm): ");
        double weight = readDouble(sc, "Enter weight (kg): ");
        int age = (int) readDouble(sc, "Enter age: ");
        sc.nextLine();
        String gender = readString(sc, "Enter gender (male/female): ");
        String activity = readString(sc, "Enter activity (sedentary, light, moderate, active, very_active): ");

        double bmr = CalorieCalculator.calculateBMR(height, weight, age, gender);
        double dailyCalories = CalorieCalculator.calculateDailyCalories(bmr, activity);

        System.out.println("\nYour BMR: " + bmr);
        System.out.println("Your Daily Calorie Need: " + dailyCalories);

        String food = readString(sc, "\nEnter food name: ").toLowerCase();

        if (!foodCaloriesMap.containsKey(food)) {
            System.out.println("⚠ Sorry, '" + food + "' not found in database. Please add it manually.");
            sc.close();
            return;
        }

        double calPerGram = foodCaloriesMap.get(food);
        double gramsAllowed = CalorieCalculator.calculateFoodAllowance(dailyCalories, calPerGram);

        System.out.printf("\n%s has %.2f calories per gram.%n", food, calPerGram);
        System.out.printf("You can eat up to %.2f grams of %s per day.%n", gramsAllowed, food);

        sc.close();
    }

    
    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                return sc.nextDouble();
            } else {
                System.out.println("Invalid number. Try again.");
                sc.next(); // clear wrong input
            }
        }
    }

    private static String readString(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}


