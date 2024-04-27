package com.study.newforest2.biz.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // 전략은 우선 오토로 한다
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String unit;

    @Column
    private String part;

    @Column
    private String name;

    @OneToMany(mappedBy = "member")
    private List<MemberProjectMapp> projectList; // 내가 속해있는 프로젝트 목록 아이디

    protected Member () {}

    //데이터 컨버터 로직 - DTO -> DAO
    public static Member convert(String unit, String part, String name) {
        Member member = new Member();
        member.unit = unit;
        member.part = part;
        member.name = name;
        return member;
    }

}
