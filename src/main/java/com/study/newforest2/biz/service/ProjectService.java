package com.study.newforest2.biz.service;

import com.study.newforest2.biz.dto.MemberDto;
import com.study.newforest2.biz.dto.ProjectDto;

import java.util.List;


public interface ProjectService {

    long addProject(ProjectDto project);
    ProjectDto getProject(long id);

    List<MemberDto> getMemberList(long id);
}
