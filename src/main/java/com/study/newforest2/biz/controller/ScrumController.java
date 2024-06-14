package com.study.newforest2.biz.controller;

import com.study.newforest2.biz.dto.ScrumDto;
import com.study.newforest2.biz.dto.ScrumFind;
import com.study.newforest2.biz.service.ScrumService;
import com.study.newforest2.core.common.ResultCode;
import com.study.newforest2.core.common.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/v3/api/scrum")
@RestController
public class ScrumController {

    private final ScrumService scrumService;

    @PostMapping
    private StandardResponse<ResultCode> addScrum(@RequestBody ScrumDto scrumDto){
        return StandardResponse.success(scrumService.addScrum(scrumDto));
    }


    @PatchMapping
    private StandardResponse<ResultCode> modScrum(@RequestBody ScrumDto scrumDto){
        return StandardResponse.success(scrumService.modScrum(scrumDto));
    }

    @GetMapping("/find")
    private StandardResponse<ScrumDto> findScrum(ScrumFind scrumFind){
        return StandardResponse.success(null);
    }
}
