package com.study.newforest2.biz.service.impl;

import com.study.newforest2.biz.entity.Member;
import com.study.newforest2.biz.entity.MemberProjectMapp;
import com.study.newforest2.biz.entity.Project;
import com.study.newforest2.biz.repository.MemberProjectMappRepository;
import com.study.newforest2.biz.repository.MemberRepository;
import com.study.newforest2.biz.repository.ProjectRepository;
import com.study.newforest2.biz.service.MemberProjectMappService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberProjectMappServiceImpl implements MemberProjectMappService {

    private final MemberProjectMappRepository memberProjectMappRepository;
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    @Override
    public long insertMemberProjectMapping(long memberId, long projectId) {
        Member member = memberRepository.selectMemberOne(memberId);
        Project project = projectRepository.selectProjectOne(projectId);
        MemberProjectMapp memberProjectMapp = MemberProjectMapp.memberProjectMapping(project, member);
        return memberProjectMappRepository.insertMemberProjectMapping(memberProjectMapp);
    }
}
