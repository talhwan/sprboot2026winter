package com.example.demo.controller;

import com.example.demo.dto.DefaultDto;
import com.example.demo.dto.PostingDto;
import com.example.demo.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RequestMapping("/api/posting")
@RestController
public class PostingRestController {

    final PostingService postingService;

    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody PostingDto.CreateReqDto param) {
        return ResponseEntity.ok(postingService.create(param));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PostingDto.UpdateReqDto param) {
        postingService.update(param);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PostingDto.UpdateReqDto param) {
        postingService.delete(param);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<PostingDto.DetailResDto> detail(DefaultDto.DetailReqDto param) {
        return ResponseEntity.ok(postingService.detail(param));
    }
    @GetMapping("/list")
    public ResponseEntity<List<PostingDto.DetailResDto>> list(PostingDto.ListReqDto param) {
        return ResponseEntity.ok(postingService.list(param));
    }
}
