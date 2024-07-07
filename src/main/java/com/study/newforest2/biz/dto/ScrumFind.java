package com.study.newforest2.biz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ScrumFind {

    @Schema(description = "1 : context 2: title")
    private String findOption;

    private String text;
}
