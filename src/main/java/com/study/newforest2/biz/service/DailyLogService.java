package com.study.newforest2.biz.service;

import com.study.newforest2.biz.dto.DailyLogDto;

public interface DailyLogService {

    long addDailyLog(DailyLogDto dailyLogDto);

    DailyLogDto getDailyLogOne(long id);

}
