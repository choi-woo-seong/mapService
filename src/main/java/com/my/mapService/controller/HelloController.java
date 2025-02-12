package com.my.mapService.controller;

import com.my.mapService.dto.Hello;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String helloMain(Model model){
        model.addAttribute("data", "Welcome SpringBoot!!");
        return "hello";
    }

    @GetMapping("/hello-mvc")
    public String helloMvc(@RequestParam(name = "name",required = false,defaultValue = "홍길동")String name,
                           @RequestParam(name = "age", required = false)Integer age, Model model){
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "hello-template";
    }

    @GetMapping("/api-string")
    @ResponseBody
    public String apiString(@RequestParam("name")String name) {
        return "hello" + name;
    }

    @GetMapping("/api-object")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name) {
        Hello hello = new Hello();
        hello.setName(name);

        return hello;
    }
}
