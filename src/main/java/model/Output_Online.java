package model;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Output_Online implements OutputFacade{
    public static String SID = "AC7ebe48a1378366c19c7d1abe8630b48e";
    public static String authToken = "bcbbc5a582297fbbc6763387dd5c70ad";
    public static String fromTwilloPhoneNo = "+12675352180";
    public static String toMyPhoneNo = "+61405088266";

    public static String parseResponse(String body){
        return null;
    }

    private String postApiRequest(String uri,String sid, String authToken ,HttpRequest.BodyPublisher body) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        //encode sid and authtoken
        String login = String.format("%s:%s", sid, authToken);

        String encodedLogin = Base64.getEncoder().encodeToString(login.getBytes("utf-8"));


        //create request object
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).setHeader("Authorization", "Basic " + encodedLogin)
                .headers("CONTENT-TYPE", "application/x-www-form-urlencoded").POST(body).build();


        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(Output_Online::parseResponse)
                .join();


        //send request
        String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return response;
    }

    @Override
    public String Output_sendSMS(String report) throws IOException, InterruptedException {


        Map<String, String> parameters = new HashMap<>();
        parameters.put("To", toMyPhoneNo);
        parameters.put("From", fromTwilloPhoneNo);
        parameters.put("Body", report);

        String content = parameters.keySet().stream()
                .map(key -> key + "=" + URLEncoder.encode(parameters.get(key), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));


        String response = postApiRequest("https://api.twilio.com/2010-04-01/Accounts/" + SID + "/Messages.json",SID,authToken,HttpRequest.BodyPublishers.ofString(content));
        return response;
    }
}
