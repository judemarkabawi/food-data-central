package fooddatacentral;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

class Schemas {
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Nutrient {
        final int id;
        final String number;
        final String name;
        final int rank;
        final String unitName;

        @JsonCreator
        Nutrient(
                @JsonProperty("id") int id,
                @JsonProperty("number") String number,
                @JsonProperty("name") String name,
                @JsonProperty("rank") int rank,
                @JsonProperty("unitName") String unitName) {
            this.id = id;
            this.number = number;
            this.name = name;
            this.rank = rank;
            this.unitName = unitName;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class FoodNutrient {
        final int id;
        final double amount;
        final int dataPoints;
        final double min;
        final double max;
        final double median;
        final String type;
        final Schemas.Nutrient nutrient;
        // final FoodNutrientDerivation foodNutrientDerivation;
        // final NutrientAnalysisDetails nutrientAnalysisDetails;

        @JsonCreator
        FoodNutrient(
                @JsonProperty("id") int id,
                @JsonProperty("amount") double amount,
                @JsonProperty("dataPoints") int dataPoints,
                @JsonProperty("min") double min,
                @JsonProperty("max") double max,
                @JsonProperty("median") double median,
                @JsonProperty("type") String type,
                @JsonProperty("nutrient") Schemas.Nutrient nutrient) {
            this.id = id;
            this.amount = amount;
            this.dataPoints = dataPoints;
            this.min = min;
            this.max = max;
            this.median = median;
            this.type = type;
            this.nutrient = nutrient;
        }
    }

    /**
     * (Implementation) Abstracts the FoodItem (currently just AbridgedFoodItem and BrandedFoodItem).
     */
    interface FoodItem {
        /* AbridgedFoodItem and BrandedFoodItem -- */
        String getDataType();
        String getDescription();
        int getFdcId();
        String getPublicationDate();

        /* -- AbridgedFoodItem and BrandedFoodItem, but pretend only BrandedFoodItem -- */

        /* This makes it easier than implementing AbridgedFoodNutrient and caring about
         * the distinction, but yeah it will be less efficient if you're only using the nutrients
         * for a food and don't need any other food item info. However, that is very rare because
         * if you're looking for nutrients you're probably going to need the serving size too,
         * in which case you're going to need the full food item info anyway.
         */
        default Optional<List<FoodNutrient>> getFoodNutrients() { return Optional.empty(); };

        /* -- BrandedFoodItem only -- */
        default Optional<String> getAvailableDate() { return Optional.empty(); }
        default Optional<String> getBrandOwner() { return Optional.empty(); }
        default Optional<String> getDataSource() { return Optional.empty(); }
        default Optional<String> getFoodClass() { return Optional.empty(); }
        default Optional<String> getGtinUpc() { return Optional.empty(); }
        default Optional<String> getHouseholdServingFullText() { return Optional.empty(); }
        default Optional<String> getIngredients() { return Optional.empty(); }
        default Optional<String> getModifiedDate() { return Optional.empty(); }
        default Optional<Double> getServingSize() { return Optional.empty(); }
        default Optional<String> getServingSizeUnit() { return Optional.empty(); }
        default Optional<String> getBrandedFoodCategory() { return Optional.empty(); }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class AbridgedFoodItem implements FoodItem {
        final String dataType;
        final String description;
        final int fdcId;
        // final AbridgedFoodNutrient foodNutrients; -- probably don't need now
        final String publicationDate;
        final String brandOwner;
        final String gtinUpc;

        @JsonCreator
        AbridgedFoodItem(
                @JsonProperty("dataType") String dataType,
                @JsonProperty("description") String description,
                @JsonProperty("fdcId") int fdcId,
                @JsonProperty("publicationDate") String publicationDate,
                @JsonProperty("brandOwner") String brandOwner,
                @JsonProperty("gtinUpc") String gtinUpc) {
            this.dataType = dataType;
            this.description = description;
            this.fdcId = fdcId;
            this.publicationDate = publicationDate;
            this.brandOwner = brandOwner;
            this.gtinUpc = gtinUpc;
        }

        @Override
        public String getDataType() {
            return dataType;
        }

        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public int getFdcId() {
            return fdcId;
        }

        @Override
        public String getPublicationDate() {
            return publicationDate;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class BrandedFoodItem implements FoodItem {
        final int fdcId;
        final String availableDate;
        final String brandOwner;
        final String dataSource;
        final String dataType;
        final String description;
        final String foodClass;
        final String gtinUpc;
        final String householdServingFullText;
        final String ingredients;
        final String modifiedDate;
        final String publicationDate;
        final double servingSize;
        final String servingSizeUnit;
        final String brandedFoodCategory;
        final List<FoodNutrient> foodNutrients;
        // List<FoodUpdateLog> foodUpdateLog;
        // LabelNutrients labelNutrients;

        @JsonCreator
        BrandedFoodItem(
                @JsonProperty("fdcId") int fdcId,
                @JsonProperty("availableDate") String availableDate,
                @JsonProperty("brandOwner") String brandOwner,
                @JsonProperty("dataSource") String dataSource,
                @JsonProperty("dataType") String dataType,
                @JsonProperty("description") String description,
                @JsonProperty("foodClass") String foodClass,
                @JsonProperty("gtinUpc") String gtinUpc,
                @JsonProperty("householdServingFullText") String householdServingFullText,
                @JsonProperty("ingredients") String ingredients,
                @JsonProperty("modifiedDate") String modifiedDate,
                @JsonProperty("publicationDate") String publicationDate,
                @JsonProperty("servingSize") double servingSize,
                @JsonProperty("servingSizeUnit") String servingSizeUnit,
                @JsonProperty("brandedFoodCategory") String brandedFoodCategory,
                @JsonProperty("foodNutrients") List<FoodNutrient> foodNutrients) {

            this.fdcId = fdcId;
            this.availableDate = availableDate;
            this.brandOwner = brandOwner;
            this.dataSource = dataSource;
            this.dataType = dataType;
            this.description = description;
            this.foodClass = foodClass;
            this.gtinUpc = gtinUpc;
            this.householdServingFullText = householdServingFullText;
            this.ingredients = ingredients;
            this.modifiedDate = modifiedDate;
            this.publicationDate = publicationDate;
            this.servingSize = servingSize;
            this.servingSizeUnit = servingSizeUnit;
            this.brandedFoodCategory = brandedFoodCategory;
            this.foodNutrients = foodNutrients;
        }

        @Override
        public String getDataType() {
            return dataType;
        }

        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public int getFdcId() {
            return fdcId;
        }
        @Override
        public String getPublicationDate() {
            return publicationDate;
        }

        @Override
        public Optional<List<FoodNutrient>> getFoodNutrients() {
            return Optional.of(foodNutrients);
        }

        @Override
        public Optional<String> getAvailableDate() {
            return Optional.of(availableDate);
        }

        @Override
        public Optional<String> getBrandOwner() {
            return Optional.of(brandOwner);
        }

        @Override
        public Optional<String> getDataSource() {
            return Optional.of(dataSource);
        }

        @Override
        public Optional<String> getFoodClass() {
            return Optional.of(foodClass);
        }

        @Override
        public Optional<String> getGtinUpc() {
            return Optional.of(gtinUpc);
        }

        @Override
        public Optional<String> getHouseholdServingFullText() {
            return Optional.of(householdServingFullText);
        }

        @Override
        public Optional<String> getIngredients() {
            return Optional.of(ingredients);
        }

        @Override
        public Optional<String> getModifiedDate() {
            return Optional.of(modifiedDate);
        }

        @Override
        public Optional<Double> getServingSize() {
            return Optional.of(servingSize);
        }

        @Override
        public Optional<String> getServingSizeUnit() {
            return Optional.of(servingSizeUnit);
        }

        @Override
        public Optional<String> getBrandedFoodCategory() {
            return Optional.of(brandedFoodCategory);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class SearchResultFood implements FoodItem {
        final int fdcId;
        final String dataType;
        final String description;
        // final AbridgedFoodNutrient foodNutrients; -- probably don't need now
        final String foodCode;
        final List<FoodNutrient> foodNutrients;
        final String publicationDate;
        final String scientificName;
        final String brandOwner;
        final String gtinUpc;
        final String ingredients;
        final String ndbNumber;
        final String additionalDescriptions;
        final String allHighlightFields;
        // score is relative score indicating how well the food matches the search criteria.
        final float score;

        @JsonCreator
        SearchResultFood(
                @JsonProperty("fdcId") int fdcId,
                @JsonProperty("dataType") String dataType,
                @JsonProperty("description") String description,
                @JsonProperty("foodCode") String foodCode,
                @JsonProperty("foodNutrients") List<FoodNutrient> foodNutrients,
                @JsonProperty("publicationDate") String publicationDate,
                @JsonProperty("scientificName") String scientificName,
                @JsonProperty("brandOwner") String brandOwner,
                @JsonProperty("gtinUpc") String gtinUpc,
                @JsonProperty("ingredients") String ingredients,
                @JsonProperty("ndbNumber") String ndbNumber,
                @JsonProperty("additionalDescriptions") String additionalDescriptions,
                @JsonProperty("allHighlightFields") String allHighlightFields,
                @JsonProperty("score") float score) {
            this.fdcId = fdcId;
            this.dataType = dataType;
            this.description = description;
            this.foodCode = foodCode;
            this.foodNutrients = foodNutrients;
            this.publicationDate = publicationDate;
            this.scientificName = scientificName;
            this.brandOwner = brandOwner;
            this.gtinUpc = gtinUpc;
            this.ingredients = ingredients;
            this.ndbNumber = ndbNumber;
            this.additionalDescriptions = additionalDescriptions;
            this.allHighlightFields = allHighlightFields;
            this.score = score;
        }

        @Override
        public String getDataType() {
            return dataType;
        }

        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public int getFdcId() {
            return fdcId;
        }

        @Override
        public String getPublicationDate() {
            return publicationDate;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class SearchResult {
        // Ignore the FoodSearchCritera field for now, since this just returns the query parameters
        final int totalHits;
        final int currentPage;
        final int totalPages;
        final List<SearchResultFood> foods;

        @JsonCreator
        SearchResult(
                @JsonProperty("totalHits") int totalHits,
                @JsonProperty("currentPage") int currentPage,
                @JsonProperty("totalPages") int totalPages,
                @JsonProperty("foods") List<SearchResultFood> foods) {
            this.totalHits = totalHits;
            this.currentPage = currentPage;
            this.totalPages = totalPages;
            this.foods = foods;
        }
    }
}
