package fooddatacentral;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * FoodDataCentral class that wraps the REST API provided by the FDC.
 * Must be instantiated with an API key to utilize.
 */
class FoodDataCentral {

    /**
     * Constructor for the FoodDataCentral class.
     * Requires an API key for the FDC API in order to use.
     * @param apiKey to make REST calls with
     */
    public FoodDataCentral(String apiKey) {

    }

    /**
     * Retrieves the specified food item from the FDC database of Branded food items if it is present.
     * @param fdcId the id of the food item to retrieve
     * @return an Optional of the Food item. Present if the item was returned from the API, empty if not.
     */
    Optional<Food> getFood(String fdcId) {
        return null;
    }

    /**
     * Retrieves up to 20 food items specified by FDC IDs provided.
     * Invalid IDs or items not found are omitted from the returned values.
     * An empty iterator will be returned if none are found.
     * @param fdcIds List of fdc ids related to food items to retrieve.
     * @return an Iterator of all found food items
     * @throws IllegalArgumentException if the parameter is null or exceeds a size of 20 items.
     */
    Iterator<Food> getFoods(List<String> fdcIds) {
        return null;
    }

    /**
     * Retrieve a list of food items for pagination.
     * Specify the page number to return. The default page size is 50.
     * Returns an empty iterator for any invalid page number.
     * @param pageNumber which page to return
     * @return an iterator containing all the food items on the corresponding page for the currently set page size.
     */
    Iterator<Food> listFoods(int pageNumber) {
        return null;
    }

    /**
     * Retrieve a list of food items for pagination.
     * Specify the page number to return. The default page size is 50.
     * Specify what attribute of the food to order by and how to order it.
     * Returns an empty iterator for any invalid page number.
     * @param pageNumber which page to return
     * @param sortBy which attribute to sort by
     * @param sortOrder the order to sort the food
     * @return an iterator containing all the food items on the corresponding page for the currently set page size.
     */
    Iterator<Food> listFoods(int pageNumber, FoodSortableAttributes sortBy, SortOrder sortOrder) {
        return null;
    }

    /**
     * Returns current page size for listFoods.
     * @return int representing the current number of items on a page returned in listFoods
     */
    public int getPageSize() {return 50;}

    /**
     * Sets page size returned in listFoods to specified number.
     * Note that the performance of listFoods may be impacted by larger numbers.
     * @param pageSize number that each page will return in listFoods. Cannot be negative.
     * @throws IllegalArgumentException if pageSize < 0.
     */
    public void setPageSize(int pageSize) {}


    /**
     * Searches the list via the given query.
     * @param query how to query the food database
     * @return iterator of the found food items. If not found, will be empty
     * @throws NullPointerException for null argument
     */
    Iterator<Food> searchFoods(SearchQuery query) {
        return null;
    }
}
