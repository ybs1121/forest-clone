package com.study.newforest2.biz.repository;

import com.study.newforest2.biz.entity.DailyLog;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DailyLogRepository {

    private final EntityManager em;


    public long insertDailyLog(DailyLog dailyLog) {
        em.persist(dailyLog);
        return dailyLog.getId();
    }

    public DailyLog selectDailyLog(long id) {
        return em.find(DailyLog.class, id);
    }
}
