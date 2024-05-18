package com.study.newforest2.biz.entity;

import com.study.newforest2.biz.dto.WorkLogDto;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class WorkLog {

    @Id
    @GeneratedValue
    @Column(name = "work_log_id")
    private long id;

    private String stdDt;

    private String workStTm;

    private String workEdTm;

    private String workTxt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member commentMember;

    private String comment;


    public static WorkLog toDao(String stdDt, String workStTm, String workEdTm
            , String workTxt, Member member, Project project, Member commentMember, String comment) {
        WorkLog workLog = new WorkLog();
        workLog.stdDt = stdDt;
        workLog.workEdTm = workEdTm;
        workLog.workStTm = workStTm;
        workLog.workTxt = workTxt;
        workLog.member = member;
        workLog.project = project;
        workLog.commentMember = commentMember;
        workLog.comment = comment;

        return workLog;
    }

}
