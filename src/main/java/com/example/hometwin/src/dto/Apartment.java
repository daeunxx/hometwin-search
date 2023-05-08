package com.example.hometwin.src.dto;

public class Apartment {

    private String aptCode;     /*아파트 코드*/
    private String roadAddr;    /*도로명 주소*/
    private String aptName;     /*아파트명*/

    public String getAptCode() {
        return aptCode;
    }

    public void setAptCode(String aptCode) {
        this.aptCode = aptCode;
    }

    public String getRoadAddr() {
        return roadAddr;
    }

    public void setRoadAddr(String roadAddr) {
        this.roadAddr = roadAddr;
    }

    public String getAptName() {
        return aptName;
    }

    public void setAptName(String aptName) {
        this.aptName = aptName;
    }
}
