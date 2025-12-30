package com.example.demo.controller;

import com.example.demo.ParamTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class DefaultRestController {

    @RequestMapping("/test")
    public Map<String, Object> test(@RequestParam Map<String, Object> params) {
        Map<String, Object> map = new HashMap<>();
        map.put("test1", params.get("a"));
        map.put("test2", 112233);
        return map;
    }

    @RequestMapping("/test2")
    public Map<String, Object> test2(ParamTest params) {
        Map<String, Object> map = new HashMap<>();
        map.put("param1", params.getParam1());
        map.put("param2", params.getParam2());
        return map;
    }

}
