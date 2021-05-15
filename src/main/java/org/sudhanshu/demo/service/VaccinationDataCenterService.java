package org.sudhanshu.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.message.AuthException;

@Service
public class VaccinationDataCenterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VaccinationDataCenterService.class);

    private String coVinApi;

    @Autowired
    public VaccinationDataCenterService(@Value("${booking.application.co-vin.api}") String coVinApi) {

        this.coVinApi = coVinApi;
    }

    public String getRealCentersDataForDistrictAndDate(String district, String date) throws AuthException {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = coVinApi+ "?district_id="+district+"&date="+date;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Connection", "keep-alive");
        headers.set("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36");
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(fooResourceUrl, HttpMethod.GET, request, String.class);
            LOGGER.info("Response: " + response.getBody());
        }catch(Exception e) {
            LOGGER.error(e.getMessage());
            throw new AuthException(e.getMessage());
        }
        return response.getBody();
    }
}
