package com.study.newforest2.biz.controller;

import com.study.newforest2.biz.dto.MemberDto;
import com.study.newforest2.biz.dto.ProjectDto;
import com.study.newforest2.biz.service.MemberProjectMappService;
import com.study.newforest2.biz.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v3/api/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberProjectMappService memberProjectMappService;

    @PostMapping
    public String addMember(@RequestBody MemberDto memberDto) {
        memberService.addMember(memberDto);
        return "success";  // todo : 포멧 만들어보기
    }

    @GetMapping
    public MemberDto getMember(Long id) {
        return memberService.getMember(id);
    }


    @PostMapping("/project")
    public String addMemberInProject(@RequestBody MemberDto.Project memberDtoProject) {
        memberProjectMappService.insertMemberProjectMapping(memberDtoProject.getMemberId(), memberDtoProject.getProjectId());
        return "success";  // todo : 포멧 만들어보기
    }

    @GetMapping("/project")
    public List<ProjectDto> getProject(long id) {
        return memberService.getProjects(id);
    }



}
