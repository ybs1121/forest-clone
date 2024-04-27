package com.study.newforest2.biz.service.impl;

import com.study.newforest2.biz.dto.MemberDto;
import com.study.newforest2.biz.dto.ProjectDto;
import com.study.newforest2.biz.entity.Member;
import com.study.newforest2.biz.entity.Project;
import com.study.newforest2.biz.repository.ProjectRepository;
import com.study.newforest2.biz.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public long addProject(ProjectDto projectDto) {
        Project project = Project.toDao(projectDto.getName(), projectDto.getStDt(), projectDto.getEndDt());
        projectRepository.insertProject(project);
        return project.getId();
    }

    public ProjectDto getProject(long id) {
        Project project = projectRepository.selectProjectOne(id);
        return ProjectDto.toDto(project);
    }

    @Override
    public List<MemberDto> getMemberList(long id) {
        List<Member> membersByProject = projectRepository.findMembersByProject(id);
        return membersByProject.stream().map(member -> MemberDto.toDto(member)).collect(Collectors.toList());
    }

}
