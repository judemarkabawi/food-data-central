package fooddatacentral;

/**
 * Search query class utilized for the search function in FoodDataCentral.
 * If not specified, the search will default to the following:
 * 50 items per page,
 * page 1,
 * will search all fields,
 * sort by no fields,
 * no sort order
 */
public class SearchQuery {
    // constants
    private static int DEFAULT_PAGE_SIZE = 50;
    private static int DEFAULT_PAGE_NUMBER = 1;

    // private variables
    private String query = "";
    private int pageNumber = DEFAULT_PAGE_NUMBER;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private FoodSortableAttributes queryField = null;
    private FoodSortableAttributes sortBy = null;
    private SortOrder sortOrder = SortOrder.NONE;

    /**
     * Generates default query for the given string.
     * @param query search term, may include search operators
     */
    public SearchQuery(String query) {
        this.query = query;
    }

    /**
     * Generates query by setting all values manually.
     * @param query search term, may include search operators
     * @param pageNumber page to return of found values
     * @param pageSize number of items to return per page
     * @param queryField what field the query will apply to
     * @param sortBy list of attributes to sort the returned values by, prioritizes first item then proceeding items
     * @param sortOrder what order to sort the returned values by
     * @throws IllegalArgumentException if either pageNumber or pageSize are &lt;= 0
     * @throws NullPointerException if the sortBy is null
     */
    public SearchQuery(String query, int pageNumber, int pageSize, FoodSortableAttributes queryField,
                       FoodSortableAttributes sortBy, SortOrder sortOrder){
        if (pageNumber < 1 || pageSize < 1) {
            throw new IllegalArgumentException("Page number and size must be > 0.");
        }
        if (sortBy == null) {
            throw new NullPointerException("sortBy cannot be a null pointer.");
        }

        this.query = query;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.queryField = queryField;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
    }

    /**
     * Returns the current query string
     * @return query string
     */
    public String getQuery() {
        return this.query;
    }

    /**
     * Sets query to given value.
     * @param query new query search term
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Retrieves current search's page number
     * @return page number
     */
    public int getPageNumber() {
        return this.pageNumber;
    }

    /**
     * Sets new page number to retrieve.
     * @param pageNumber new page number
     * @throws IllegalArgumentException if page number &lt;= 0
     */
    public void setPageNumber(int pageNumber) {
        if (pageNumber < 1) {
            throw new IllegalArgumentException("Page number must be > 0.");
        }
        this.pageNumber = pageNumber;
    }

    /**
     * Returns current page size.
     * @return page size
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * Sets page size to new value. Cannot be negative.
     * @param pageSize new page size
     * @throws IllegalArgumentException if pageSize &lt;= 0
     */
    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must be > 0.");
        }
        this.pageSize = pageSize;
    }

    /**
     * Returns query field that is currently being searched. Null if there currently is none.
     * @return current query field/attribute. Null if there currently is none.
     */
    public FoodSortableAttributes getQueryField() {
        return this.queryField;
    }

    /**
     * Sets query field to the given value.
     * @param queryField attribute to apply the query/search term to.
     * @throws NullPointerException if queryField is null.
     */
    public void setQueryField(FoodSortableAttributes queryField) {
        if (queryField == null) {
            throw new NullPointerException("Cannot pass in null query field.");
        }
        this.queryField = queryField;
    }

    /**
     * Resets the query field to the default value. This will apply query to all available attributes/fields.
     */
    public void setQueryFieldToDefault() {
        this.queryField = null;
    }

    /**
     * Retrieves current sort by field. Null if there is none
     * @return attribute to sort by
     */
    public FoodSortableAttributes getSortBy() {
        return this.sortBy;
    }

    /**
     * Set sort by to new value.
     * @param sortBy column/attribute to sort by
     */
    public void setSortBy(FoodSortableAttributes sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * Returns current sort order. Null if there is none.
     * @return current sort order.
     */
    public SortOrder getSortOrder() {
        return this.sortOrder;
    }

    /**
     * Set new sort order. Only possible is sortBy is set.
     * @param sortOrder new sort order
     * @throws IllegalStateException if there is no current "sort by"
     */
    public void setSortOrder(SortOrder sortOrder) {
        if (this.sortBy == null) {
            throw new IllegalStateException("Cannot set sort order without 'sort by'.");
        }
        this.sortOrder = sortOrder;
    }

    public String getParameterString() {
        String query = "";
        query += "&query=" + this.query;
        query += "&dataType=Branded";
        query += "&pageNumber=" + this.pageNumber;
        query += "&pageSize=" + this.pageSize;
        if (this.queryField != null) {
            query += "&queryField=" + this.queryField.getAttributeString();
        }
        if (this.sortBy != null) {
            query += "&sortBy=" + this.sortBy.getAttributeString();
        }
        if (this.sortBy != null && this.sortOrder != null) {
            query += "&sortOrder=" + this.sortOrder.getSortOrderString();
        }

        return query;
    }

}
