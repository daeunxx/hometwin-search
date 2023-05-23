package com.example.hometwin.src.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.example.hometwin.src.dto.Apartment;
import com.example.hometwin.src.dto.HomeTwinFile;


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

    public Apartment getApartmentDetail(String aptCode, String sizeType, String styleType){

        Apartment apartment = new Apartment();

        apartment.setAptCode(aptCode);
        apartment.setSizeType(sizeType);
        apartment.setStyleType(styleType);

        return apartment;
    }

    public HomeTwinFile getHomeTwinFile(String data, String aptCode, String sizeType, String styleType){

        JSONObject jsonObject = new JSONObject(data);
        HomeTwinFile homeTwinFile = new HomeTwinFile();

        homeTwinFile.setAptCode(aptCode);
        homeTwinFile.setSizeType(sizeType);
        homeTwinFile.setStyleType(styleType);

        homeTwinFile.setResult(jsonObject.optString("result"));
        homeTwinFile.setRegionCode(jsonObject.optString("region_code"));
        homeTwinFile.setGvvFilename(jsonObject.optString("gvv_filename"));
        homeTwinFile.setGvvContent(jsonObject.optString("gvv_content"));
        homeTwinFile.setGvfFilename(jsonObject.optString("gvf_filename"));
        homeTwinFile.setGvfContent(jsonObject.optString("gvf_content"));

        return homeTwinFile;
    }

    public File createFile(String fileName, String fileContent) throws IOException {

        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file, true);

        fileWriter.write(fileContent);
        fileWriter.flush();
        fileWriter.close();

        return file;
    }

    public StringBuilder getHomeTwinData(String aptCode, String sizeType, String styleType) throws Exception {
        String masterURL = "https://flooropt.prod.genieverse.co.kr/get/hometwin?"
                + "apt_code=" + aptCode
                + "&size_type=" + sizeType
                + "&style_type=" + styleType
                + "&flip_code=0";

        URL url = new URL(masterURL);

        HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        StringBuilder builder = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        return builder;
    }

    public String getFileName(String aptCode, String sizeType, String styleType, String fileType, StringBuilder builder) throws IOException {

        String filename = new String();

        HomeTwinFile homeTwinFile = new HomeTwinFile();
        homeTwinFile = this.getHomeTwinFile(builder.toString(), aptCode, sizeType, styleType);

        File file = this.createFile(homeTwinFile.getGvvFilename(), homeTwinFile.getGvvContent());

        if(fileType.equals("gvf"))
            file = this.createFile(homeTwinFile.getGvfFilename(), homeTwinFile.getGvvContent());

        filename = file.getName();

        return filename;
    }
}
