package com.example.demo.controller;

import com.example.demo.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RequestMapping("/api/posting")
@RestController
public class PostingRestController {

    final PostingService postingService;

    @PostMapping("")
    public Map<String, Object> create(@RequestBody Map<String, Object> map) {
        return postingService.create(map);
    }
    @PutMapping("")
    public void update(@RequestBody Map<String, Object> map) {
        postingService.update(map);
    }
    @DeleteMapping("")
    public void delete(@RequestBody Long id) {
        postingService.delete(id);
    }

    @GetMapping("")
    public Map<String, Object> detail(@RequestParam Long id) {
        return postingService.detail(id);
    }
    @GetMapping("/list")
    public Map<String, Object> list() {
        return postingService.list();
    }
}
