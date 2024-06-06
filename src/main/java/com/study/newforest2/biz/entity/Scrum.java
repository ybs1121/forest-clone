package com.study.newforest2.biz.entity;

import com.study.newforest2.biz.dto.ScrumDto;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Scrum {

    @Id
    @GeneratedValue
    @Column(name = "scrum_id")
    private Long id;

    private String scrumTitle;

    private String scrumContext;

    private String stDt;

    private String endDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    public static Scrum toDao(ScrumDto scrumDto, Project project, Member member) {
        Scrum scrum = new Scrum();
        scrum.setScrumTitle(scrumDto.getScrumTitle());
        scrum.setScrumContext(scrumDto.getScrumContext());
        scrum.setStDt(scrumDto.getStDt());
        scrum.setEndDt(scrumDto.getEndDt());
        scrum.setProject(project);
        scrum.setMember(member);
        return scrum;
    }

}
