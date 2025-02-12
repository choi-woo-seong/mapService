package com.my.mapService.repository;

import com.my.mapService.dto.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapMemberRepositoryTest {
    MapMemberRepository repository = new MapMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    void save() {
        Member member = new Member();
        member.setName("장원영");
        member.setAddress("서울");

        Member result = repository.save(member);

        Long saveId = 1L;

//        Assertions.assertEquals(result.getId(),saveId);


        org.assertj.core.api.Assertions.assertThat(result.getId()).isEqualTo(saveId);
    }

    @Test
    @DisplayName("ID로 검색하기")
    void findById() {
        Member member = new Member();
        member.setName("안유진");
        member.setAddress("제주");

        repository.save(member);

        Long id = 1L;

        Member findMember = repository.findById(id).get();

        org.assertj.core.api.Assertions.assertThat(findMember.getId()).isEqualTo(id);
    }
}