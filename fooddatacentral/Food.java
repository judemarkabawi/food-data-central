package fooddatacentral;

import java.util.Date;
import java.util.List;

/**
 * Represents a food item with various attributes.
 * 
 * Currently, this just represents a subset of the attributes in a
 * BrandedFoodItem in the Food Data Central API.
 */
public class Food {

    /**
     * Private constructor to prevent instantiation.
     */
    private Food () {

    }
    
    /**
     * Gets the unique identifier for the food item.
     * 
     * @return the food ID
     */
    public long getId() {
        return 0;
    }

    /**
     * Gets the date the food item became available.
     * 
     * @return the availability date
     */
    public Date getAvailableDate() {
        return null;
    }

    /**
     * Gets the brand owner of the food item.
     * 
     * @return the brand owner name
     */
    public String getBrandOwner() {
        return null;
    }

    /**
     * Gets the description of the food item.
     * 
     * @return the food description
     */
    public String getDescription() {
        return null;
    }

    /**
     * Gets the full text description of the household serving size.
     * 
     * @return the serving size description
     */
    public String getHouseholdServingFullText() {
        return null;
    }

    /**
     * Gets the list of ingredients for the food item.
     * 
     * @return a list of ingredients
     */
    public List<String> getIngredients() {
        return null;
    }

    /**
     * Gets the date the food item was last modified.
     * 
     * @return the modification date
     */
    public Date getModifiedDate() {
        return null;
    }

    /**
     * Gets the publication date for the food item.
     * 
     * @return the publication date
     */
    public Date getPublicationDate() {
        return null;
    }

    /**
     * Gets the serving size for the food item.
     * 
     * @return the serving size in the specified units
     */
    public int getServingSize() {
        return 0;
    }

    /**
     * Gets the units for the serving size (e.g., grams, ounces).
     * 
     * @return the units for serving size
     */
    public Units getServingSizeUnits() {
        return null;
    }

    /**
     * Gets the list of nutrients labeled for the food item.
     * 
     * @return a list of labeled nutrients
     */
    public List<Nutrient> getLabelNutrients() {
        return null;
    }
}
