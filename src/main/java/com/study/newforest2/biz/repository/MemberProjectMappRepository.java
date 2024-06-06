package com.study.newforest2.biz.repository;

import com.study.newforest2.biz.entity.Member;
import com.study.newforest2.biz.entity.MemberProjectMapp;
import com.study.newforest2.biz.entity.Project;
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

    public boolean isExist(Member member, Project project) {
        return (boolean) em.createQuery(
                        "SELECT EXISTS (SELECT 1 FROM MemberProjectMapp WHERE member = :member AND project =:prject)"
                ).setParameter("member", member)
                .setParameter("prject", project)
                .getSingleResult();
    }
}
