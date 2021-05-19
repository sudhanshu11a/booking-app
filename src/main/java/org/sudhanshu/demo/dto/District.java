package org.sudhanshu.demo.dto;

public enum District {
    New_Delhi("140", "New Delhi"),
    Central_Delhi("141", "Central Delhi"),
    West_Delhi("142", "West Delhi"),
    North_West_Delhi("143", "North West Delhi"),
    South_East_Delhi("144", "South East Delhi"),
    East_Delhi("145", "East Delhi"),
    North_Delhi("146", "North Delhi"),
    North_East_Delhi("147", "North East Delhi"),
    Shahdara("148", "Shahdara"),
    South_Delhi("149", "South Delhi"),
    South_West_Delhi("150", "South West Delhi");

    private String id;
    private String name;

    District(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }

    public String getName(){
        return name;
    }
}
