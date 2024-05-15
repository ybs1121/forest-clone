package com.study.newforest2.biz.repository;

import com.study.newforest2.biz.entity.WorkLog;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WorkLogRepository {

    private final EntityManager em;


    public long insertWorkLog(WorkLog workLog) {
        em.persist(workLog);
        return workLog.getId();
    }

    public List<WorkLog> findByMemberId(Long memberId) {
//        return em.createQuery("" +
//                "select wl from WorkLog wl where wl.member.id = :memberId", WorkLog.class)
//                .setParameter("memberId", memberId)
//                .getResultList();
        return em.createQuery(
                        "select wl from WorkLog wl " +
                        "join fetch wl.project p " +
                        "where wl.member.id = :memberId", WorkLog.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
