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

    /**
     * Generates default query for the given string.
     * @param query search term, may include search operators
     */
    public SearchQuery(String query) {

    }

    /**
     * Generates query by setting all values manually.
     * @param query search term, may include search operators
     * @param pageNumber page to return of found values
     * @param pageSize number of items to return per page
     * @param queryField what field the query will apply to
     * @param sortBy what to sort the returned values by
     * @param sortOrder what order to sort the returned values by
     */
    public SearchQuery(String query, int pageNumber, int pageSize, FoodSortableAttributes queryField,
                       FoodSortableAttributes sortBy, SortOrder sortOrder){

    }

    /**
     * Returns the current query string
     * @return query string
     */
    public String getQuery() {return null;}

    /**
     * Sets query to given value.
     * @param query new query search term
     */
    public void setQuery(String query) {}

    /**
     * Retrieves current search's page number
     * @return page number
     */
    public int getPageNumber() {return -1;}

    /**
     * Sets new page number to retrieve.
     * @param pageNumber new page number
     */
    public void setPageNumber(int pageNumber) {}

    /**
     * Returns current page size.
     * @return page size
     */
    public int getPageSize() {return -1;}

    /**
     * Sets page size to new value. Cannot be negative.
     * @param pageSize new page size
     * @throws IllegalArgumentException if pageSize < 0
     */
    public void setPageSize(int pageSize) {}

    /**
     * Returns query field that is currently being searched. Null if there currently is none.
     * @return current query field/attribute. Null if there currently is none.
     */
    public FoodSortableAttributes getQueryField() {return null;}

    /**
     * Sets query field to the given value.
     * @param queryField attribute to apply the query/search term to.
     * @throws NullPointerException if queryField is null.
     */
    public void setQueryField(FoodSortableAttributes queryField) {}

    /**
     * Resets the query field to the default value. This will apply query to all available attributes/fields.
     */
    public void setQueryFieldDefault() {}

    /**
     * Retrieves current sort by field. Null if there is none
     * @return attribute to sort by
     */
    public FoodSortableAttributes getSortBy() {return null;}

    /**
     * Set sort by to new value.
     * @param sortBy new column/attribute to sort by
     */
    public void setSortBy(FoodSortableAttributes sortBy) {}

    /**
     * Returns current sort order. Null if there is none.
     * @return current sort order.
     */
    public SortOrder getSortOrder() {return null;}

    /**
     * Set new sort order. Only possible is sortBy is set.
     * @param sortOrder new sort order
     * @throws IllegalStateException if there is no current "sort by"
     */
    public void setSortOrder(SortOrder sortOrder) {}

}
