package presenter;

import org.checkerframework.checker.units.qual.A;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class JsonParser {
    //https://api.airvisual.com/v2/countries?key=f942928c-b2ec-4ab7-a7d9-009d88f63d36
    public static ArrayList<String> parseSupportedCountries(String toBeParsed){
        ArrayList<String> countries = new ArrayList<>();


        JSONObject obj = new JSONObject(toBeParsed);

        if(obj.has("error")){
            JSONObject error = obj.getJSONObject("error");
            String error_message = error.getString("message");
            System.out.println(error_message);
        }

        String status = obj.getString("status");
        System.out.println("status: "+ status);

        JSONArray arr = obj.getJSONArray("data");
        for (int i = 0; i < arr.length(); i++) {
            countries.add(arr.getJSONObject(i).getString("country"));
            //String country = arr.getJSONObject(i).getString("country");
            //System.out.println("country: " + country);
        }
        return countries;
    }


    //https://api.airvisual.com/v2/states?country=China&key=f942928c-b2ec-4ab7-a7d9-009d88f63d36
    public static ArrayList<String> parseSupportedStates(String toBeParsed){

        ArrayList<String> states = new ArrayList<>();

        JSONObject obj = new JSONObject(toBeParsed);

        if(obj.has("error")){
            JSONObject error = obj.getJSONObject("error");
            String error_message = error.getString("message");
            System.out.println(error_message);
        }

        String status = obj.getString("status");
        System.out.println("status: "+ status);

        JSONArray arr = obj.getJSONArray("data");
        for (int i = 0; i < arr.length(); i++) {
            states.add(arr.getJSONObject(i).getString("state"));
//            String state = arr.getJSONObject(i).getString("state");
//            System.out.println("state: " + state);
        }
        return states;
    }


    //https://api.airvisual.com/v2/cities?state=Beijing&country=China&key=f942928c-b2ec-4ab7-a7d9-009d88f63d36

    public static ArrayList<String> parseSupportedCities(String toBeParsed){
        ArrayList<String> cities = new ArrayList<>();

        JSONObject obj = new JSONObject(toBeParsed);

        if(obj.has("error")){
            JSONObject error = obj.getJSONObject("error");
            String error_message = error.getString("message");
            System.out.println(error_message);
        }

        String status = obj.getString("status");
        System.out.println("status: "+ status);

        JSONArray arr = obj.getJSONArray("data");
        for (int i = 0; i < arr.length(); i++) {
            cities.add(arr.getJSONObject(i).getString("city"));
//            String city = arr.getJSONObject(i).getString("city");
//            System.out.println("city: "+ city);
        }
        return cities;
    }


    //https://api.airvisual.com/v2/city?city=Beijing&state=Beijing&country=China&key=f942928c-b2ec-4ab7-a7d9-009d88f63d36
    public static LinkedHashMap<String,String>  parseSpecifiedCityData(String toBeParsed){

        return null;
    }
}
