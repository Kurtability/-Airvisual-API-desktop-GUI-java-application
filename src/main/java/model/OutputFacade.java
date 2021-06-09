package model;

import java.io.IOException;

public interface OutputFacade{

    String Output_sendSMS(String reportToSend) throws IOException, InterruptedException;

}
