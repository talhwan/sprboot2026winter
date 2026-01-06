package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity // 디비로 보면, 테이블!!
public class Board {
    @Id private Long id; // pk로 사용할 부분! (중복 불가, 없으면 안됨!)
    String title;
    String content;
    String author;
}
