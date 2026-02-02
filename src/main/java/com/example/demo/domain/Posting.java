package com.example.demo.domain;

import com.example.demo.dto.DefaultDto;
import com.example.demo.dto.PostingDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Posting extends AuditingFields {
    @Column(nullable = false) Long userId;
    @Column(nullable = false) String title;
    String content;

    protected  Posting() {}
    private Posting(Boolean deleted, Long userId, String title, String content) {
        this.deleted = deleted;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
    public static Posting of(Long userId, String title, String content) {
        return new Posting(false, userId, title, content);
    }
    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
    public void update(PostingDto.UpdateReqDto param) {
        if(param.getDeleted() != null) { setDeleted(param.getDeleted()); }
        if(param.getTitle() != null) { setTitle(param.getTitle()); }
        if(param.getContent() != null) { setContent(param.getContent()); }
    }
}
