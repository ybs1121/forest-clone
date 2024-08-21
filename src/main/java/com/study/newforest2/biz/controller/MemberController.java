package com.study.newforest2.biz.controller;

import com.study.newforest2.biz.dto.MemberDto;
import com.study.newforest2.biz.dto.ProjectDto;
import com.study.newforest2.biz.service.MemberProjectMappService;
import com.study.newforest2.biz.service.MemberService;
import com.study.newforest2.core.common.StandardResponse;
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
    public StandardResponse<Long> addMember(@RequestBody MemberDto memberDto) {
        return StandardResponse.success(memberService.addMember(memberDto));
    }

    @GetMapping("/{memberId}")
    public StandardResponse<MemberDto> getMember(@PathVariable long memberId) {
        return StandardResponse.success(memberService.getMember(memberId));
    }

    @GetMapping
    public StandardResponse<List<MemberDto>> getMembers() {
        return StandardResponse.success(memberService.getMembers());
    }


    @PostMapping("/project")
    public StandardResponse<Long> addMemberInProject(@RequestBody MemberDto.Project memberDtoProject) {
        return StandardResponse.success(memberProjectMappService.insertMemberProjectMapping(memberDtoProject.getMemberId(), memberDtoProject.getProjectId()));  // todo : 포멧 만들어보기
    }

    @GetMapping("/project")
    public StandardResponse<List<ProjectDto>> getProject(long id) {
        return StandardResponse.success(memberService.getProjects(id));
    }


}
