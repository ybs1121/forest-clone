package com.study.newforest2.biz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MemberFind {

    @Schema(description = "1 : memberName 2: projectName")
    private String findOption;

    private String findText;
}
