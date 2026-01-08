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
    String title;
    String content;
    String author;

    //외부에서 사용 못하게 하려고!
    protected  Posting() {}
    //외부에서 사용 불가
    private Posting(Boolean deleted, String title, String content, String author) {
        this.deleted = deleted;
        this.title = title;
        this.content = content;
        this.author = author;
    }
    // 이 메서드를 통해서만, 엔티티의 인스턴스 만들겁니다!
    public static Posting of(String title, String content, String author) {
        return new Posting(false, title, content, author);
    }
    // create하고 난 후, 해야 할 일
    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
    public void update(PostingDto.UpdateReqDto param) {
        if(param.getDeleted() != null) { setDeleted(param.getDeleted()); }
        if(param.getTitle() != null) { setTitle(param.getTitle()); }
        if(param.getContent() != null) { setContent(param.getContent()); }
    }
}
