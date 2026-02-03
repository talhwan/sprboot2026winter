package com.example.demo.mapper;

import com.example.demo.dto.PostingimgDto;

import java.util.List;

public interface PostingimgMapper {
    PostingimgDto.DetailResDto detail(Long id);
    List<PostingimgDto.DetailResDto> list(PostingimgDto.ListReqDto param);
    int listCount(PostingimgDto.PagedListReqDto param);
    List<PostingimgDto.DetailResDto> pagedList(PostingimgDto.PagedListReqDto param);
    List<PostingimgDto.DetailResDto> scrolledList(PostingimgDto.ScrolledListReqDto param);
}