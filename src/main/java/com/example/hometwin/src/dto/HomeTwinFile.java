package com.example.hometwin.src.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeTwinFile {

    private String aptCode;             /*아파트 코드*/
    private String sizeType;            /*평형*/
    private String styleType;           /*일반/확장*/
    private String flieCode;            /*상하좌우 반전*/
    private String result;              /*결과*/
    private String regionCode;          /*지역 코드*/
    private String gvvFilename;         /*gvv 파일명*/
    private String gvvContent;          /*gvv 파일 내용*/
    private String gvfFilename;         /*gvf 파일명*/
    private String gvfContent;          /*gvf 파일 내용*/
}
