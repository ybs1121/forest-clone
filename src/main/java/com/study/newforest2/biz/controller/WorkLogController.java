package com.study.newforest2.biz.controller;

import com.study.newforest2.biz.dto.ProjectDto;
import com.study.newforest2.biz.dto.WorkLogDto;
import com.study.newforest2.biz.service.WorkLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v3/api/work-log")
public class WorkLogController {

    private final WorkLogService workLogService;

    @PostMapping
    public String addWorkLog(@RequestBody WorkLogDto workLogDto) {
        workLogService.addWorkLog(workLogDto);
        return "success";
    }

    @GetMapping("/{memberId}")
    public List<WorkLogDto> getWorkLog(@PathVariable long memberId) {
        return workLogService.getWorkLogListByMemberId(memberId);
    }

}
