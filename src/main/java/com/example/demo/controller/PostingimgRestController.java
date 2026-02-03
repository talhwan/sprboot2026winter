package com.example.demo.controller;

import com.example.demo.dto.DefaultDto;
import com.example.demo.dto.PostingimgDto;
import com.example.demo.service.PostingimgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/postingimg")
@RestController
public class PostingimgRestController {

    final PostingimgService postingimgService;

    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody PostingimgDto.CreateReqDto param) {
        return ResponseEntity.ok(postingimgService.create(param));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PostingimgDto.UpdateReqDto param) {
        postingimgService.update(param);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PostingimgDto.UpdateReqDto param) {
        postingimgService.delete(param);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<PostingimgDto.DetailResDto> detail(DefaultDto.DetailReqDto param) {
        return ResponseEntity.ok(postingimgService.detail(param));
    }
    @GetMapping("/list")
    public ResponseEntity<List<PostingimgDto.DetailResDto>> list(PostingimgDto.ListReqDto param) {
        return ResponseEntity.ok(postingimgService.list(param));
    }
}
