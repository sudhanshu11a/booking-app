package org.sudhanshu.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.sudhanshu.demo.dto.Center;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CenterFilterCretiria {

    private static final Logger LOGGER = LoggerFactory.getLogger(CenterFilterCretiria.class);

    private int minAvailableCapacity;
    @Autowired
    public CenterFilterCretiria(@Value("${booking.application.co-vin.min-available-capacity}") Integer minAvailableCapacity) {
     this.minAvailableCapacity = minAvailableCapacity;
    }

    public List<Center> filterCenterByAgeAndAvailability(List<Center> centers, int minAgeLimit){
        List<Center> centers1 = centers.stream()
                .filter(center -> {
                    return !(center.getSessions().stream()
                            .filter(session -> session.getMinAgeLimit()==minAgeLimit)
                            .collect(Collectors.toList()))
                            .isEmpty();
                })
                .collect(Collectors.toList());
        LOGGER.info(String.valueOf(centers1.size()));
        List<Center> centers2 = centers1.stream()
                .filter(center -> {
                    return !(center.getSessions().stream()
                            .filter(session -> session.getAvailableCapacity()>minAvailableCapacity)
                            .collect(Collectors.toList()))
                            .isEmpty();
                }).collect(Collectors.toList());
        LOGGER.info(String.valueOf(centers2.size()));
        return centers2;
    }

}
