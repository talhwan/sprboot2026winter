package com.example.demo.domain;

import com.example.demo.dto.DefaultDto;
import com.example.demo.dto.PostingimgDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Postingimg extends AuditingFields {
    @Column(nullable = false) Long postingId;
    @Column(nullable = false) String img;

    protected Postingimg() {}
    private Postingimg(Boolean deleted, Long postingId, String img) {
        this.deleted = deleted;
        this.postingId = postingId;
        this.img = img;
    }
    public static Postingimg of(Long postingId, String img) {
        return new Postingimg(false, postingId, img);
    }
    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
    public void update(PostingimgDto.UpdateReqDto param) {
        if(param.getDeleted() != null) { setDeleted(param.getDeleted()); }
        if(param.getImg() != null) { setImg(param.getImg()); }
    }
}
