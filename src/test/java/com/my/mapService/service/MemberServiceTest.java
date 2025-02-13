package com.my.mapService.service;

import com.my.mapService.dto.Member;
import com.my.mapService.repository.MapMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MapMemberRepository repository = new MapMemberRepository();
    MemberService memberService = new MemberService(repository);

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void 회원가입() {
        Member member = new Member();
        member.setName("장원영");
        member.setAddress("서울");

        Long saveId = memberService.join(member);

        Member findMember = memberService.findOne(saveId).orElse(null);

        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void 중복회원검사() {
        Member member = new Member();
        member.setName("장원영");
        member.setAddress("서울");

        Member member1 = new Member();
        member1.setName("안유진");
        member1.setAddress("제주");
        memberService.join(member);

        Long result = memberService.join(member1);

        Assertions.assertThat(result).isEqualTo(-1L);
    }

    @Test
    @DisplayName("아이디로 검색")
    public void findOne() {
        Member member = new Member();
        member.setName("장원영");
        member.setAddress("서울");
        memberService.join(member);

        Long saveId = 1L;

        Member findMember = memberService.findOne(saveId).orElse(null);

        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    @DisplayName("전체리스트 검색")
    public void findAll() {
        Member member = new Member();
        member.setName("장원영");
        member.setAddress("서울");
        memberService.join(member);

        Member member1 = new Member();
        member1.setName("안유진");
        member1.setAddress("제주");
        memberService.join(member1);

        List<Member> memberList = memberService.findAll();

        Assertions.assertThat(memberList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("삭제 테스트")
    public void deleteById() {
        Member jang = new Member();
        jang.setName("장원영");
        jang.setAddress("서울");
        memberService.join(jang);

        memberService.deleteById(1L);

        int size = memberService.findAll().size();

        Assertions.assertThat(size).isEqualTo(0);
    }

    @Test
    @DisplayName("수정하기")
    public void update() {
        Member jang = new Member();
        jang.setName("장원영");
        jang.setAddress("서울");
        memberService.join(jang);

        Member updatejang = new Member();
        updatejang.setId(1L);
        updatejang.setName("안유진");
        updatejang.setAddress("제주");

        memberService.update(updatejang);
        Member result = memberService.findOne(1L).orElse(null);

        Assertions.assertThat(result.getName()).isEqualTo("안유진");
        Assertions.assertThat(result.getAddress()).isEqualTo("제주");
    }
}