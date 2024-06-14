package com.study.newforest2.biz.repository;

import com.study.newforest2.biz.dto.ScrumFind;
import com.study.newforest2.biz.entity.Scrum;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScrumRepository {

    private final EntityManager em;


    public long insertScrum(Scrum scrum) {
        em.persist(scrum);
        return scrum.getId();
    }

    public Scrum selectScrum(long id) {
        return em.find(Scrum.class, id);
    }

//    public Scrum selectScrumByFind(ScrumFind scrumFind) {
//
//    }
}
