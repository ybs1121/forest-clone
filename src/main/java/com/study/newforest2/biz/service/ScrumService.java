package com.study.newforest2.biz.service;

import com.study.newforest2.biz.dto.ScrumDto;
import com.study.newforest2.core.common.ResultCode;

public interface ScrumService {

    ResultCode addScrum(ScrumDto scrumDto);

    ResultCode modScrum(ScrumDto scrumDto);
}
