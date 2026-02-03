package com.example.demo.service.impl;

import com.example.demo.domain.Postingimg;
import com.example.demo.dto.DefaultDto;
import com.example.demo.dto.PostingimgDto;
import com.example.demo.mapper.PostingimgMapper;
import com.example.demo.repository.PostingimgRepository;
import com.example.demo.service.PostingimgService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostingimgServiceImpl implements PostingimgService {

    final PostingimgRepository postingimgRepository;
    final PostingimgMapper postingimgMapper;

    /**/

    @Override
    public DefaultDto.CreateResDto create(PostingimgDto.CreateReqDto param) {
        return postingimgRepository.save(param.toEntity()).toCreateResDto();
    }
    @Override
    public void update(PostingimgDto.UpdateReqDto param) {
        Postingimg postingimg = postingimgRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        postingimg.update(param);
        postingimgRepository.save(postingimg);
    }

    @Override
    public void delete(PostingimgDto.UpdateReqDto param) {
        update(PostingimgDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    public PostingimgDto.DetailResDto get(Long id) {
        PostingimgDto.DetailResDto res = postingimgMapper.detail(id);
        //~~~연산 추가 가능성 있음!?
        return res;
    }

    @Override
    public PostingimgDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        return get(param.getId());
    }

    public List<PostingimgDto.DetailResDto> detailList(List<PostingimgDto.DetailResDto> list) {
        List<PostingimgDto.DetailResDto> newList = new ArrayList<>();
        for(PostingimgDto.DetailResDto each : list){
            newList.add(get(each.getId()));
        }
        return newList;
    }

    @Override
    public List<PostingimgDto.DetailResDto> list(PostingimgDto.ListReqDto param) {
        param.init();
        List<PostingimgDto.DetailResDto> res = postingimgMapper.list(param);
        return detailList(res);
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(PostingimgDto.PagedListReqDto param) {
        DefaultDto.PagedListResDto res = param.init(postingimgMapper.listCount(param));
        res.setList(detailList(postingimgMapper.pagedList(param)));
        return res;
    }

    @Override
    public List<PostingimgDto.DetailResDto> scrolledList(PostingimgDto.ScrolledListReqDto param) {
        param.init();
        List<PostingimgDto.DetailResDto> list = postingimgMapper.scrolledList(param);
        return detailList(list);
    }
}
