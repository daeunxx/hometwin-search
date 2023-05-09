package com.example.hometwin.src.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.example.hometwin.src.dto.Apartment;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    public List<Apartment> jsonToObject(StringBuilder builder) throws JsonMappingException, JsonProcessingException {

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
}
