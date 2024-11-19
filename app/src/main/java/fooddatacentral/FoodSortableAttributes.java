package fooddatacentral;

/**
 * Enum representing what attributes of food you can sort by and search by (i.e. columns).
 */
public enum FoodSortableAttributes {
    /**
     * Sortable by the name of the food.
     */
    NAME("name"),
    /**
     * Sortable by the brand of the food.
     */
    BRAND("brand"),
    /**
     * Sortable by the serving size of the food.
     */
    SERVING_SIZE("serving size"),
    /**
     * Sortable by the number of calories in the food.
     */
    CALORIES("calories");

    private final String attribute;

    FoodSortableAttributes(String attribute) {this.attribute = attribute;}

    /**
     * Gets the string representation of the food attribute
     * @return the attribute string
     */
    public String getAttributeString() {return this.attribute;}
}
