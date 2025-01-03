package fooddatacentral;

import com.fasterxml.jackson.annotation.JsonCreator;

import fooddatacentral.Schemas.FoodNutrient;

/**
 * Represents a specific nutrient in a food item, with its type, value, and
 * units.
 */
public class Nutrient {
    private FoodNutrient nutrient;

    @JsonCreator
    Nutrient(FoodNutrient nutrient) {
        this.nutrient = nutrient;
    }

    /**
     * Gets the unique identifier for the nutrient.
     * 
     * @return the nutrient ID
     */
    public long getId() {
        return nutrient.id;
    }

    /**
     * Gets the type of the nutrient (e.g., protein, carbohydrate).
     * 
     * @return the nutrient type
     */
    public NutrientType getNutrientType() {
        return NutrientType.fromString(nutrient.nutrient.name);
    }

    /**
     * Gets the units of measurement for the nutrient (e.g., grams, milligrams).
     * 
     * @return the units of the nutrient
     */
    public Units getUnits() {
        return Units.fromString(nutrient.nutrient.unitName);
    }

    /**
     * Gets the amount of the nutrient.
     * 
     * @return the nutrient value
     */
    public double getAmount() {
        return nutrient.amount;
    }
}
