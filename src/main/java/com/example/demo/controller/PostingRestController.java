package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/posting")
@RestController
public class PostingRestController {

    @GetMapping("/create")
    public Map<String, Object> create(@RequestParam Map<String, Object> map) {

        System.out.println("map : " + map);

        Map<String, Object> map_result = new HashMap<>();
        map_result.put("status", 200);

        return map_result;
    }
}
