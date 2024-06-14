package com.study.newforest2.biz.service;

import com.study.newforest2.biz.dto.ScrumDto;
import com.study.newforest2.biz.dto.ScrumFind;
import com.study.newforest2.core.common.ResultCode;

import java.util.List;

public interface ScrumService {

    ResultCode addScrum(ScrumDto scrumDto);

    ResultCode modScrum(ScrumDto scrumDto);

    List<ScrumDto> getScrumList(ScrumFind scrumFind);
}
