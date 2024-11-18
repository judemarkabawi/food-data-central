package fooddatacentral;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class Utility {
    static final String API_URL = "https://api.nal.usda.gov/fdc/v1/";
    static final String API_FOOD_URL = API_URL + "food/";
    static final String API_FOODS_URL = API_URL + "foods";
    static final String API_FOOD_LIST_URL = API_URL + "foods/list";
    static final String API_FOODS_SEARCH_URL = API_URL + "foods/search";

    static final HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).build();

    static HttpResponse<String> sendApiRequest(String url) throws IOException, InterruptedException {
        URI uri = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}
