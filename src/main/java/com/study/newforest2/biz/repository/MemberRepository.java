package com.study.newforest2.biz.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.newforest2.biz.dto.MemberFind;
import com.study.newforest2.biz.dto.ScrumFind;
import com.study.newforest2.biz.entity.*;
import com.study.newforest2.core.common.BizException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext // 엔티티 메니저를 주입해주는 어노테이션 -> 생성자 주입해줌
    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    /**
     * 사용자 저장
     * 조건 1. 사용자 구분을 위해 중복된 이름은 허용하지 않는다. 홍길동, 홍길동1 (todo)
     * @param member
     * @return
     */
    public long insertMember(final Member member) {
        em.persist(member);
        return member.getId();
    }

    /**
     * 사용자 조회 단건
     * @param id
     * @return
     */
    public Member selectMemberOne(long id) {
        return em.find(Member.class, id);
    }

    public List<Project> findProject(final long id) {
        return em.createQuery("select p FROM MemberProjectMapp mpm JOIN mpm.project p WHERE mpm.member.id = :id",Project.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Member> selectMemberByFind(MemberFind find) {
        return queryFactory.selectFrom(QMember.member)
                .join(QMember.member.projectList, QMemberProjectMapp.memberProjectMapp)
                .join(QMemberProjectMapp.memberProjectMapp.project, QProject.project)
                .where(findOption(find))
                .fetch();

    }

    // 조건
    private BooleanExpression findOption(MemberFind find) {
        QMember member = QMember.member;
        QProject project = QProject.project;
        if (find == null) {
            return null;
        }

        if ("1".equals(find.getFindOption())) {
            return member.name.contains(find.getFindText());
        } else if ("2".equals(find.getFindOption())) {
            return project.name.contains(find.getFindText());
        } else {
            throw new BizException(400, "옵션값이 올바르지 않습니다.");
        }

    }



//    public List<Object[]> findProjectsAndMembersByMemberId(Long memberId) {
//        return em.createQuery(
//                        "SELECT p, m FROM MemberProjectMapp mpm JOIN mpm.project p JOIN mpm.member m WHERE mpm.member.id = :memberId", Object[].class)
//                .setParameter("memberId", memberId)
//                .getResultList();
//    }
//
//    List<Object[]> results = memberRepository.findProjectsAndMembersByMemberId(memberId);
//for (Object[] result : results) {
//        Project project = (Project) result[0];
//        Member member = (Member) result[1];
//        // 이제 project와 member 객체를 사용할 수 있습니다.
//    }



}
