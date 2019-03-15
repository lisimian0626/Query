package com.beidousat.querydata.model;

public class SelectConfig {
    private String stationName;
    private String start_time;
    private String end_time;
    private int select_index;
    private String select_text;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getSelect_index() {
        return select_index;
    }

    public void setSelect_index(int select_index) {
        this.select_index = select_index;
    }

    public String getSelect_text() {
        return select_text;
    }

    public void setSelect_text(String select_text) {
        this.select_text = select_text;
    }
}
