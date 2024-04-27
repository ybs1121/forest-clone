package com.study.newforest2.biz.dto;

import com.study.newforest2.biz.entity.MemberProjectMapp;
import com.study.newforest2.biz.entity.Project;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class ProjectDto {

    private Long id;

    private String name;

    private String stDt;

    private String endDt;

    private List<MemberProjectMapp> memberList; // 멤버 아이디 리스트



    // 컨버터 로직
    public static ProjectDto toDto (Project project) {
        ProjectDto dto = new ProjectDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setStDt(project.getStDt());
        dto.setEndDt(project.getEndDt());
        return dto;
    }

}
