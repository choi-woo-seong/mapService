package com.my.mapService.repository;

import com.my.mapService.dto.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

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
        List<Member> memberList = new ArrayList<>(store.values());

        return memberList;
    }

    @Override
    public Optional<Member> findByName(String name) {
        Optional<Member> result = store.values().stream().filter(x->x.getName().equals(name)).findAny();

        return result;

//        for(Long key : store.keySet()){
//            if(store.get(key).getName().equals(name)){
//                Optional<Member> r = Optional.ofNullable(store.get(key));
//                return r;
//            }
//        }
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public Member updateById(Long memberId, Member member) {
        store.put(memberId,member);

        return store.get(memberId);
    }

    public void clearStore() {
        store.clear();
        sequence = 1L;
    }
}
