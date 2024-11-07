
# FoodData Central: Java API for Obtaining FDC Food Data

## Overview
The fooddatacentral Java API wraps the [FoodData Central REST API](https://fdc.nal.usda.gov/api-spec/fdc_api.html#/) provided by USDA. 
With this API, clients can easily access information regarding a variety of foods without needing to
manually do HTTP requests.

## Requirements
1. The API will work on any platform that supports Java and with JDK 8 or above installed. 
2. The API requires a valid API key. Please sign up for a key at [USDA's API key signup link](https://fdc.nal.usda.gov/api-key-signup.html).

## Installation
```bash
git clone https://github.com/judemarkabawi/food-data-central
```

Or, simply download zip at [the repository](https://github.com/judemarkabawi/food-data-central).

## Sample Usage
1. Loading the main FoodDataCentral class

    ```java
    FoodDataCentral fdc = new FoodDataCentral("[YOUR_API_KEY]");
    ```

2. Get one food by ID, maps to `GET /v1/food/{fdcId}`

    ```java
    Optional<Food> obtainedFoodData = fdc.getFood("1105919");
    if (obtainedFoodData.isPresent()) {
        Food food = obtainedFoodData.get();
        // Do something with the Food instance
    } else {
        // Food not found or API key is invalid
    }
    ```

3. Get a list of foods by IDs, maps to `GET/POST /v1/foods`

    ```java
    Iterator<Food> foods = fdc.getFoods(List.of("1105919", "1105918", "1105917"));
    while (foods.hasNext()) {
        Food food = foods.next();
        // Do something with the food
    }
    ```

4. Setting page size for pagination

    ```java
    fdc.setPageSize(10);
    ```

5. List foods, maps to `GET/POST /v1/foods/list`

    ```java
    // Get the 11th page of foods. In this case, returning food 101 to 110
    Iterator<Food> foodsPage = fdc.listFoods(11);
    while (foodsPage.hasNext()) {
        Food food = foodsPage.next();
        // Do something with the food
    }
    ```

6. More list foods, maps to `GET/POST /v1/foods/list`

    ```java
    // Get the 11th page of foods but sort them in ascending order by name of food
    Iterator<Food> foodsPageSorted = fdc.listFoods(11, FoodSortableAttributes.NAME, SortOrder.ASCENDING);
    while (foodsPage.hasNext()) {
        Food food = foodsPage.next();
        // Do something with the food
    }
    ```

7. Search foods, maps to `GET/POST /v1/foods/search`

    ```java
    // Search for foods by keyword "apple." Using default search, which is searching 
    // on the first page, 50 items per page, and results unsorted
    SearchQuery query = new SearchQuery("apple");
    Iterator<Food> foodsSearch = fdc.searchFoods(query);
    while (foodsSearch.hasNext()) {
        Food food = foodsSearch.next();
        // Do something with the food
    }
    ```