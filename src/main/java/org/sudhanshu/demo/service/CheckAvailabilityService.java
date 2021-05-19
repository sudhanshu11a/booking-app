/**
 * 
 */
package org.sudhanshu.demo.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.sudhanshu.demo.dto.Center;
import org.sudhanshu.demo.util.CenterConversion;
import org.sudhanshu.demo.dto.District;
import org.sudhanshu.demo.util.CenterFilterCretiria;

import javax.security.auth.message.AuthException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sudha
 *
 */
@Service
public class CheckAvailabilityService {

    CenterConversion centerConversion;

    VaccinationDataCenterService vaccinationDataCenterService;

    @Autowired
    private FakeResponseService fakeResponseService;

    private CenterFilterCretiria centerFilterCretiria;

    private boolean mockResponse;

    @Autowired
    public CheckAvailabilityService(VaccinationDataCenterService vaccinationDataCenterService,
                                    CenterConversion centerConversion,
                                    CenterFilterCretiria centerFilterCretiria,
                                    @Value("${covid.api.response.mock:false}")
                                    boolean mockResponse) {
        this.vaccinationDataCenterService = vaccinationDataCenterService;
        this.centerConversion = centerConversion;
        this.centerFilterCretiria = centerFilterCretiria;
        this.mockResponse = mockResponse;
        LOGGER.info("MockResponse : " + mockResponse);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckAvailabilityService.class);

    public List<Center> getAvaiableCentersForAllDistricts(String date, int minAgeLimit) throws AuthException{
        List<Center> centers = new ArrayList<>();
        centers.addAll(getAvailableCentersForDistrict(District.New_Delhi.getId(), date));
        centers.addAll(getAvailableCentersForDistrict(District.Central_Delhi.getId(), date));
        centers.addAll(getAvailableCentersForDistrict(District.West_Delhi.getId(), date));
        centers.addAll(getAvailableCentersForDistrict(District.North_West_Delhi.getId(), date));
        centers.addAll(getAvailableCentersForDistrict(District.South_East_Delhi.getId(), date));
        centers.addAll(getAvailableCentersForDistrict(District.East_Delhi.getId(), date));
        centers.addAll(getAvailableCentersForDistrict(District.North_Delhi.getId(), date));
        centers.addAll(getAvailableCentersForDistrict(District.North_East_Delhi.getId(), date));
        centers.addAll(getAvailableCentersForDistrict(District.Shahdara.getId(), date));
        centers.addAll(getAvailableCentersForDistrict(District.South_Delhi.getId(), date));
        centers.addAll(getAvailableCentersForDistrict(District.South_West_Delhi.getId(), date));
        centers = centerFilterCretiria.filterCenterByAgeAndAvailability(centers, minAgeLimit);
        return centers;
    }

    public List<Center> getAvaiableCentersForSingleDistricts(String district, String date, int minAgeLimit) throws AuthException{
        List<Center> centers = new ArrayList<>();
        centers.addAll(getAvailableCentersForDistrict(district, date));
        centers = centerFilterCretiria.filterCenterByAgeAndAvailability(centers, minAgeLimit);
        return centers;
    }

    private List<Center> getAvailableCentersForDistrict(String district, String date) throws AuthException{
        JsonNode rootNode = null;
        List<Center> centerList = null;
        String response = null;
        try{
            ObjectMapper mapper = new ObjectMapper();
            response = getResponseFromAPI(district, date);
            rootNode = mapper.readTree(response);
            centerList = centerConversion.getObjectByJson(rootNode);
        }catch(IOException e) {
            LOGGER.error(e.getMessage());
            centerList = new ArrayList<>();
        }
        return centerList;
    }

    private String getResponseFromAPI(String district, String date) throws IOException, AuthException {
        if(mockResponse){
            return fakeResponseService.getFakeResponse();
        }
        return vaccinationDataCenterService.getRealCentersDataForDistrictAndDate(district, date);
    }

}
