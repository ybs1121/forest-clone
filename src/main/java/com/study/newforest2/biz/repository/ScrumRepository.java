package com.study.newforest2.biz.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.newforest2.biz.dto.ScrumFind;
import com.study.newforest2.biz.entity.QScrum;
import com.study.newforest2.biz.entity.Scrum;
import com.study.newforest2.core.common.BizException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScrumRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public long insertScrum(Scrum scrum) {
        em.persist(scrum);
        return scrum.getId();
    }

    public Scrum selectScrum(long id) {
        return em.find(Scrum.class, id);
    }

    public List<Scrum> selectScrumByFind(ScrumFind scrumFind) {
        return queryFactory.selectFrom(QScrum.scrum)
                .where(findOption(scrumFind))
                .fetch();
    }


    // 조건
    private BooleanExpression findOption(ScrumFind find) {
        QScrum scrum = QScrum.scrum;
        if (find == null) {
            return null;
        }

        if ("1".equals(find.getFindOption())) {
            return scrum.scrumContext.contains(find.getText());
        } else if ("2".equals(find.getFindOption())) {
            return scrum.scrumTitle.contains(find.getText());
        } else {
            throw new BizException(400, "옵션값이 올바르지 않습니다.");
        }

    }
}
