package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/board")
@RestController
public class BoardRestController {

    //임시로 저장할 게시판 정보
    List<Map<String, Object>> list = new ArrayList<>();
    int sequence = 0;

    @RequestMapping("/create")
    public Map<String, Object> create(@RequestParam Map<String, Object> params){

        String title = (String) params.get("title");
        String content = (String) params.get("content");
        String author = (String) params.get("author");

        //아이디값 줘볼까?
        params.put("id", ++sequence);
        LocalDateTime now = LocalDateTime.now();
        params.put("createdAt", now.toString());


        list.add(params);
        //System.out.println("title: " + title);
        System.out.println("list : " + list.toString());

        Map<String, Object> map_result = new HashMap<>();
        map_result.put("result_code", 200);

        return map_result;
    }
    @RequestMapping("/update")
    public Map<String, Object> update(@RequestParam Map<String, Object> params){

        String id = params.get("id") + "";

        Map<String, Object> map_board = null;
        for(Map<String, Object> each : list){
            String tempId = each.get("id") + "";
            if(tempId.equals(id)){
                map_board = each;
            }
        }
        if(map_board != null){
            String title = (String) params.get("title");
            if(title != null){
                map_board.put("title", title);
            }
            String content = (String) params.get("content");
            if(content != null){
                map_board.put("content", content);
            }
        }

        Map<String, Object> map_result = new HashMap<>();
        map_result.put("result_code", 200);

        return map_result;
    }

    @RequestMapping("/detail/{id}")
    public Map<String, Object> detail(@PathVariable String id){

        //String id = params.get("id") + "";
        System.out.println("id : " + id);

        Map<String, Object> map_board = null;
        for(Map<String, Object> each : list){
            String tempId = each.get("id") + "";
            if(tempId.equals(id)){
                map_board = each;
            }
        }

        Map<String, Object> map_result = new HashMap<>();
        map_result.put("result_code", 200);
        map_result.put("data", map_board);

        return map_result;
    }
    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam Map<String, Object> params){

        Map<String, Object> map_result = new HashMap<>();
        map_result.put("result_code", 200);
        map_result.put("data", list);

        return map_result;
    }
}
