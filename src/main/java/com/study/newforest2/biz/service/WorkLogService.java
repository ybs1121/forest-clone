package com.study.newforest2.biz.service;

import com.study.newforest2.biz.dto.WorkLogDto;

import java.util.List;

public interface WorkLogService {

    long addWorkLog(WorkLogDto workLogDto);

    List<WorkLogDto> getWorkLogListByMemberId(Long memberId);
}
