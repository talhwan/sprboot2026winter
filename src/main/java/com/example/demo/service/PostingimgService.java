package com.example.demo.service;

import com.example.demo.dto.DefaultDto;
import com.example.demo.dto.PostingimgDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostingimgService {
    /**/
    DefaultDto.CreateResDto create(PostingimgDto.CreateReqDto param);
    void update(PostingimgDto.UpdateReqDto param);
    void delete(PostingimgDto.UpdateReqDto param);
    PostingimgDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<PostingimgDto.DetailResDto> list(PostingimgDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(PostingimgDto.PagedListReqDto param);
    List<PostingimgDto.DetailResDto> scrolledList(PostingimgDto.ScrolledListReqDto param);

}
