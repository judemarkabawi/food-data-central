import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import fooddatacentral.*;

public class Client {
    
    public static void main(String[] args) {
        // Load FoodDataCentral
        FoodDataCentral fdc = new FoodDataCentral("[YOUR_API_KEY]");
        
        // Get one food by ID
        Optional<Food> obtainedFoodData = fdc.getFood("1105919");
        if (obtainedFoodData.isPresent()) {
            Food food = obtainedFoodData.get();
            List<Nutrient> nutrients = food.getLabelNutrients();
            for (Nutrient nutrient : nutrients) {
                NutrientType type = nutrient.getNutrientType();
                Units unit = nutrient.getUnits();
                System.out.println("Nutrient type: " + type + ". Amount: " + nutrient.getAmount() + " " + unit);
            }
        } else {
            // Food not found or API key is invalid
        }

        // Get a list of foods by IDs
        Iterator<Food> foods = fdc.getFoods(List.of("1105919", "1105918", "1105917"));
        while (foods.hasNext()) {
            Food food = foods.next();
            // Do something with the food
        }

        // Setting a page size of 10 so each page will contain 10 foods
        fdc.setPageSize(10);

        // Get the 11th page of foods. In this case, returning food 101 to 110
        Iterator<Food> foodsPage = fdc.listFoods(11);
        while (foodsPage.hasNext()) {
            Food food = foodsPage.next();
            // Do something with the food
        }

        // Get the 11th page of foods but sort them in ascending order by name of food
        Iterator<Food> foodsPageSorted = fdc.listFoods(
            11, FoodSortableAttributes.NAME, SortOrder.ASCENDING);
        while (foodsPage.hasNext()) {
            Food food = foodsPage.next();
            // Do something with the food
        }

        // Search for foods by keyword "apple." Using default search, which is searching 
        // on the first page, 50 items per page, and results unsorted
        SearchQuery query = new SearchQuery("apple");
        Iterator<Food> foodsSearch = fdc.searchFoods(query);
        while (foodsSearch.hasNext()) {
            Food food = foodsSearch.next();
            // Do something with the food
        }
    }
}
