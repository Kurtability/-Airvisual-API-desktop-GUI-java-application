package model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Output_Online implements OutputFacade{

    private String postApiRequest(String uri, String token , HttpRequest.BodyPublisher body) throws IOException, InterruptedException {
        return null;
    }

    @Override
    public String Output_sendSMS(String report) {
        return null;
    }
}
