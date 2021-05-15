package org.sudhanshu.demo.dto;

public enum District {
    New_Delhi("140"),
    Central_Delhi("141"),
    West_Delhi("142"),
    North_West_Delhi("143"),
    South_East_Delhi("144"),
    East_Delhi("145"),
    North_Delhi("146"),
    North_East_Delhi("147"),
    Shahdara("148"),
    South_Delhi("149"),
    South_West_Delhi("150");

    private String id;

    District(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
}
