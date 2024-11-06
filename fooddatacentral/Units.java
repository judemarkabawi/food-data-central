package fooddatacentral;

/**
 * Enum representing different units of measurement for nutrients and serving sizes.
 */
public enum Units {
    MICROGRAMS("µg"),
    MILLIGRAMS("mg"),
    GRAMS("g"),
    KILOCALORIES("kcal"),
    OUNCES("oz"),
    TEASPOONS("tsp"),
    TABLESPOONS("tbsp"),
    CUPS("cup");

    private final String unit;

    /**
     * Constructor for the Units enum.
     * 
     * @param unit the string representation of the unit
     */
    Units(String unit) {
        this.unit = unit;
    }

    /**
     * Gets the string representation of the unit.
     * 
     * @return the unit as a string
     */
    public String getUnitString() {
        return unit;
    }
}
