package com.study.newforest2.biz.dto;

import com.study.newforest2.biz.entity.Scrum;
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


    public static ScrumDto toDto(Scrum scrum) {
        ScrumDto dto = new ScrumDto();
        dto.setId(scrum.getId());
        dto.setScrumTitle(scrum.getScrumTitle());
        dto.setScrumContext(scrum.getScrumContext());
        dto.setStDt(scrum.getStDt());
        dto.setEndDt(scrum.getEndDt());
        dto.setProjectId(scrum.getProject().getId());
        dto.setMemberId(scrum.getMember().getId());
        return dto;
    }

}
