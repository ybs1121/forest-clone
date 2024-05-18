package com.study.newforest2.biz.service.impl;

import com.study.newforest2.biz.dto.WorkLogDto;
import com.study.newforest2.biz.entity.Member;
import com.study.newforest2.biz.entity.Project;
import com.study.newforest2.biz.entity.WorkLog;
import com.study.newforest2.biz.repository.MemberRepository;
import com.study.newforest2.biz.repository.ProjectRepository;
import com.study.newforest2.biz.repository.WorkLogRepository;
import com.study.newforest2.biz.service.WorkLogService;
import com.study.newforest2.core.common.BizException;
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

        if (findMember == null) {
            throw new BizException(404, "존재하지 않는 회원입니다.");
        }

        Project findProject = projectRepository.selectProjectOne(workLogDto.getProjectId());
        if (findProject == null) {
            throw new BizException(404, "존재하지 않는 프로젝트입니다.");
        }

        Member commentMember = null;
        if (workLogDto.getCommentMemberId() != null) {
            commentMember = memberRepository.selectMemberOne(workLogDto.getCommentMemberId());
            if (commentMember == null) {
                throw new BizException(404, "존재하지 않는 회원입니다.");
            }
        }


        return workLogRepository.insertWorkLog(WorkLog.toDao(workLogDto.getStdDt()
                , workLogDto.getWorkStTm(), workLogDto.getWorkEdTm(), workLogDto.getWorkTxt()
                , findMember, findProject, commentMember, workLogDto.getComment()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkLogDto> getWorkLogListByMemberId(Long memberId) {
        return workLogRepository.findByMemberId(memberId).stream().map(workLog -> WorkLogDto.toDto(workLog))
                .collect(Collectors.toList());
    }
}
