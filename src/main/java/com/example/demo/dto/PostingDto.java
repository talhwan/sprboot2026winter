package com.example.demo.dto;

import com.example.demo.domain.Posting;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class PostingDto {

    @Getter @Setter
    public static class CreateReqDto {
        String title;
        String content;
        String author;

        public Posting toEntity() {
            return Posting.of(title, content, author);
        }
    }
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto {
        String title;
        String content;
    }
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto {
        String title;
        String content;
        String author;
    }
}
