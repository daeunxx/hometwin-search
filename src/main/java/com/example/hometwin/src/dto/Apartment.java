package com.example.hometwin.src.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "아파트")
public class Apartment {

    @Schema(description = "아파트 코드")
    private String aptCode;

    @Schema(description = "도로명 주소")
    private String roadAddr;

    @Schema(description = "아파트명")
    private String aptName;

    @Schema(description = "평형")
    private String sizeType;

    @Schema(description = "일반/확장")
    private String styleType;

    @Schema(description = "공급 면적")
    private String size;

    @Schema(description = "층수")
    private String floor;
}
