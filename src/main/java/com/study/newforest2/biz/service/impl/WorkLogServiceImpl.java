package com.study.newforest2.biz.service.impl;

import com.study.newforest2.biz.dto.WorkLogDto;
import com.study.newforest2.biz.entity.Member;
import com.study.newforest2.biz.entity.Project;
import com.study.newforest2.biz.entity.WorkLog;
import com.study.newforest2.biz.repository.MemberRepository;
import com.study.newforest2.biz.repository.ProjectRepository;
import com.study.newforest2.biz.repository.WorkLogRepository;
import com.study.newforest2.biz.service.WorkLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class WorkLogServiceImpl implements WorkLogService {

    private final WorkLogRepository workLogRepository;
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    @Override
    public long addWorkLog(WorkLogDto workLogDto) {
        Member findMember = memberRepository.selectMemberOne(workLogDto.getMemberId());
        Project findProject = projectRepository.selectProjectOne(workLogDto.getProjectId());
        return workLogRepository.insertWorkLog(WorkLog.toDao(workLogDto.getStdDt()
                , workLogDto.getWorkStTm(), workLogDto.getWorkEdTm(), workLogDto.getWorkTxt()
                , findMember, findProject));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkLogDto> getWorkLogListByMemberId(Long memberId) {
        return workLogRepository.findByMemberId(memberId).stream().map(workLog -> WorkLogDto.toDto(workLog))
                .collect(Collectors.toList());
    }
}
