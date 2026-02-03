package com.example.demo.mapper;

import com.example.demo.dto.PostingDto;

import java.util.List;

public interface PostingMapper {
    PostingDto.DetailResDto detail(Long id);
    List<PostingDto.DetailResDto> list(PostingDto.ListReqDto param);
    int listCount(PostingDto.PagedListReqDto param);
    List<PostingDto.DetailResDto> pagedList(PostingDto.PagedListReqDto param);
    List<PostingDto.DetailResDto> scrolledList(PostingDto.ScrolledListReqDto param);
}