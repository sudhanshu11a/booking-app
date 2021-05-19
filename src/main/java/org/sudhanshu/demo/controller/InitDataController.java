package org.sudhanshu.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sudhanshu.demo.dto.District;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin
public class InitDataController {

    @GetMapping("/district/delhi/all")
    public ResponseEntity<?> getAllDistrictData(){

        List<Map<String, String>> list = Stream.of(District.values()).parallel().map(temp -> {
            Map<String, String> obj = new HashMap<String, String>();
            obj.put("id", temp.getId());
            obj.put("name", temp.getName());
            return obj;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }
}
