package com.study.newforest2;

import com.study.newforest2.biz.dto.MemberDto;
import com.study.newforest2.biz.dto.ProjectDto;
import com.study.newforest2.biz.entity.Member;
import com.study.newforest2.biz.service.DailyLogService;
import com.study.newforest2.biz.service.MemberProjectMappService;
import com.study.newforest2.biz.service.MemberService;
import com.study.newforest2.biz.service.ProjectService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class Init {

    private final InitInner initInner;

    @PostConstruct
    public void init() {
        initInner.init();
    }


    @Transactional
    @Component
    @RequiredArgsConstructor
    @Slf4j
    static class InitInner {

        private final MemberService memberService;
        private final ProjectService projectService;
        private final DailyLogService dailyLogService;
        private final MemberProjectMappService memberProjectMappService;

        public void init() {
            log.info("Init start");
            MemberDto memberDto = new MemberDto();
            memberDto.setPart("2nd");
            memberDto.setUnit("2Unit");
            memberDto.setName("김길동");
            long memberId = memberService.addMember(memberDto);

            ProjectDto projectDto = new ProjectDto();
            projectDto.setName("포레스트 프로젝트");
            projectDto.setStDt("20240423");
            projectDto.setEndDt("20250423");
            long projectId = projectService.addProject(projectDto);
            memberProjectMappService.insertMemberProjectMapping(memberId,projectId);
            log.info("Init completed");
        }
    }



}
