package com.example.hometwin.src.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentSize {

    private String aptCode;      /*아파트 코드*/
    private String sizeType;     /*평형*/
    private String styleType;    /*일반/확장*/
    private String size;         /*공급 면적*/
    private String floor;        /*층수*/
}
