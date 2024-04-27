package com.study.newforest2.biz.repository;

import com.study.newforest2.biz.entity.Member;
import com.study.newforest2.biz.entity.Project;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectRepository {

    private final EntityManager em;


    public long insertProject(final Project project) {
        em.persist(project);
        return project.getId();
    }

    public Project selectProjectOne(final long id) {
        return em.find(Project.class, id);
    }

    public List<Member> findMembersByProject(final long projectId) {
        return em.createQuery("select m from MemberProjectMapp mpm join mpm.member m where mpm.project.id = :id", Member.class)
                .setParameter("id", projectId).getResultList();
    }
}
