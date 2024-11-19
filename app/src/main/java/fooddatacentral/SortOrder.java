package fooddatacentral;

/**
 * Enum representing options for sorting food items in pagination/list.
 */
public enum SortOrder {
    /**
     * Sort in ascending order.
     */
    ASCENDING("ascending"),
    /**
     * Sort in descending order.
     */
    DESCENDING("descending"),
    /**
     * Do not sort.
     */
    NONE("none");

    private final String sortOrder;

    SortOrder(String sortOrder) {this.sortOrder = sortOrder;}

    /**
     * Gets string representation of the sort order
     * @return sort order
     */
    public String getSortOrderString() {
        return this.sortOrder;
    }
}
