package org.sudhanshu.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sudhanshu.demo.dto.Center;
import org.sudhanshu.demo.service.CheckAvailabilityService;
import org.sudhanshu.demo.util.ApplicationConstants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private CheckAvailabilityService checkAvailabilityService;

    @GetMapping("/centers/delhi/{district}/{minAge}")
    public ResponseEntity<?> getAvailableCenters(@PathVariable String district, @PathVariable int minAge){
        List<Center> filteredCenters = null;
        try {
            if("all".equals(district)){
                return getAvailableCentersAllDistrictsOneWeek(minAge);
            }
            filteredCenters =  checkAvailabilityService.getAvaiableCentersForSingleDistricts(district, getTodaysDate(), minAge);
        }catch(Exception e){
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok(filteredCenters);

    }

    @GetMapping("/check/age/{minAge}/all/{date}")
    public ResponseEntity<?> getAvailableCentersAllDistricts(@PathVariable int minAge, @PathVariable String date){
        try {
            return ResponseEntity.ok(checkAvailabilityService.getAvaiableCentersForAllDistricts(date, minAge));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping(value = "/check/delhi/all/{minAge}" , produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> getAvailableCentersAllDistrictsOneWeek(@PathVariable int minAge){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApplicationConstants.DATE_FORMATER);
        String date = localDate.format(formatter);
        List<Center>  filteredCenters = null;
        try{
            filteredCenters =  checkAvailabilityService.getAvaiableCentersForAllDistricts(date, minAge);
        }catch(Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return ResponseEntity.ok(filteredCenters);
    }

    private String getTodaysDate(){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApplicationConstants.DATE_FORMATER);
        return localDate.format(formatter);

    }
}
