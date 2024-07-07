package com.study.newforest2.biz.repository;

import com.study.newforest2.biz.dto.MemberDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisMemberRepository extends CrudRepository<MemberDto, Long> {
}
