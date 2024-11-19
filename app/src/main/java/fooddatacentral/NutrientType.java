package fooddatacentral;

import java.util.Arrays;

/**
 * Enum representing various types of nutrients that a food item can contain.
 */
public enum NutrientType {
    ENERGY("Energy"),
    ADDED_SUGARS("Sugars, added"),
    TRANS_FAT("Fatty acids, total trans"),
    FIBER("Fiber, total dietary"),
    IRON("Iron, Fe"),
    PROTEIN("Protein"),
    CHOLESTEROL("Cholesterol"),
    SATURATED_FAT("Fatty acids, total saturated"),
    TOTAL_SUGARS("Total Sugars"),
    SODIUM("Sodium, Na"),
    VITAMIN_D("Vitamin D (D2 + D3), International Units"),
    POTASSIUM("Potassium, K"),
    CARBOHYDRATE("Carbohydrate, by difference"),
    TOTAL_FAT("Total lipid (fat)"),
    CALCIUM("Calcium, Ca"),
    UNKNOWN("Unknown");

    /** According to FDC FoodData API */
    private final String nutrientName;

    /**
     * Constructor for the Units enum.
     * 
     * @param unit the string representation of the unit
     */
    NutrientType(String nutrientName) {
        this.nutrientName = nutrientName;
    }

    public static NutrientType fromString(String nutrientName) {
        return Arrays.stream(NutrientType.values()).filter(nutrient -> nutrient.nutrientName.equals(nutrientName))
                .findFirst().orElse(NutrientType.UNKNOWN);
    }
}
