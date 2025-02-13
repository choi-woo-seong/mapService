package com.my.mapService.repository;

import com.my.mapService.dto.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

        repository.save(member);

        Member result = repository.findById(member.getId()).get();


        Assertions.assertEquals(member,result);


        assertThat(result).isEqualTo(result);
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

    @Test
    @DisplayName("전체검색테스트")
    void findAll() {
        Member jang = new Member();
        jang.setName("장원영");
        jang.setAddress("서울");
        repository.save(jang);

        Member ahn = new Member();
        ahn.setName("안유진");
        ahn.setAddress("제주");
        repository.save(ahn);

        List<Member> list = repository.findAll();

        assertThat(list.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("이름으로 검색하기")
    void findByName() {
        Member jang = new Member();
        jang.setName("장원영");
        jang.setAddress("서울");
        repository.save(jang);

        Member ahn = new Member();
        ahn.setName("안유진");
        ahn.setAddress("제주");
        repository.save(ahn);

        Member result = repository.findByName("장원영").get();

        assertThat(result).isEqualTo(jang);

    }

    @Test
    @DisplayName("ID로 삭제하기")
    void deleteById() {
        Member jang = new Member();
        jang.setName("장원영");
        jang.setAddress("서울");
        repository.save(jang);

        repository.deleteById(1L);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("데이터 수정 확인")
    void updateById() {
        Member jang = new Member();
        jang.setName("장원영");
        jang.setAddress("서울");
        repository.save(jang);

        Member updatejang = new Member();
        updatejang.setName("장원영");
        updatejang.setAddress("제주");

        Member result = repository.updateById(1L,updatejang);

        assertThat(result.getAddress()).isEqualTo("제주");

    }
}