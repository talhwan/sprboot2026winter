package com.example.demo.service;

import com.example.demo.dto.DefaultDto;
import com.example.demo.dto.PostingDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PostingService {
    public DefaultDto.CreateResDto create(PostingDto.CreateReqDto param);
    public void update(PostingDto.UpdateReqDto param);
    public void delete(PostingDto.UpdateReqDto param);
    public PostingDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    public List<PostingDto.DetailResDto> list();
}
