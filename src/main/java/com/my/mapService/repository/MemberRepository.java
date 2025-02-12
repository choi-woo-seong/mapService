package com.my.mapService.repository;

import com.my.mapService.dto.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id);

    List<Member> findAll();

    Optional<Member> findByName(String name);

    void deleteById(Long id);

    Member updateById(Long memberId, Member member);

}
