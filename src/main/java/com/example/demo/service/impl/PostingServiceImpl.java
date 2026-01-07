package com.example.demo.service.impl;

import com.example.demo.domain.Posting;
import com.example.demo.repositody.PostingRepository;
import com.example.demo.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostingServiceImpl implements PostingService {

    final PostingRepository postingRepository;

    @Override
    public Map<String, Object> create(Map<String, Object> map) {

        Posting posting = new Posting();
        //Long id = Long.parseLong(map.get("id").toString());
        String title = map.get("title") + "";
        String content = map.get("content") + "";
        String author = map.get("author") + "";
        //posting.setId(id);
        posting.setTitle(title);
        posting.setContent(content);
        posting.setAuthor(author);
        posting = postingRepository.save(posting); // 저장한 결과 리턴을 엔티티로 돌려줌!

        Map<String, Object> result = new HashMap<>();
        result.put("resultCode", 200);
        result.put("id", posting.getId());

        return result;
    }
    @Override
    public void update(Map<String, Object> map) {
        Long id = Long.parseLong(map.get("id") + "");
        Posting posting = postingRepository.findById(id).orElseThrow(() -> new RuntimeException("no data"));

        if(map.get("title") != null) { posting.setTitle(map.get("title") + ""); }
        if(map.get("content") != null) { posting.setContent(map.get("content") + ""); }
        postingRepository.save(posting);
    }
    @Override
    public void delete(Long id) {
        Posting posting = postingRepository.findById(id).orElseThrow(() -> new RuntimeException("no data"));
        postingRepository.delete(posting);
    }
    @Override
    public Map<String, Object> detail(Long id) {
        //Optional<Posting> posting = postingRepository.findById(id); // 0 , 1
        Posting posting = postingRepository.findById(id).orElseThrow(() -> new RuntimeException("no data"));
        Map<String, Object> result = new HashMap<>();
        result.put("resultCode", 200);
        result.put("data", posting);
        return result;
    }
    @Override
    public Map<String, Object> list() {
        List<Posting> list = postingRepository.findAll();
        Map<String, Object> result = new HashMap<>();
        result.put("resultCode", 200);
        result.put("data", list);
        return result;
    }
}
