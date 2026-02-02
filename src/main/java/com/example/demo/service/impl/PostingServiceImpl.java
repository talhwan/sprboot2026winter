package com.example.demo.service.impl;

import com.example.demo.domain.Posting;
import com.example.demo.dto.DefaultDto;
import com.example.demo.dto.PostingDto;
import com.example.demo.mapper.PostingMapper;
import com.example.demo.repository.PostingRepository;
import com.example.demo.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostingServiceImpl implements PostingService {

    final PostingRepository postingRepository;
    final PostingMapper postingMapper;

    /**/

    @Override
    public DefaultDto.CreateResDto create(PostingDto.CreateReqDto param) {
        return postingRepository.save(param.toEntity()).toCreateResDto();
    }
    @Override
    public void update(PostingDto.UpdateReqDto param) {
        Posting posting = postingRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        posting.update(param);
        postingRepository.save(posting);
    }

    @Override
    public void delete(PostingDto.UpdateReqDto param) {
        update(PostingDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    public PostingDto.DetailResDto get(Long id) {
        PostingDto.DetailResDto res = postingMapper.detail(id);
        //~~~연산 추가 가능성 있음!?
        return res;
    }

    @Override
    public PostingDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        return get(param.getId());
    }

    public List<PostingDto.DetailResDto> detailList(List<PostingDto.DetailResDto> list) {
        List<PostingDto.DetailResDto> newList = new ArrayList<>();
        for(PostingDto.DetailResDto each : list){
            newList.add(get(each.getId()));
        }
        return newList;
    }

    @Override
    public List<PostingDto.DetailResDto> list() {
        List<PostingDto.DetailResDto> res = postingMapper.list();
        return detailList(res);
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(PostingDto.PagedListReqDto param) {
        DefaultDto.PagedListResDto res = param.init(postingMapper.listCount(param));
        res.setList(detailList(postingMapper.pagedList(param)));
        return res;
    }

    @Override
    public List<PostingDto.DetailResDto> scrolledList(PostingDto.ScrolledListReqDto param) {
        param.init();
        if(param.getCursor() != null && "title".equals(param.getOrderby())){
            Long id = param.getCursor();
            Posting posting = postingRepository.findById(id).orElseThrow(() -> new RuntimeException("no data"));
            param.setCursorsearch(posting.getTitle() + "_" + id);
        }
        List<PostingDto.DetailResDto> list = postingMapper.scrolledList(param);
        return detailList(list);
    }
}
