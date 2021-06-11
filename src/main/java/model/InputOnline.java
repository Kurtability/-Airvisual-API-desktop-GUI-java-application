package model;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class InputOnline implements InputFacade{

    static String apiKey;

    public InputOnline() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);

        apiKey = properties.getProperty("IQAIR_API_Key");

    }

    private String getApiRequest(String uri) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        return response;
    }



    @Override
    public String listSupportedCountries() throws InterruptedException, IOException {
        String response = getApiRequest("https://api.airvisual.com/v2/countries?key="+ apiKey);
        return response;
    }

    @Override
    public String listSupportedStatesFromChosenCountry(String country) throws IOException, InterruptedException {
        String response = getApiRequest("https://api.airvisual.com/v2/states?country="+country+"&key=" + apiKey);

        return response;
    }

    @Override
    public String listSupportedCitiesFromChosenState(String state, String country) throws IOException, InterruptedException {
        String response = getApiRequest("https://api.airvisual.com/v2/cities?state="+state + "&country=" +country + "&key=" + apiKey);

        return response;
    }

    @Override
    public String listSpecifiedCityDataFromChosenState(String city, String state, String country) throws IOException, InterruptedException {
        String response = getApiRequest("https://api.airvisual.com/v2/city?city="+city+"&state="+state+"&country="+country+"&key=" + apiKey);
        return response;
    }


}
