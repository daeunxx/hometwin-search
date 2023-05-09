package com.example.hometwin.src.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apartment {

    private String aptCode;     /*아파트 코드*/
    private String roadAddr;    /*도로명 주소*/
    private String aptName;     /*아파트명*/
}
