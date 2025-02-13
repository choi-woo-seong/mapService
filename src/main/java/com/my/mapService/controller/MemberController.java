package com.my.mapService.controller;

import com.my.mapService.dto.Member;
import com.my.mapService.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/members")
//@RequiredArgsConstructor
public class MemberController {
//    private final MemberService service;

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

//    @Autowired
//    MemberService service;

    @GetMapping("/new")
    public String memberAdd(Model model) {
        model.addAttribute("member",new Member());
        return "/members/createMemberForm";
    }

    @GetMapping("")
    public String memberList(Model model) {
        List<Member> memberList = service.findAll();
        model.addAttribute("memberList",memberList);
        return "/members/memberList";
    }

    @PostMapping("/new")
    public String createMember(Member member) {
        service.join(member);
        return "redirect:/members";
    }

    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable("id")Long id) {
        service.deleteById(id);
        return "redirect:/members";
    }

    @GetMapping("/update/{id}")
    public String updateMember(@PathVariable("id")Long id,Model model) {
        model.addAttribute("member",service.findOne(id));
        return "/members/updateMemberForm";
    }

    @PostMapping("/update")
    public String update(Member member) {
        service.update(member);
        return "redirect:/members";
    }
}
