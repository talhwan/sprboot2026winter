package com.example.demo.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@Controller
public class BoardPageController {

    @RequestMapping("/create")
    public String create(){
        return "board/create";
    }
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable String id){
        return "board/detail";
    }
    @RequestMapping("/list")
    public String list(){
        return "board/list";
    }

}
