package com.study.newforest2.biz.dto;

import lombok.Data;

@Data
public class ScrumDto {

    private Long id;

    private String scrumTitle;

    private String scrumContext;

    private String stDt;

    private String endDt;

    private Long projectId;

    private Long memberId;

}
