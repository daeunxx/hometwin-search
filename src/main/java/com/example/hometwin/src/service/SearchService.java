package com.example.hometwin.src.service;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.example.hometwin.src.dto.Apartment;

@Service
public class SearchService {

    public List<Apartment> getSearchList(StringBuilder builder){

        String data = (builder.substring(10, builder.indexOf("]")+1));

        JSONArray jsonArray = new JSONArray(data);

        List<Apartment> list = new ArrayList<>();

        for(int i=0; i<jsonArray.length(); i++){
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            Apartment apartment = new Apartment();

            apartment.setAptCode(jsonObject.optString("apt_code"));
            apartment.setAptName(jsonObject.optString("apt_name"));
            apartment.setRoadAddr(jsonObject.optString("road_addr"));

            list.add(apartment);
        }

        return list;
    }

    public List<Apartment> getSizeList(StringBuilder builder, String aptCode){

        String data = (builder.substring(10, builder.indexOf("]")+1));

        JSONArray jsonArray = new JSONArray(data);

        List<Apartment> list = new ArrayList<>();

        for(int i=0; i<jsonArray.length(); i++){
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            Apartment apartment = new Apartment();

            apartment.setAptCode(aptCode);
            apartment.setSizeType(jsonObject.optString("size_type"));
            apartment.setStyleType(jsonObject.optString("style_type"));
            apartment.setSize(jsonObject.optString("size"));
            apartment.setFloor(jsonObject.optString("floor"));

            list.add(apartment);
        }

        return list;
    }
}
