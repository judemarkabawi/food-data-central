package fooddatacentral;

import java.util.Arrays;

/**
 * Enum representing different units of measurement for nutrients and serving
 * sizes.
 */
public enum Units {
    /**
     * Unit in micrograms (µg).
     */
    MICROGRAMS("µg"),
    /**
     * Unit in milligrams (mg).
     */
    MILLIGRAMS("mg"),
    /**
     * Unit in grams (g).
     */
    GRAMS("g"),
    /**
     * Unit in milliliters (mL).
     */
    KILOCALORIES("kcal"),
    /**
     * Unit in ounces (oz).
     */
    OUNCES("oz"),
    /**
     * Unit in teaspoons (tsp).
     */
    TEASPOONS("tsp"),
    /**
     * Unit in tablespoons (tbsp).
     */
    TABLESPOONS("tbsp"),
    /**
     * Unit in cups (cup).
     */
    CUPS("cup"),

    /**
     * Any other unit
     */
    UNKNOWN("unknown unit");

    private final String unitName;

    /**
     * Constructor for the Units enum.
     * 
     * @param unit the string representation of the unit
     */
    Units(String unit) {
        this.unitName = unit;
    }

    /**
     * Gets the string representation of the unit.
     * 
     * @return the unit as a string
     */
    public String getUnitString() {
        return unitName;
    }

    public static Units fromString(String unitName) {
        return Arrays.stream(Units.values()).filter(unit -> unit.unitName.equals(unitName)).findFirst()
                .orElse(Units.UNKNOWN);
    }
}
