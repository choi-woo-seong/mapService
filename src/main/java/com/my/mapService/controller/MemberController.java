package com.my.mapService.controller;

import com.my.mapService.dto.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/members")
public class MemberController {
    @GetMapping("/new")
    public String memberAdd() {
        return "/members/createMemberForm";
    }

    @GetMapping("")
    public String memberList() {
        return "/members/memberList";
    }

    @PostMapping("/new")
    public String createMember(Member member) {

        return "redirect:/members";
    }
}
