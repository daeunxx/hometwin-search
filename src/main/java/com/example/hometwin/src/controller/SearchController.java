package com.example.hometwin.src.controller;

import org.json.JSONObject;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SearchController {

    @GetMapping("/search/all_data")
    public String apacheHttpClient(String keyword) throws IOException {
        System.out.println(keyword);
        String query = URLEncoder.encode(keyword, "UTF-8");
        String masterURL = "https://flooropt.prod.genieverse.co.kr/search/all_data?keyword=" + query;

        URL url = new URL(masterURL);

        HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        int responseCode = connection.getResponseCode();

        System.out.println(responseCode);
        System.out.println(connection.getResponseMessage());
        System.out.println(connection.getContent().toString());


        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        StringBuilder builder = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        JSONObject responseJson = new JSONObject(builder.toString());
        System.out.println(responseJson);

        return "apartList";
    }

    @GetMapping("/search")
    public String hello() {
        return "index";
    }
}
