package model;

import java.io.IOException;

public interface OutputFacade{

    String sendSMS(String reportToSend) throws IOException, InterruptedException;

}
