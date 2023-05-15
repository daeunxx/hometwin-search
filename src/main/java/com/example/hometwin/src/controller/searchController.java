package com.example.hometwin.src.controller;

import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JFileChooser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.hometwin.src.service.SearchService;

@Controller
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.hometwin"})
public class searchController {

    private final SearchService searchService;

    @Autowired
    public searchController(SearchService searchService){
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String apartmentSearch() {
        return "index";
    }

    @GetMapping("/search/all_data")
    public String apartmentList(String keyword, Model model) throws IOException {
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

        System.out.println(reader);
        System.out.println(builder);
        model.addAttribute("apartmentList", searchService.getSearchList(builder));

        return "apartmentList";
    }

    @GetMapping("/get/space_info")
    public String apartmentDetail(String aptCode, Model model, HttpServletRequest request) throws IOException {
        String masterURL = "https://flooropt.prod.genieverse.co.kr/get/space_info?apt_code=" + aptCode;

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

        System.out.println(builder);
        model.addAttribute("sizeList", searchService.getSizeList(builder, aptCode));

        return "apartmentDetail";
    }

    @RequestMapping("/get/image/thumbnail")
    public String apartmentThumbnail(String aptCode, String sizeType, String styleType, Model model, HttpServletRequest request) throws IOException {

        String url = "https://flooropt.prod.genieverse.co.kr/get/image/thumbnail?"
                + "apt_code=" + aptCode
                + "&size_type=" + sizeType
                + "&style_type=" + styleType
                + "&flip_code=0&img_num=0";

        model.addAttribute("data", searchService.getApartmentDetail(aptCode, sizeType, styleType));
        model.addAttribute("url", url);

        return "apartmentThumnail";
    }

    @GetMapping("/get/hometwin")
    public void getHomeTwinFile(String aptCode, String sizeType, String styleType, Model model, HttpServletResponse response) throws IOException, Exception {
        StringBuilder builder = searchService.getHomeTwinData(aptCode, sizeType, styleType);
        String gvvFilename = searchService.getFileName(aptCode, sizeType, styleType, "gvv",  builder);
        String gvfFilename = searchService.getFileName(aptCode, sizeType, styleType, "gvf",  builder);

        String rootPath = System.getProperty("user.dir");
        String gvvFilePath = rootPath +  "\\" + gvvFilename;
        String gvfFilePath = rootPath +  "\\" + gvfFilename;

        System.out.println(gvvFilePath);
        System.out.println(gvfFilePath);

        List<Path> files = Arrays.asList(Paths.get(gvvFilePath),
                Paths.get(gvfFilePath));

        response.setContentType("application/zip"); // zip archive format
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                .filename("download.zip", StandardCharsets.UTF_8)
                .build()
                .toString());

        JFileChooser jFileChooser = new JFileChooser();

        try(ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())){
            for (Path file : files) {
                try (InputStream inputStream = Files.newInputStream(file)) {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getFileName().toString()));
                    StreamUtils.copy(inputStream, zipOutputStream);
                    zipOutputStream.flush();
                }
            }
        }
    }

}
