package fooddatacentral;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.fasterxml.jackson.databind.ObjectMapper;

import fooddatacentral.Schemas.BrandedFoodItem;
import fooddatacentral.Schemas.FoodItem;

/**
 * Represents a food item with various attributes.
 * 
 * Currently, this just represents a subset of the attributes in a
 * BrandedFoodItem in the Food Data Central API.
 */
public class Food {
    private FoodItem foodItem;
    private String apiKey;

    Food(FoodItem foodItem, String apiKey) {
        this.foodItem = foodItem;
    }

    /**
     * Parse a date string coming from the FoodData API
     * 
     * @param date Date in "month/day/year" format
     * @return Parsed date as a {@code LocalDate}
     */
    private static LocalDate parseApiDate(String date) {
        var formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return LocalDate.parse(date, formatter);
    }

    /**
     * Wrapper around accessing maybe-there properties that need an API call to fill
     * in the BrandedFoodItem before trying again.
     */
    private <T> T getPropertyWithLoad(Function<FoodItem, Optional<T>> getter) {
        Optional<T> value = getter.apply(foodItem);
        if (value.isPresent()) {
            return value.get();
        } else {
            BrandedFoodItem fullFoodItem = loadFullFoodItem();
            return getter.apply(fullFoodItem)
                    .orElseThrow(() -> new IllegalStateException("Property not found in BrandedFoodItem"));
        }
    }

    /**
     * Loads the full food item type (just BrandedFoodItem) when a call is
     * made to a method requiring full info.
     */
    private BrandedFoodItem loadFullFoodItem() {
        // fix later
        URI uri = URI.create(Utility.API_FOOD_URL + foodItem.getFdcId() + "?api_key=" + apiKey);
        HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).build();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            Schemas.BrandedFoodItem food = mapper.readValue(response.body(), Schemas.BrandedFoodItem.class);
            this.foodItem = food;
            return food;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the unique identifier for the food item.
     * 
     * @return the food ID
     */
    public long getId() {
        return foodItem.getFdcId();
    }

    /**
     * Gets the date the food item became available.
     * 
     * @return the availability date
     */
    public LocalDate getAvailableDate() {
        String availableDate = getPropertyWithLoad(FoodItem::getAvailableDate);
        return parseApiDate(availableDate);
    }

    /**
     * Gets the brand owner of the food item.
     * 
     * @return the brand owner name
     */
    public String getBrandOwner() {
        return getPropertyWithLoad(FoodItem::getBrandOwner);
    }

    /**
     * Gets the description of the food item.
     * 
     * @return the food description
     */
    public String getDescription() {
        return foodItem.getDescription();
    }

    /**
     * Gets the full text description of the household serving size.
     * 
     * @return the serving size description
     */
    public String getHouseholdServingFullText() {
        return getPropertyWithLoad(FoodItem::getHouseholdServingFullText);
    }

    /**
     * Gets the ingredients for the food item.
     * 
     * @return The list of ingredients
     */
    public String getIngredients() {
        return getPropertyWithLoad(FoodItem::getIngredients);
    }

    /**
     * Gets the date the food item was last modified.
     * 
     * @return the modification date
     */
    public LocalDate getModifiedDate() {
        String modifiedDate = getPropertyWithLoad(FoodItem::getModifiedDate);
        return parseApiDate(modifiedDate);
    }

    /**
     * Gets the publication date for the food item.
     * 
     * @return the publication date
     */
    public LocalDate getPublicationDate() {
        return parseApiDate(foodItem.getPublicationDate());
    }

    /**
     * Gets the serving size for the food item.
     * 
     * @return the serving size in the specified units
     */
    public double getServingSize() {
        return getPropertyWithLoad(FoodItem::getServingSize);
    }

    /**
     * Gets the units for the serving size (e.g., grams, ounces).
     * 
     * @return the units for serving size
     */
    public Units getServingSizeUnits() {
        String units = getPropertyWithLoad(FoodItem::getServingSizeUnit);
        if (units == "µg") {
            return Units.MICROGRAMS;
        } else if (units == "mg") {
            return Units.MILLIGRAMS;
        } else if (units == "g") {
            return Units.GRAMS;
        } else if (units == "kcal") {
            return Units.KILOCALORIES;
        } else if (units == "oz") {
            return Units.OUNCES;
        } else if (units == "tsp") {
            return Units.TEASPOONS;
        } else if (units == "tbsp") {
            return Units.TABLESPOONS;
        } else if (units == "cup") {
            return Units.CUPS;
        } else {
            return Units.UNKNOWN;
        }
    }

    /**
     * Gets the list of nutrients labeled for the food item.
     * 
     * @return a list of labeled nutrients
     */
    public List<Nutrient> getLabelNutrients() {
        return null;
    }
}
