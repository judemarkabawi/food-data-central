package fooddatacentral;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a specific nutrient in a food item, with its type, value, and units.
 */
public class Nutrient {
    private int number;
    private String name;
    private double amount;
    private String unitName;
    private String derivationCode;
    private String derivationDescription;

    @JsonCreator
    private Nutrient(
            @JsonProperty("number") int number,
            @JsonProperty("name") String name,
            @JsonProperty("amount") double amount,
            @JsonProperty("unitName") String unitName,
            @JsonProperty("derivationCode") String derivationCode,
            @JsonProperty("derivationDescription") String derivationDescription) {
        this.number = number;
        this.name = name;
        this.amount = amount;
        this.unitName = unitName;
        this.derivationCode = derivationCode;
        this.derivationDescription = derivationDescription;
    }
    
    /**
     * Gets the unique identifier for the nutrient.
     * 
     * @return the nutrient ID
     */
    public long getId() {
        return 0;
    }

    /**
     * Gets the type of the nutrient (e.g., protein, carbohydrate).
     * 
     * @return the nutrient type
     */
    public NutrientType getNutrientType() {
        return NutrientType.PROTEIN;
    }

    /**
     * Gets the units of measurement for the nutrient (e.g., grams, milligrams).
     * 
     * @return the units of the nutrient
     */
    public Units getUnits() {
        return Units.GRAMS;
    }

    /**
     * Gets the amount of the nutrient.
     * 
     * @return the nutrient value
     */
    public float getAmount() {
        return 0;
    }
}
