package com.example.demo.service;

import com.example.demo.dto.DefaultDto;
import com.example.demo.dto.PostingDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PostingService {
    DefaultDto.CreateResDto create(PostingDto.CreateReqDto param);
    void update(PostingDto.UpdateReqDto param);
    void delete(PostingDto.UpdateReqDto param);
    PostingDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<PostingDto.DetailResDto> list();
}
