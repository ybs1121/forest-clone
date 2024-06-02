package com.study.newforest2.biz.controller;

import com.study.newforest2.biz.dto.DailyLogDto;
import com.study.newforest2.biz.service.DailyLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/daily-log")
public class DailyLogController {

    private final DailyLogService dailyLogService;

    @PostMapping
    public String postDailyLog(@RequestBody DailyLogDto dailyLogDto) {
        dailyLogService.addDailyLog(dailyLogDto);
        return "success";
    }
}
