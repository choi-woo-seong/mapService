package com.my.mapService.repository;

import com.my.mapService.dto.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Repository
public class MapMemberRepository implements MemberRepository{
    public static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 1L;
    @Override
    public Member save(Member member) {
        member.setId(sequence);
        sequence++;
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return List.of();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Member updateById(Long memberId, Member member) {
        return null;
    }

    public void clearStore() {
        store.clear();
        sequence = 1L;
    }
}
