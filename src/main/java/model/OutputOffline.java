package model;

import java.io.IOException;

public class OutputOffline implements OutputFacade{
    @Override
    public String sendSMS(String reportToSend) throws IOException, InterruptedException {
        String dummyResponse = "{\"sid\": \"SMfc9d8bb6898044398c9b6fa3abf0ad6e\", \"date_created\": \"Wed, 09 Jun 2021 07:45:01 +0000\", " +
                "\"date_updated\": \"Wed, 09 Jun 2021 07:45:01 +0000\", \"date_sent\": null, \"account_sid\": " +
                "\"AC7ebe48a1378366c19c7d1abe8630b48e\", \"to\": \"+61405088266\", \"from\": \"+12675352180\", " +
                "\"messaging_service_sid\": null, \"body\": \"Sent from your Twilio trial account - " +
                "{city=Beijing, state=Beijing, country=China, location type=Point, location coordinate 0=116.462153, " +
                "location coordinate 1=39.941674, current weather ts=2021-06-09T07:00:00.000Z, current weather tp=25.0, " +
                "current weather pr=1011.0, current weather hu=88.0, current weather ws=2.0, current weather wd=350.0, " +
                "current weather ic=10d, current pollution ts=2021-06-09T07:00:00.000Z, current pollution aqius=78.0, " +
                "current pollution mainus=p2, current pollution aqicn=52.0, current pollution maincn=p1}\", " +
                "\"status\": \"queued\", \"num_segments\": \"4\", \"num_media\": \"0\", \"direction\": \"outbound-api\", " +
                "\"api_version\": \"2010-04-01\", \"price\": null, \"price_unit\": \"USD\", \"error_code\": null, " +
                "\"error_message\": null, \"uri\": \"" +
                "/2010-04-01/Accounts/AC7ebe48a1378366c19c7d1abe8630b48e/Messages/SMfc9d8bb6898044398c9b6fa3abf0ad6e.json\", " +
                "\"subresource_uris\": " +
                "{\"media\": \"/2010-04-01/Accounts/AC7ebe48a1378366c19c7d1abe8630b48e/Messages/SMfc9d8bb6898044398c9b6fa3abf0ad6e/Media.json\"}}";

        return dummyResponse;
    }
}
