package com.example.demo.service.impl;

import com.example.demo.domain.Posting;
import com.example.demo.dto.DefaultDto;
import com.example.demo.dto.PostingDto;
import com.example.demo.repositody.PostingRepository;
import com.example.demo.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PostingServiceImpl implements PostingService {

    final PostingRepository postingRepository;

    @Override
    public DefaultDto.CreateResDto create(PostingDto.CreateReqDto param) {
        return postingRepository.save(param.toEntity()).toCreateResDto();
    }
    @Override
    public void update(PostingDto.UpdateReqDto param) {
        Long id = param.getId();
        Posting posting = postingRepository.findById(id).orElseThrow(() -> new RuntimeException("no data"));
        posting.update(param);
        postingRepository.save(posting);
    }
    @Override
    public void delete(PostingDto.UpdateReqDto param) {
        // 완전 삭제!
        /*Long id = param.getId();
        Posting posting = postingRepository.findById(id).orElseThrow(() -> new RuntimeException("no data"));
        postingRepository.delete(posting);*/
        // soft delete
        update(PostingDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    public PostingDto.DetailResDto toResDto(Posting posting) {
        PostingDto.DetailResDto res = new PostingDto.DetailResDto();
        res.setId(posting.getId());
        res.setDeleted(posting.getDeleted());
        res.setTitle(posting.getTitle());
        res.setContent(posting.getContent());
        res.setAuthor(posting.getAuthor());
        res.setCreatedAt(posting.getCreatedAt());
        res.setModifiedAt(posting.getModifiedAt());
        return res;
    }

    @Override
    public PostingDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        Posting posting = postingRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        return toResDto(posting);
    }
    @Override
    public List<PostingDto.DetailResDto> list() {
        List<Posting> list = postingRepository.findAll();
        List<PostingDto.DetailResDto> returnList = new ArrayList<>();
        for(Posting each : list) {
            PostingDto.DetailResDto res = toResDto(each);
            returnList.add(res);
        }
        return returnList;
    }
}
