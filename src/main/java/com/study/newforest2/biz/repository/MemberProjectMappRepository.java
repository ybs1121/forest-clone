package com.study.newforest2.biz.repository;

import com.study.newforest2.biz.entity.MemberProjectMapp;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberProjectMappRepository {

    private final EntityManager em;

    public long insertMemberProjectMapping(MemberProjectMapp memberProjectMapp) {
        em.persist(memberProjectMapp);
        return memberProjectMapp.getId();
    }
}
