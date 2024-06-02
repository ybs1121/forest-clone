package com.study.newforest2.biz.service.impl;

import com.study.newforest2.biz.dto.DailyLogDto;
import com.study.newforest2.biz.entity.DailyLog;
import com.study.newforest2.biz.entity.Member;
import com.study.newforest2.biz.entity.Project;
import com.study.newforest2.biz.repository.DailyLogRepository;
import com.study.newforest2.biz.repository.MemberRepository;
import com.study.newforest2.biz.repository.ProjectRepository;
import com.study.newforest2.biz.service.DailyLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DailyLogServiceImpl implements DailyLogService {

    private final DailyLogRepository dailyLogRepository;
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    @Override
    public long addDailyLog(DailyLogDto dailyLogDto) {
        Member findMember = memberRepository.selectMemberOne(dailyLogDto.getMemberId());
        Project findProject = projectRepository.selectProjectOne(dailyLogDto.getProjectId());
        DailyLog dao = DailyLog.toDao(dailyLogDto.getContent(), dailyLogDto.getStdDt(),
                dailyLogDto.getStTm(), dailyLogDto.getEndTm(), findMember, findProject);
        return dailyLogRepository.insertDailyLog(dao);
    }

    @Override
    public DailyLogDto getDailyLogOne(long id) {
        DailyLog dailyLog = dailyLogRepository.selectDailyLog(id);
        return DailyLogDto.toDto(dailyLog);
    }
}
