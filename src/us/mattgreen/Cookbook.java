package us.mattgreen;

import java.util.ArrayList;
import java.util.Arrays;

public class Cookbook {

    // Hold all the meals that are read in from the file
    //private Meal[] meals = new Meal[100];
    private ArrayList<Meal> meals = new ArrayList<>(67);
    // Hold the next (empty) index in the array
    private int i = 0;
    // Holds calories for each meal type
    private int breakfastCalories = 0;
    private int lunchCalories = 0;
    private int dinnerCalories = 0;
    private int dessertCalories = 0;

    //Holds the lowest and highest number of calories for each meal type and stores each value in an array
    private int breakfastLow = 10000;
    private int breakfastHigh = 0;
    private int[] breakfastIndividual = new int[13];
    private int breakfastItems = 0;
            
    private int lunchLow = 10000;
    private int lunchHigh = 0;
    private int[] lunchIndividual = new int[14];
    private int lunchItems = 0;    

    private int dinnerLow = 10000;
    private int dinnerHigh = 0;
    private int[] dinnerIndividual = new int[25];
    private int dinnerItems = 0;    

    private int dessertLow = 10000;
    private int dessertHigh = 0;   
    private int[] dessertIndividual = new int[16];
    private int dessertItems = 0;    
    public void addElementWithStrings(String mealTypeStr, String mealNameStr, String caloriesStr) {
        MealType mealType;

        // Do we have room in the array for one more?
        if (i <= meals.size()) {

            // Find the correct enum using a switch? Or use .fromValue() instead?
            switch (mealTypeStr) {
                case "Breakfast":
                    mealType = MealType.BREAKFAST;
                    break;
                case "Lunch":
                    mealType = MealType.LUNCH;
                    break;
                case "Dinner":
                    mealType = MealType.DINNER;
                    break;
                case "Dessert":
                    mealType = MealType.DESSERT;
                    break;
                default:
                    mealType = MealType.DINNER;
                    System.out.println("Meal Creation Error: Invalid Meal Type " + mealTypeStr + ", defaulted to Dinner.");
            }

            int calories;

            if (caloriesStr.matches("-?\\d+(\\.\\d+)?")) {
                calories = Integer.parseInt(caloriesStr);
            } else {
                calories = 100;
                System.out.println("Meal Creation Error: Invalid Calories " + caloriesStr + ", defaulted to 100.");
            }
            switch (mealTypeStr) {
                case "Breakfast":
                    breakfastCalories += calories;
                    if (calories > breakfastHigh) {
                        breakfastHigh = calories;
                    }
                    else if (calories < breakfastLow) {
                        breakfastLow = calories;
                    }
                    breakfastIndividual[breakfastItems] = calories;
                    breakfastItems++;
                    break;
                case "Lunch":
                    lunchCalories += calories;
                    if (calories > lunchHigh) {
                        lunchHigh = calories;
                    }
                    else if (calories < lunchLow) {
                        lunchLow = calories;
                    }
                    lunchIndividual[lunchItems] = calories;
                    lunchItems++;                    
                    break;
                case "Dinner":
                    dinnerCalories += calories;
                    if (calories > dinnerHigh) {
                        dinnerHigh = calories;
                    }
                    else if (calories < dinnerLow) {
                        dinnerLow = calories;
                    }
                    dinnerIndividual[dinnerItems] = calories;
                    dinnerItems++;                     
                    break;
                case "Dessert":
                    dessertCalories += calories;
                    if (calories > dessertHigh) {
                        dessertHigh = calories;
                    }
                    else if (calories < dessertLow) {
                        dessertLow = calories;
                    }
                    dessertIndividual[dessertItems] = calories;
                    dessertItems++;                      
                    break;
                default:
                    break;
            }
            meals.add(i++, new Meal(mealType, mealNameStr, calories));
        } else {
            System.out.println("Meal Creation Error: Items exceeded system size.  File has " + i + ", while the system can only handle " + meals.size() + ".");
        }
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void printAllMeals() {
        for (Meal item : meals) {
            if (item != null) {
                System.out.println(item);
            }
            else {
            }
        }
        System.out.println("Meal\t\tTotal\tMean\tMin\tMax\tMedian");  
        Arrays.sort(breakfastIndividual);
        System.out.println("Breakfest\t" + breakfastCalories + "\t" + (breakfastCalories / 13) + "\t" + breakfastLow + "\t" + breakfastHigh + "\t" + breakfastIndividual[6]);
        Arrays.sort(lunchIndividual);
        System.out.println("Lunch\t\t" + lunchCalories  + "\t" + (lunchCalories / 14) + "\t" + lunchLow + "\t" + lunchHigh + "\t" + lunchIndividual[6]);
        Arrays.sort(dinnerIndividual);
        System.out.println("Dinner\t\t" + dinnerCalories + "\t" + (dinnerCalories / 25) + "\t" + dinnerLow + "\t" + dinnerHigh + "\t" + dinnerIndividual[13]);
        Arrays.sort(dessertIndividual);
        System.out.println("Dessert\t\t" + dessertCalories  + "\t" + (dessertCalories / 16) + "\t" + dessertLow + "\t" + dessertHigh + "\t" + dessertIndividual[7]);
    }

    public void printMealsByType(MealType mealType) {
        for (Meal item : meals) {
            if (item != null && item.getMealType() == mealType) {
                System.out.println(item);
            }
        }
        System.out.println();
        System.out.println("Meal\t\tTotal\tMean\tMin\tMax\tMedian");          
        switch (mealType) {
            case BREAKFAST:
                Arrays.sort(breakfastIndividual);
                System.out.println("Breakfest\t" + breakfastCalories + "\t" + (breakfastCalories / 13) + "\t" + breakfastLow + "\t" + breakfastHigh + "\t" + breakfastIndividual[6]);
                break;
            case LUNCH:
                Arrays.sort(lunchIndividual);
                System.out.println("Lunch\t\t" + lunchCalories  + "\t" + (lunchCalories / 14) + "\t" + lunchLow + "\t" + lunchHigh + "\t" + lunchIndividual[6]);
                break;
            case DINNER:
                Arrays.sort(dinnerIndividual);
                System.out.println("Dinner\t\t" + dinnerCalories + "\t" + (dinnerCalories / 25) + "\t" + dinnerLow + "\t" + dinnerHigh + "\t" + dinnerIndividual[13]);
                break;
            case DESSERT:
                Arrays.sort(dessertIndividual);
                System.out.println("Dessert\t\t" + dessertCalories  + "\t" + (dessertCalories / 16) + "\t" + dessertLow + "\t" + dessertHigh + "\t" + dessertIndividual[7]);                       
                break;
            default:
                break;
        }
   }

    public void printByNameSearch(String s) {
        // Maybe add a message if no match found?
        for (Meal item : meals) {
            // Maybe make this case-insensitive?
            if (item != null && item.getItem().indexOf(s) >= 0) {
                System.out.println(item);
            }
        }
    }
}
