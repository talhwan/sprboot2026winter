package com.example.demo.controller;

import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/api/board")
@RestController
public class BoardRestController {

    // @Autowired // 이렇게도 가능하지만, 우리는 생성자 방식만 사용할 예정!
    final BoardService boardService;
    /*
    //생성자 방식으로 보드 서비스 주입!
    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }
     */

    @GetMapping("/create")
    public Map<String, Object> create(@RequestParam Map<String, Object> params){
        return boardService.create(params);
    }
    @GetMapping("/update")
    public void update(@RequestParam Map<String, Object> params){
        boardService.update(params);
    }
    @GetMapping("/delete")
    public void delete(@RequestParam int id){
        boardService.delete(id);
    }
    @GetMapping("/detail/{id}")
    public Map<String, Object> detail(@PathVariable int id){
        return boardService.detail(id);
    }
    @GetMapping("/list")
    public Map<String, Object> list(@RequestParam Map<String, Object> params){
        return boardService.list();
    }
}
