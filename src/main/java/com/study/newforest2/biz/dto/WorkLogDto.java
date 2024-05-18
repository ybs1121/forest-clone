package com.study.newforest2.biz.dto;

import com.study.newforest2.biz.entity.WorkLog;
import lombok.Data;

@Data
public class WorkLogDto {

    private long id;

    private String stdDt;

    private String workStTm;

    private String workEdTm;

    private String workTxt;

    private long memberId;

    private long projectId;

    private Long commentMemberId;

    private String comment;

    private String projectName;

    public static WorkLogDto toDto(WorkLog workLog) {
        WorkLogDto dto = new WorkLogDto();
        dto.setId(workLog.getId());
        dto.setStdDt(workLog.getStdDt());
        dto.setWorkStTm(workLog.getWorkStTm());
        dto.setWorkEdTm(workLog.getWorkEdTm());
        dto.setWorkTxt(workLog.getWorkTxt());
        dto.setProjectName(workLog.getProject().getName());

        return dto;
    }
}
