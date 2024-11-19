package fooddatacentral;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fooddatacentral.Schemas.BrandedFoodItem;

/**
 * FoodDataCentral class that wraps the REST API provided by the FDC.
 * Must be instantiated with an API key to utilize.
 */
public class FoodDataCentral {
    private String apiKey;

    private String withApiKeyParam(String url) {
        return url + "?api_key=" + apiKey;
    }

    /**
     * Constructor for the FoodDataCentral class.
     * Requires an API key for the FDC API in order to use.
     * 
     * @param apiKey to make REST calls with
     * @throws IllegalArgumentException If the API key is invalid
     * @throws IOException              If there is an IO error during the web
     *                                  request
     * @throws InterruptedException     If there is a thread error during the web
     *                                  request
     */
    public FoodDataCentral(String apiKey) throws IOException, InterruptedException {
        this.apiKey = apiKey;

        // Try a simple API request to ensure valid API key
        HttpResponse<String> response = Utility.sendApiRequest(withApiKeyParam(Utility.API_FOOD_LIST_URL));
        if (response.statusCode() == 403) {
            throw new IllegalArgumentException("Invalid argument to FoodDataCentral constructor: Bad API key");
        }
        // TODO: There's a chance some other error on the server side is thrown and we
        // end up not throwing here, fix later
    }

    /**
     * Retrieves the specified food item from the FDC database of Branded food items
     * if it is present.
     * 
     * @param fdcId the id of the food item to retrieve
     * @throws InvalidArgumentException If the ID supplied is not a valid food item
     *                                  ID
     * @return The food item
     */
    public Food getFood(String fdcId) throws IOException, InterruptedException {
        // Request
        HttpResponse<String> response = Utility.sendApiRequest(withApiKeyParam(Utility.API_FOOD_URL + fdcId));

        // Parse
        ObjectMapper mapper = new ObjectMapper();
        Schemas.BrandedFoodItem food = mapper.readValue(response.body(), Schemas.BrandedFoodItem.class);
        return new Food(food, apiKey);
    }

    /**
     * Retrieves up to 20 food items specified by FDC IDs provided.
     * Invalid IDs or items not found are omitted from the returned values.
     * An empty iterator will be returned if none are found.
     * 
     * @param fdcIds List of fdc ids related to food items to retrieve.
     * @return an Iterator of all found food items
     * @throws IllegalArgumentException if the parameter is null or exceeds a size
     *                                  of 20 items.
     */
    public List<Food> getFoods(List<String> fdcIds) throws IOException, InterruptedException {
        // Request
        String queryIds = String.join(",", fdcIds);
        HttpResponse<String> response = Utility
                .sendApiRequest(withApiKeyParam(Utility.API_FOODS_URL) + "&fdcIds=" + queryIds);

        System.out.println(response.body());

        // Parse
        ObjectMapper mapper = new ObjectMapper();
        List<BrandedFoodItem> brandedFoods = mapper.readValue(response.body(),
                new TypeReference<List<BrandedFoodItem>>() {
                });
        List<Food> foods = brandedFoods.stream().map(food -> new Food(food, apiKey)).collect(Collectors.toList());
        return foods;
    }

    /**
     * Retrieve a list of food items for pagination.
     * Specify the page number to return. The default page size is 50.
     * Returns an empty iterator for any invalid page number.
     * 
     * @param pageNumber which page to return
     * @return an iterator containing all the food items on the corresponding page
     *         for the currently set page size.
     */
    public Iterator<Food> listFoods(int pageNumber) {
        return null;
    }

    /**
     * Retrieve a list of food items for pagination.
     * Specify the page number to return. The default page size is 50.
     * Specify what attribute of the food to order by and how to order it.
     * Returns an empty iterator for any invalid page number.
     * 
     * @param pageNumber which page to return
     * @param sortBy list of which attribute to sort by, prioritizes first item in list, then proceeding items
     * @param sortOrder the order to sort the food
     * @return an iterator containing all the food items on the corresponding page for the currently set page size.
     */
    public Iterator<Food> listFoods(int pageNumber, List<FoodSortableAttributes> sortBy, SortOrder sortOrder) {
        return null;
    }

    /**
     * Returns current page size for listFoods.
     * 
     * @return int representing the current number of items on a page returned in
     *         listFoods
     */
    public int getPageSize() {
        return 50;
    }

    /**
     * Sets page size returned in listFoods to specified number.
     * Note that the performance of listFoods may be impacted by larger numbers.
     * 
     * @param pageSize number that each page will return in listFoods. Cannot be
     *                 negative.
     * @throws IllegalArgumentException if {@code pageSize < 0}.
     */
    public void setPageSize(int pageSize) {
    }

    /**
     * Searches the list via the given query.
     * 
     * @param query how to query the food database
     * @return iterator of the found food items. If not found, will be empty
     * @throws NullPointerException for null argument
     */
    public Iterator<Food> searchFoods(SearchQuery query) {
        return null;
    }
}
