package com.study.newforest2.biz.dto;

import com.study.newforest2.biz.entity.DailyLog;
import lombok.Data;

@Data
public class DailyLogDto {

    private long id;

    private String content;

    private String stdDt;

    private String stTm;

    private String endTm;

    private long memberId;

    private long projectId;


    public static DailyLogDto toDto(DailyLog dailyLog) {
        DailyLogDto dailyLogDto = new DailyLogDto();
        dailyLogDto.setId(dailyLog.getId());
        dailyLogDto.setContent(dailyLog.getContent());
        dailyLogDto.setStdDt(dailyLog.getStdDt());
        dailyLogDto.setStTm(dailyLog.getStTm());
        dailyLogDto.setEndTm(dailyLog.getEndTm());
        return dailyLogDto;
    }
}
