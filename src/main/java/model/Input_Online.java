package model;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class Input_Online implements InputFacade{

    static String API_key;

    public Input_Online() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);

        API_key = properties.getProperty("IQAIR_API_Key");

    }

    private String getApiRequest(String uri) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        return response;
    }



    @Override
    public String Input_listSupportedCountries() throws IOException, InterruptedException {
        String response = getApiRequest("https://api.airvisual.com/v2/countries?key="+API_key);
        return response;
    }

    @Override
    public String Input_listSupportedStatesFromChosenCountry(String country) throws IOException, InterruptedException {
        String response = getApiRequest("https://api.airvisual.com/v2/states?country="+country+"&key=" + API_key);

        return response;
    }

    @Override
    public String Input_listSupportedCitiesFromChosenState(String state, String country) throws IOException, InterruptedException {
        String response = getApiRequest("https://api.airvisual.com/v2/cities?state="+state + "&country=" +country + "&key=" +API_key);

        return response;
    }

    @Override
    public String Input_listSpecifiedCityDataFromChosenState(String city, String state, String country) throws IOException, InterruptedException {
        String response = getApiRequest("https://api.airvisual.com/v2/city?city="+city+"&state="+state+"&country="+country+"&key=" +API_key);
        return response;
    }


}
