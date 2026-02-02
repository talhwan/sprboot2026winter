package com.example.demo.dto;

import com.example.demo.domain.Posting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

public class PostingDto {

    /**/

    @Getter
    @Setter
    public static class CreateReqDto {
        Long userId;
        String title;
        String content;

        public Posting toEntity() {
            return Posting.of(userId, title, content);
        }
    }
    @Getter @Setter @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto {
        String title;
        String content;
    }
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto {
        Long userId;
        String title;
        String content;

        String userUsername;
        String userName;
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        Long userId;
        String title;
    }
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ScrolledListReqDto extends DefaultDto.ScrolledListReqDto {
        Long userId;
        String title;
    }
}
