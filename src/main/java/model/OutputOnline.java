package model;

import java.io.FileInputStream;
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
import java.util.Properties;
import java.util.stream.Collectors;

public class OutputOnline implements OutputFacade{
    public static String SID;
    public static String authToken;
    public static String fromTwilloPhoneNo;
    public static String toMyPhoneNo;

    public OutputOnline() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);

        SID = properties.getProperty("TWILLO_API_SID");
        authToken = properties.getProperty("TWILLO_API_AuthToken");
        fromTwilloPhoneNo = properties.getProperty("TWILLO_API_FromTwilloPhoneNumber");
        toMyPhoneNo = properties.getProperty("TWILLO_API_ToMyPhoneNUmber");
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
                .join();


        //send request
        String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return response;
    }

    @Override
    public String sendSMS(String report) throws IOException, InterruptedException {


        Map<String, String> parameters = new HashMap<>();
        parameters.put("To", toMyPhoneNo);
        parameters.put("From", fromTwilloPhoneNo);
        parameters.put("Body", report);

        String content = parameters.keySet().stream()
                .map(key -> key + "=" + URLEncoder.encode(parameters.get(key), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));


        String response = postApiRequest("https://api.twilio.com/2010-04-01/Accounts/" + SID + "/Messages.json",SID,authToken,HttpRequest.BodyPublishers.ofString(content));
        System.out.println("posted once!!! if you are getting double messages, then its twilio being funky with ya");
        return response;
    }
}
