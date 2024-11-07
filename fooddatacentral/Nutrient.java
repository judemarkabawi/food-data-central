package fooddatacentral;

/**
 * Represents a specific nutrient in a food item, with its type, value, and units.
 */
public class Nutrient {

    /**
     * Private constructor to prevent instantiation.
     */
    private Nutrient() {

    }
    
    /**
     * Gets the unique identifier for the nutrient.
     * 
     * @return the nutrient ID
     */
    long getId() {
        return 0;
    }

    /**
     * Gets the type of the nutrient (e.g., protein, carbohydrate).
     * 
     * @return the nutrient type
     */
    NutrientType getNutrientType() {
        return NutrientType.PROTEIN;
    }

    /**
     * Gets the units of measurement for the nutrient (e.g., grams, milligrams).
     * 
     * @return the units of the nutrient
     */
    Units getUnits() {
        return Units.GRAMS;
    }

    /**
     * Gets the amount of the nutrient.
     * 
     * @return the nutrient value
     */
    float getAmount() {
        return 0;
    }
}
