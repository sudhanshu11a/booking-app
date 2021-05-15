package org.sudhanshu.demo.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.sudhanshu.demo.dto.Center;
import org.sudhanshu.demo.dto.Session;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CenterConversion {

    private static final Logger LOGGER = LoggerFactory.getLogger(CenterConversion.class);

    public List<Center> getObjectByJson(JsonNode rootNode){
        try{
             return getCentersByJson(rootNode);
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            throw ex;
        }
    }

    private List<Center> getCentersByJson(JsonNode rootNode){
        DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern(ApplicationConstants.DATE_FORMATER);
        DateTimeFormatter timeFormater = DateTimeFormatter.ofPattern("HH:mm:ss");
        List<Center> centerList = new ArrayList<>();
        if(rootNode!=null && rootNode.get("centers").isArray()){
            for (final JsonNode objNode : rootNode) {
                for(final JsonNode centerNode: objNode){
                    Center center = new Center();
                    center.setCenterId((centerNode.get("center_id").asLong()));
                    center.setName(centerNode.get("name").asText());
                    center.setAddress(centerNode.get("address").asText());
                    center.setState_name(centerNode.get("state_name").asText());
                    center.setDistrictName(centerNode.get("district_name").asText());
                    center.setBlockName(centerNode.get("block_name").asText());
                    center.setPincode(centerNode.get("pincode").asLong());
                    center.setLat(centerNode.get("lat").asInt());
                    center.setLoog(centerNode.get("long").asInt());
                    center.setFrom(LocalTime.parse(centerNode.get("from").asText(), timeFormater));
                    center.setTo(LocalTime.parse(centerNode.get("to").asText(), timeFormater));
                    center.setFeeType(centerNode.get("fee_type").asText());
                    center.setSessions(getSessionByJson(centerNode.get("sessions"), dateFormater));
                    centerList.add(center);
                }
            }
        }
        return centerList;
    }


    private List<Session> getSessionByJson(JsonNode sessionsNode, DateTimeFormatter dateFormater){
        List<Session> sessions = new ArrayList<>();
        for(JsonNode sessionNode: sessionsNode){
            Session session = new Session();
            session.setSession_id(sessionNode.get("session_id").asText());
            session.setDate(LocalDate.parse(sessionNode.get("date").asText(), dateFormater));
            session.setAvailableCapacity(sessionNode.get("available_capacity").asInt());
            session.setMinAgeLimit(sessionNode.get("min_age_limit").asInt());
            session.setVaccine(sessionNode.get("vaccine").asText());
            session.setSlots(getSlotsByJson(sessionNode.get("slots")));
            sessions.add(session);
        }
        return sessions;
    }

    private List<String> getSlotsByJson(JsonNode slotsNode){
        List<String> slots = new ArrayList<>();
        slotsNode.forEach(slot -> slots.add(slot.asText()));
        return slots;
    }
}
