package com.study.newforest2.biz.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.newforest2.biz.dto.MemberDto;
import com.study.newforest2.biz.dto.ProjectDto;
import com.study.newforest2.biz.dto.ProjectFind;
import com.study.newforest2.biz.entity.Member;
import com.study.newforest2.biz.entity.Project;
import com.study.newforest2.biz.entity.QProject;
import com.study.newforest2.biz.repository.ProjectRepository;
import com.study.newforest2.biz.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final JPAQueryFactory queryFactory;

    @Transactional
    public long addProject(ProjectDto projectDto) {
        Project project = Project.toDao(projectDto.getName(), projectDto.getStDt(), projectDto.getEndDt());
        projectRepository.insertProject(project);
        return project.getId();
    }

    public ProjectDto getProject(long id) {
        Project project = projectRepository.selectProjectOne(id);
        return ProjectDto.toDto(project);
    }

    @Override
    public List<MemberDto> getMemberList(long id) {
        List<Member> membersByProject = projectRepository.findMembersByProject(id);
        return membersByProject.stream().map(member -> MemberDto.toDto(member)).collect(Collectors.toList());
    }

    @Override
    public List<ProjectDto> getProjectList(ProjectFind projectFind) {
        List<Project> fetch = queryFactory.select(QProject.project)
                .from(QProject.project)
                .where(projectNameLike(projectFind)
                        , projectStartDtFilter(projectFind.getProjectStDt()))

                .fetch();
        List<ProjectDto> collect = fetch.stream().map(project -> ProjectDto.toDto(project)).collect(Collectors.toList());
        return collect;
    }


    private BooleanExpression projectNameLike(ProjectFind projectFind) {
        log.info(projectFind.getProjectName());
        if (StringUtils.isEmpty(projectFind.getProjectName())) {
            return null;
        }

        return QProject.project.name.like("%" + projectFind.getProjectName() + "%");
    }

    private BooleanExpression projectStartDtFilter(String dateString) {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }

        DateExpression<java.time.LocalDate> searchDate = toDate(dateString);
        StringExpression projectStDt = QProject.project.stDt;
//        StringExpression projectEdDt = QProject.project.endDt;

        return Expressions.asDate(projectStDt).loe(searchDate.stringValue());
//                .and(Expressions.asDate(projectEdDt).goe(searchDate.stringValue()));
    }


    private DateExpression<java.time.LocalDate> toDate(String dateString) {
        return Expressions.dateTemplate(java.time.LocalDate.class, "to_date({0}, 'YYYYMMDD')", dateString);
    }
}
