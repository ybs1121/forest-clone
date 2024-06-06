package com.study.newforest2.biz.entity;

import com.study.newforest2.biz.dto.DailyLogDto;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * 업무일지
 */
@Entity
@Getter
public class DailyLog {

    @Id
    @GeneratedValue
    private long id;

    private String content;

    @Column(length = 8)
    private String stdDt;

    private String stTm;

    private String endTm;

    @ManyToOne(fetch = FetchType.LAZY) // 단방향
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY) // 단방향
    private Project project;


    public static DailyLog toDao(String content,String stdDt, String stTm, String endTm, Member member, Project project) {
        DailyLog dailyLog = new DailyLog();
        dailyLog.stdDt = stdDt;
        dailyLog.stTm = stTm;
        dailyLog.endTm = endTm;
        dailyLog.member = member;
        dailyLog.project = project;
        dailyLog.content = content;
        return dailyLog;
    }

}
