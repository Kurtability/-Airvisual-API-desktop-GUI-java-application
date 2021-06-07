package model;

import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// first three and last one in the API
public class Input_Online implements InputFacade{

    static String API_key = "f942928c-b2ec-4ab7-a7d9-009d88f63d36";
    //private String API_key;

    public Input_Online(){
        //this.API_key= API_key;


    }

    private String getApiRequest(String uri) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        //HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).setHeader("Authorization", "Bearer " + API_key).build();

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
