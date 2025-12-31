package com.example.demo.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("") //클래스 내 모든 메서드 앞에 공통으로 해당
@Controller
public class DefaultController {

    // 1. 요청한 url을 리다이랙트로 보내기
    @RequestMapping({"", "/"})
    public String empty() {
        return "index";
    }
    @RequestMapping("/index")
    public String empty2() {
        return "redirect:/";
    }


    /*
    // 1. 요청한 url이 변하지 않음.
    @RequestMapping({"", "/", "/index"})
    public String empty() {
        return "index";
    }

    // 이전
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    */



    @RequestMapping("/add")
    public String add(int a, int b, Model model) {
        System.out.println("a : " + a);
        System.out.println("b : " + b);

        int sum = a + b;
        model.addAttribute("sum", sum);
        return "add";
    }

    @RequestMapping("/board")
    public String board(@RequestParam String title, String content, Model model) {
        /*
        System.out.println("a : " + a);
        System.out.println("b : " + b);
        int result = a * b;
        */
        model.addAttribute("result", 112233);
        return "board";
    }

}
