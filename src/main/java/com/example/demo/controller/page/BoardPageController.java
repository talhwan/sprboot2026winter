package com.example.demo.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/board")
@Controller
public class BoardPageController {
    @GetMapping(value = "/{page}")
    public String test(@PathVariable String page){
        return "board/" + page;
    }
    @GetMapping("/{page}/{id}")
    public String test2(@PathVariable String page, @PathVariable String id){
        return "board/" + page;
    }
    /*
    @RequestMapping("/create")
    public String create(){
        return "board/create";
    }
    @RequestMapping("/list")
    public String list(){
        return "board/list";
    }
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable String id){
        return "board/detail";
    }
    */
}
