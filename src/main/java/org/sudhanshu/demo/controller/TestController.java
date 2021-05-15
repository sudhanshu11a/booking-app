package org.sudhanshu.demo.controller;


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

    @Autowired
    private CheckAvailabilityService checkAvailabilityService;

    @GetMapping("/check/{district}/{date}/{minAge}")
    public ResponseEntity<?> getAvailableCenters(@PathVariable String district, @PathVariable String date, @PathVariable int minAge){
        try {
            return ResponseEntity.ok(checkAvailabilityService.getAvaiableCentersForSingleDistricts(district, date, minAge));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
        return ResponseEntity.ok(filteredCenters);
    }

    private String getTodaysDate(){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApplicationConstants.DATE_FORMATER);
        return localDate.format(formatter);

    }
}
