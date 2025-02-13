package com.my.mapService.service;

import com.my.mapService.dto.Member;
import com.my.mapService.repository.MapMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberService {
    //1.필드 주입방법
//    @Autowired
//    MapMemberRepository memberRepository;

    //2.필드 주입방법
    //@RequiredArgsConstructor
//    private final MapMemberRepository repository;

    //3.생성자 주입방법
    private final MapMemberRepository memberRepository;

    public MemberService(MapMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입기능
    public Long join(Member member){
        Optional<Member> result = memberRepository.findByName(member.getName());

        if(result.isPresent() && result.get().getAddress().equals(member.getAddress())){
            return -1L;
        }else{
            Member save = memberRepository.save(member);
            return save.getId();
        }
    }

    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public void update(Member member) {
        memberRepository.updateById(member.getId(),member);
    }
}
