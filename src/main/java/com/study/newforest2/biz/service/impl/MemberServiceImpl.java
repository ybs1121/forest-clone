package com.study.newforest2.biz.service.impl;

import com.study.newforest2.biz.dto.MemberDto;
import com.study.newforest2.biz.dto.ProjectDto;
import com.study.newforest2.biz.entity.Member;
import com.study.newforest2.biz.entity.Project;
import com.study.newforest2.biz.repository.MemberRepository;
import com.study.newforest2.biz.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final CacheManager cacheManager;

//    @CachePut(value = "members")
    @Transactional
    @Override
    public long addMember(MemberDto memberDto) {
        Member member = Member.convert(memberDto.getUnit(), memberDto.getPart(), memberDto.getName());
        // 가정 : Member는 자주 추가되지 않는다. Cache Update를 해준다.
        long memberId = memberRepository.insertMember(member);
        Cache members = cacheManager.getCache("members");
        List<MemberDto> meberDtoList = memberRepository.findMembers().stream().map(m -> MemberDto.toDto(m)).collect(Collectors.toList());
        members.put("members", meberDtoList);
        log.info("Cache members: {}", members);

        return memberId;
    }

//    @Cacheable(value = "members")
    @Override
    public List<MemberDto> getMembers() {
        Cache cache = cacheManager.getCache("members");
        if (cache.get("members") != null) {
            log.info("MemberCache Hit");
            return (List<MemberDto>) cache.get("members").get();
        }

        try {
            log.info("MemberCache Miss");
            Thread.sleep(3000); // 조회에 3초 걸린다고 가정 Cache 저장
        } catch (Exception e) {
            log.error("e :  {}", e.getCause());
            throw new RuntimeException(e);
        }

        List<MemberDto> meberDtoList = memberRepository.findMembers().stream().map(member -> MemberDto.toDto(member)).collect(Collectors.toList());
        cache.put("members", meberDtoList); // Cache에 저장한다.
        return meberDtoList;
    }

    @Override
    public MemberDto getMember(long id) {
        Member member = memberRepository.selectMemberOne(id);
        return MemberDto.toDto(member);
    }

    @Override
    public List<ProjectDto> getProjects(long id) {
        List<Project> projectList = memberRepository.findProject(id);
        return projectList.stream().map(p -> ProjectDto.toDto(p)).collect(Collectors.toList());
    }
}
