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

        String status = obj.getString("status");
        System.out.println("status: "+ status);
        if(status.equals("fail")){
            JSONObject dataObj = obj.getJSONObject("data");
            String errorMessage = dataObj.getString("message");
            System.out.println("error message: " + errorMessage + ", please make sure the entered api key is valid");
            System.exit(0);
        }

        JSONArray arr = obj.getJSONArray("data");
        for (int i = 0; i < arr.length(); i++) {
            countries.add(arr.getJSONObject(i).getString("country"));
        }
        return countries;
    }


    //https://api.airvisual.com/v2/states?country=China&key=f942928c-b2ec-4ab7-a7d9-009d88f63d36
    public static ArrayList<String> parseSupportedStates(String toBeParsed){

        ArrayList<String> states = new ArrayList<>();

        JSONObject obj = new JSONObject(toBeParsed);

        String status = obj.getString("status");
        System.out.println("status: "+ status);
        if(status.equals("fail")){
            JSONObject dataObj = obj.getJSONObject("data");
            String errorMessage = dataObj.getString("message");
            System.out.println("error message: " + errorMessage +" ,its potentially caused by the change url formatting from API, please restart the program and select another country");
            System.exit(0);
        }

        JSONArray arr = obj.getJSONArray("data");
        for (int i = 0; i < arr.length(); i++) {
            states.add(arr.getJSONObject(i).getString("state"));
        }
        return states;
    }


    //https://api.airvisual.com/v2/cities?state=Beijing&country=China&key=f942928c-b2ec-4ab7-a7d9-009d88f63d36

    public static ArrayList<String> parseSupportedCities(String toBeParsed){
        ArrayList<String> cities = new ArrayList<>();

        JSONObject obj = new JSONObject(toBeParsed);

        String status = obj.getString("status");

        System.out.println("status: "+ status);
        if(status.equals("fail")){
            JSONObject dataObj = obj.getJSONObject("data");
            String errorMessage = dataObj.getString("message");
            System.out.println("error message: " + errorMessage + " ,its potentially caused by the change url formatting from API, please restart the program and select another state from this country");
            System.exit(0);
        }

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
        LinkedHashMap<String,String> result = new LinkedHashMap<>();

        JSONObject obj = new JSONObject(toBeParsed);

        String status = obj.getString("status");
        System.out.println("status: " + status);
        if(status.equals("fail")){
            JSONObject dataObj = obj.getJSONObject("data");
            String errorMessage = dataObj.getString("message");
            System.out.println("error message: " + errorMessage + " ,its potentially caused by the change url formatting from API, please restart the program and select another city from this state");
            System.exit(0);
        }

        JSONObject data_obj = obj.getJSONObject("data");

        String city = data_obj.getString("city");
        result.put("city", city);

        String state = data_obj.getString("state");
        result.put("state", state);

        String country = data_obj.getString("country");
        result.put("country", country);


        JSONObject location_obj = data_obj.getJSONObject("location");
        String location_type = location_obj.getString("type");
        result.put("location type", location_type);

        JSONArray coordinates_arr = location_obj.getJSONArray("coordinates");
        for (int i = 0; i < coordinates_arr.length(); i++){

            double coordinates = coordinates_arr.getDouble((i));
            result.put("location coordinate " + i, String.valueOf(coordinates));
        }

        JSONObject current_obj = data_obj.getJSONObject("current");
        JSONObject currentWeather_obj = current_obj.getJSONObject("weather");
        String currentWeather_ts = currentWeather_obj.getString("ts");
        result.put("current weather ts", currentWeather_ts);

        double currentWeather_tp = currentWeather_obj.getDouble("tp");
        result.put("current weather tp" , String.valueOf(currentWeather_tp));

        double currentWeather_pr = currentWeather_obj.getDouble("pr");
        result.put("current weather pr", String.valueOf(currentWeather_pr));

        double currentWeather_hu = currentWeather_obj.getDouble("hu");
        result.put("current weather hu", String.valueOf(currentWeather_hu));

        double currentWeather_ws = currentWeather_obj.getDouble("ws");
        result.put("current weather ws", String.valueOf(currentWeather_ws));

        double currentWeather_wd = currentWeather_obj.getDouble("wd");
        result.put("current weather wd", String.valueOf(currentWeather_wd));

        String currentWeather_ic = currentWeather_obj.getString("ic");
        result.put("current weather ic", currentWeather_ic);



        JSONObject currentPollution_obj = current_obj.getJSONObject("pollution");
        String currentPollution_ts = currentPollution_obj.getString("ts");
        result.put("current pollution ts", currentPollution_ts);

        double currentPollution_aqius = currentPollution_obj.getDouble("aqius");
        result.put("current pollution aqius", String.valueOf(currentPollution_aqius));

        String currentPollution_mainus = currentPollution_obj.getString("mainus");
        result.put("current pollution mainus", currentPollution_mainus);

        double currentPollution_aqicn = currentPollution_obj.getDouble("aqicn");
        result.put("current pollution aqicn", String.valueOf(currentPollution_aqicn));

        String currentPollution_maincn = currentPollution_obj.getString("maincn");
        result.put("current pollution maincn", currentPollution_maincn);

        return result;
    }
}
