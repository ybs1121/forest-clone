package com.study.newforest2.biz.service.impl;

import com.study.newforest2.biz.dto.MemberDto;
import com.study.newforest2.biz.dto.ProjectDto;
import com.study.newforest2.biz.entity.Member;
import com.study.newforest2.biz.entity.Project;
import com.study.newforest2.biz.repository.MemberRepository;
import com.study.newforest2.biz.repository.RedisMemberRepository;
import com.study.newforest2.biz.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
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
    private final RedisMemberRepository redisMemberRepository;


//    private final RedisTemplate<String, Object> redisTemplate;

    @Transactional
    @Override
    public long addMember(MemberDto memberDto) {
        Member member = Member.convert(memberDto.getUnit(), memberDto.getPart(), memberDto.getName());
        return memberRepository.insertMember(member);
    }

    @Override
    public MemberDto getMember(long id) {
        //Redis에 저장된 데이터 있는지 확인
        MemberDto o = redisMemberRepository.findById(id).orElse(null);
        if (o == null) {
            Member member = memberRepository.selectMemberOne(id);
            MemberDto dto = MemberDto.toDto(member);
            redisMemberRepository.save(dto);
            log.info("Member id : {} 를 redis에 저장합니다.",id);
            return dto;
        } else {
            log.info("Member id : {} 를 redis에서 꺼냅니다.", id);
        }

        return o;
    }

    @Override
    public List<ProjectDto> getProjects(long id) {
        List<Project> projectList = memberRepository.findProject(id);
        return projectList.stream().map(p -> ProjectDto.toDto(p)).collect(Collectors.toList());
    }
}
