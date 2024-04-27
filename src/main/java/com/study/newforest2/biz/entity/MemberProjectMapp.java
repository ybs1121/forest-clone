package com.study.newforest2.biz.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class MemberProjectMapp {

    @Id
    @GeneratedValue
    @Column(name = "member_project_mapp_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    protected MemberProjectMapp() {}

    // 프로젝트 매핑
    public static MemberProjectMapp memberProjectMapping(Project project, Member member) {
        MemberProjectMapp memberProjectMapp = new MemberProjectMapp();
        memberProjectMapp.project = project;
        memberProjectMapp.member = member;
        return memberProjectMapp;
    }
}
