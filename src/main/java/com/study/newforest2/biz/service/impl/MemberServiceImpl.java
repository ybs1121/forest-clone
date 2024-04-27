package com.study.newforest2.biz.service.impl;

import com.study.newforest2.biz.dto.MemberDto;
import com.study.newforest2.biz.dto.ProjectDto;
import com.study.newforest2.biz.entity.Member;
import com.study.newforest2.biz.entity.Project;
import com.study.newforest2.biz.repository.MemberRepository;
import com.study.newforest2.biz.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public long addMember(MemberDto memberDto) {
        Member member = Member.convert(memberDto.getUnit(), memberDto.getPart(), memberDto.getName());
        return memberRepository.insertMember(member);
    }

    @Override
    public MemberDto getMember(long id) {
        Member member = memberRepository.selectMemberOne(id);
        return MemberDto.toDto(member);
    }

    @Override
    public List<ProjectDto> getProjects(long id) {
        List<Project> projectList = memberRepository.findProject(id);
        return projectList.stream().map(p -> ProjectDto.toDto(p)).collect(Collectors.toList());
    }
}
