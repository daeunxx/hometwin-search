package com.example.hometwin.src.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "홈트윈 파일")
public class HomeTwinFile {

    @Schema(description = "아파트 코드")
    private String aptCode;

    @Schema(description = "평형")
    private String sizeType;

    @Schema(description = "일반/확장")
    private String styleType;

    @Schema(description = "상하좌우 반전")
    private String flieCode;

    @Schema(description = "결과")
    private String result;

    @Schema(description = "지역 코드")
    private String regionCode;

    @Schema(description = "gvv 파일명")
    private String gvvFilename;

    @Schema(description = "gvv 파일 내용")
    private String gvvContent;

    @Schema(description = "gvf 파일명")
    private String gvfFilename;

    @Schema(description = "gvf 파일 내용")
    private String gvfContent;
}
