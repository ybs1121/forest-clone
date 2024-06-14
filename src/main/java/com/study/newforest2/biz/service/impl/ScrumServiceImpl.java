package com.study.newforest2.biz.service.impl;

import com.study.newforest2.biz.dto.ScrumDto;
import com.study.newforest2.biz.dto.ScrumFind;
import com.study.newforest2.biz.entity.Member;
import com.study.newforest2.biz.entity.MemberProjectMapp;
import com.study.newforest2.biz.entity.Project;
import com.study.newforest2.biz.entity.Scrum;
import com.study.newforest2.biz.repository.MemberProjectMappRepository;
import com.study.newforest2.biz.repository.MemberRepository;
import com.study.newforest2.biz.repository.ProjectRepository;
import com.study.newforest2.biz.repository.ScrumRepository;
import com.study.newforest2.biz.service.ScrumService;
import com.study.newforest2.core.common.BizException;
import com.study.newforest2.core.common.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ScrumServiceImpl implements ScrumService {

    private final ScrumRepository scrumRepository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final MemberProjectMappRepository memberProjectMappRepository;

    @Override
    public ResultCode addScrum(ScrumDto scrumDto) {
        Project project = projectRepository.selectProjectOne(scrumDto.getProjectId());

        if (project == null) {
            throw new BizException(200, "프로젝트를 찾을 수 없습니다.");
        }

        Member member = memberRepository.selectMemberOne(scrumDto.getMemberId());
        if (member == null) {
            throw new BizException(200, "사용자를 찾을 수 없습니다.");
        }

        if (!memberProjectMappRepository.isExist(member, project)) {
            throw new BizException(200, "프로젝트에 속해있는 사용자가 아닙니다.");
        }


        Scrum scrum = Scrum.toDao(scrumDto, project, member);
        scrumRepository.insertScrum(scrum);


        return ResultCode.RESULT_OK;
    }

    @Override
    public ResultCode modScrum(ScrumDto scrumDto) {
        Project project = projectRepository.selectProjectOne(scrumDto.getProjectId());

        if (project == null) {
            throw new BizException(200, "프로젝트를 찾을 수 없습니다.");
        }

        Scrum scrum = scrumRepository.selectScrum(scrumDto.getId());

        if (scrum == null) {
            throw new BizException(200, "스크럼을 찾을 수 없습니다.");
        }


        // mod
        scrum.setScrumTitle(scrumDto.getScrumTitle());
        scrum.setScrumContext(scrumDto.getScrumContext());
        scrum.setStDt(scrumDto.getStDt());
        scrum.setEndDt(scrumDto.getEndDt());

        return ResultCode.RESULT_OK;
    }

    @Override
    public List<ScrumDto> getScrumList(ScrumFind scrumFind) {
        List<Scrum> scrums = scrumRepository.selectScrumByFind(scrumFind);
        return scrums.stream().map(scrum -> ScrumDto.toDto(scrum)).collect(Collectors.toList());
    }
}
