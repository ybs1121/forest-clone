package com.study.newforest2.biz.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Project {

    @Id
    @GeneratedValue
    @Column(name = "project_id")
    private Long id;

    private String name;

    private String stDt;

    private String endDt;

    @OneToMany(mappedBy = "project")
    private List<MemberProjectMapp> memberList; // 멤버 아이디 리스트

    protected Project() {
    }


    //convert 로직
    public static Project toDao(String name, String stDt, String endDt) {
        Project project = new Project();
        project.name = name;
        project.stDt = stDt;
        project.endDt = endDt;
        return project;
    }


}

