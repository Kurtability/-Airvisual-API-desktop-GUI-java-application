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


    @Override
    public String Input_listSupportedCountries() throws IOException, InterruptedException {
        return null;
    }

    @Override
    public String Input_listSupportedStatesFromChosenCountry(String country) throws IOException, InterruptedException {
        return null;
    }

    @Override
    public String Input_listSupportedCitiesFromChosenState(String state, String country) throws IOException, InterruptedException {
        return null;
    }

    @Override
    public String Input_listSpecifiedCityDataFromChosenState(String city, String state, String country) throws IOException, InterruptedException {
        return null;
    }


}
