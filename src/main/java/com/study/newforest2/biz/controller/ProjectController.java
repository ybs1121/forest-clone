package com.study.newforest2.biz.controller;

import com.study.newforest2.biz.dto.MemberDto;
import com.study.newforest2.biz.dto.ProjectDto;
import com.study.newforest2.biz.dto.ProjectFind;
import com.study.newforest2.biz.service.ProjectService;
import com.study.newforest2.core.common.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v3/api/project")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public String addProject(@RequestBody ProjectDto projectDto) {
        projectService.addProject(projectDto);
        return "success";
    }

    @GetMapping
    public ProjectDto getProjectOne(long id) {
        return projectService.getProject(id);
    }

    @GetMapping("/member")
    public List<MemberDto> getMembers(long id) {
        return projectService.getMemberList(id);
    }


    @GetMapping("/find")
    public StandardResponse<List<ProjectDto>> getProjects(ProjectFind projectFind) {
        return StandardResponse.success(projectService.getProjectList(projectFind));
    }

}
