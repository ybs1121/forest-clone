package com.study.newforest2.biz;

import com.study.newforest2.biz.dto.MemberDto;
import com.study.newforest2.biz.dto.ProjectDto;
import com.study.newforest2.biz.dto.WorkLogDto;
import com.study.newforest2.biz.entity.Member;
import com.study.newforest2.biz.service.MemberProjectMappService;
import com.study.newforest2.biz.service.MemberService;
import com.study.newforest2.biz.service.ProjectService;
import com.study.newforest2.biz.service.WorkLogService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitData {

    private final MemberService memberService;
    private final ProjectService projectService;
    private final MemberProjectMappService memberProjectMappService;
    private final WorkLogService workLogService;

    @PostConstruct
    void init() {
        MemberDto memberDto = new MemberDto();
        memberDto.setPart("Dev");
        memberDto.setUnit("2nd");
        memberDto.setName("김길동");
        long memberId = memberService.addMember(memberDto);
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("내부 프로젝트");
        projectDto.setStDt("20240101");
        projectDto.setEndDt("20241230");
        long projectId = projectService.addProject(projectDto);

        ProjectDto projectDto2 = new ProjectDto();
        projectDto2.setName("업무일지 프로젝트");
        projectDto2.setStDt("20231101");
        projectDto2.setEndDt("20241030");
        long projectId2 = projectService.addProject(projectDto2);


        memberProjectMappService.insertMemberProjectMapping(memberId, projectId);
        WorkLogDto workLogDto = new WorkLogDto();
        workLogDto.setProjectId(projectId);
        workLogDto.setMemberId(memberId);
        workLogDto.setWorkTxt("열심히 일했습니다.");
        workLogDto.setStdDt("20240315");
        workLogDto.setWorkStTm("0900");
        workLogDto.setWorkEdTm("1800");
        workLogService.addWorkLog(workLogDto);

    }
}
