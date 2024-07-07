package com.study.newforest2.biz.dto;

import com.study.newforest2.biz.entity.Member;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Service;

// 사용자 DTO
@Data
@Service
@ToString
@RedisHash(timeToLive = 60)
public class MemberDto {

    private Long id;

    private String unit;

    private String part;

    private String name;

    //컨버터 로직
    public static MemberDto toDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.id = member.getId();
        memberDto.unit = member.getUnit();
        memberDto.part = member.getPart();
        memberDto.name = member.getName();
        return memberDto;
    }


    @Getter
    @Setter
    public static class Project {
        private Long memberId;
        private Long projectId;
    }

}
