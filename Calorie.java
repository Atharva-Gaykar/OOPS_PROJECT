import java.util.Scanner;

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double height = readDouble(sc, "Enter height (cm): ");
        double weight = readDouble(sc, "Enter weight (kg): ");
        int age = (int) readDouble(sc, "Enter age: ");

        String gender = readString(sc, "Enter gender (male/female): ");
        String activity = readString(sc, "Enter activity (sedentary, light, moderate, active, very_active): ");

        double bmr = CalorieCalculator.calculateBMR(height, weight, age, gender);
        double dailyCalories = CalorieCalculator.calculateDailyCalories(bmr, activity);

        System.out.println("\nYour BMR: " + bmr);
        System.out.println("Your Daily Calorie Need: " + dailyCalories);

        String food = readString(sc, "Enter food name: ");
        double calPerGram = readDouble(sc, "Calories per gram of " + food + ": ");

        double gramsAllowed = CalorieCalculator.calculateFoodAllowance(dailyCalories, calPerGram);

        System.out.println("\nYou can eat up to " + gramsAllowed + " grams of " + food);

        sc.close();
    }

    // ---------- SAFE INPUT METHODS ----------
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
        return sc.next();
    }
}
