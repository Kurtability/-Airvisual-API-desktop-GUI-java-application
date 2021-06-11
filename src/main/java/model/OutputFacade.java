package model;

import java.io.IOException;
/**
 * This class provides access to send the report generated from IQAIR API to the TWILLO API
 */
public interface OutputFacade{
    /**
     * posts request to the TWILLO api to send the report as SMS
     * <b>Preconditions:</b><br>
     * A ModelFacadeImpl has been instantiated.<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param reportToSend the report that the user intends to send as SMS.<br>
     * @return JSON format of the response of the post request from TWILLO
     * @throws IOException If the function fails to connect to the api
     * @throws InterruptedException If the function fails to connect to the api
     */
    String sendSMS(String reportToSend) throws IOException, InterruptedException;

}
