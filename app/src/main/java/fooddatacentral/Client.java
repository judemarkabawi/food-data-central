package fooddatacentral;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Load FoodDataCentral
        String apiKey = System.getenv("FOOD_API_KEY");
        FoodDataCentral fdc = new FoodDataCentral(apiKey);

        // Get one food by ID
        {
            Food food = fdc.getFood("2144261");
            System.out.println(food.getId());
            System.out.println(food.getBrandOwner());
            System.out.println(food.getDescription());
            System.out.println(food.getIngredients());

            List<Nutrient> nutrients = food.getNutrients();
            for (Nutrient nutrient : nutrients) {
                NutrientType type = nutrient.getNutrientType();
                Units unit = nutrient.getUnits();
                System.out.println("Nutrient type: " + type + ". Amount: " + nutrient.getAmount() + " " + unit);
            }
        }

        //System.exit(0);

        // Get a list of foods by IDs
        List<Food> foods = fdc.getFoods(List.of("2144261", "2120496"));
        for (Food food : foods) {
            System.out.println(food.getId());
            System.out.println(food.getBrandOwner());
            System.out.println(food.getDescription());
            System.out.println(food.getIngredients());
        }

        // Get the 11th page of foods. In this case, returning food 101 to 110
        Iterator<Food> foodsPage = fdc.listFoods(11, 10);
        while (foodsPage.hasNext()) {
            Food food = foodsPage.next();
            System.out.println("LIST FOOD ITEM: " + food.getDescription());
        }

        // Get the 11th page of foods but sort them in ascending order by name of food
        Iterator<Food> foodsPageSorted = fdc.listFoods(
            11, 10, FoodSortableAttributes.NAME, SortOrder.ASCENDING);
        while (foodsPage.hasNext()) {
            Food food = foodsPage.next();
            // Do something with the food
            System.out.println(food.getId());
        }

        // Search for foods by keyword "apple." Using default search, which is searching
        // on the first page, 50 items per page, and results unsorted
        SearchQuery query = new SearchQuery("apple");
        query.setPageSize(10);
        Iterator<Food> foodsSearch = fdc.searchFoods(query);
        while (foodsSearch.hasNext()) {
            Food food = foodsSearch.next();
            // Do something with the food
            System.out.println("SEARCH FOOD ITEM: " + food.getDescription());
        }
    }
}
