package org.sudhanshu.demo.dto;

import java.time.LocalDate;
import java.util.List;

public class Session {
    private String session_id;
    private LocalDate date;
    private int availableCapacity;
    private int minAgeLimit;
    private String vaccine;
    private List<String> slots;

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public int getMinAgeLimit() {
        return minAgeLimit;
    }

    public void setMinAgeLimit(int minAgeLimit) {
        this.minAgeLimit = minAgeLimit;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public List<String> getSlots() {
        return slots;
    }

    public void setSlots(List<String> slots) {
        this.slots = slots;
    }

    @Override
    public String toString() {
        return "Session{" +
                ", date=" + date +
                ", availableCapacity=" + availableCapacity +
                ", minAgeLimit=" + minAgeLimit +
                ", vaccine='" + vaccine + '\'' +
                '}';
    }
}
