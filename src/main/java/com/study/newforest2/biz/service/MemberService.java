package com.study.newforest2.biz.service;


import com.study.newforest2.biz.dto.MemberDto;
import com.study.newforest2.biz.dto.ProjectDto;

import java.util.List;

public interface MemberService {

    long addMember(MemberDto memberDto);

    List<MemberDto> getMembers();

    MemberDto getMember(long id);

    List<ProjectDto> getProjects(long id);
}
