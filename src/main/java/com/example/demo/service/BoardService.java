package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface BoardService {
    Map<String, Object> create(Map<String, Object> params);
    void update(Map<String, Object> params);
    void delete(int id);
    Map<String, Object> detail(int id);
    Map<String, Object> list();
}
