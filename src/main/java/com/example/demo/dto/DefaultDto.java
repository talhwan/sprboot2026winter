package com.example.demo.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class DefaultDto {

    @Getter @Setter @Builder
    public static class CreateResDto {
        Long id;
    }
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto {
        Long id;
        Boolean deleted;
    }
    @Getter @Setter
    public static class DetailReqDto {
        Long id;
    }
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        Long id;
        Boolean deleted;
        LocalDateTime createdAt;
        LocalDateTime modifiedAt;
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto {
        String orderby; //정렬기준
        String orderway; //정렬방향

        Boolean deleted;

        public void init(){
            if(getOrderby() == null || getOrderby().isEmpty()){
                setOrderby("id");
            }
            if(getOrderway() == null || getOrderway().isEmpty()){
                setOrderway("DESC");
            }
        }
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class PagedListReqDto {
        Integer callpage; //호출할 페이지
        Integer perpage; // 한번에 볼 페이지 수
        Integer offset; // 실제 조회할 글 순번
        String orderby; //정렬기준
        String orderway; //정렬방향

        Boolean deleted;

        public DefaultDto.PagedListResDto init(int totalcount){
            Integer perpage = getPerpage();
            if(perpage == null || perpage <= 0){
                perpage = 10;
            }
            int totalpage = totalcount / perpage;
            if(totalcount % perpage > 0) {totalpage++;}

            Integer callpage = getCallpage();
            if(callpage == null || callpage <= 0){
                callpage = 1;
            }
            if(callpage > totalpage){
                callpage = totalpage;
            }

            int offset = (callpage - 1) * perpage;
            setPerpage(perpage);
            setOffset(offset);

            if(getOrderby() == null || getOrderby().isEmpty()){
                setOrderby("id");
            }
            if(getOrderway() == null || getOrderway().isEmpty()){
                setOrderway("DESC");
            }

            return DefaultDto.PagedListResDto.builder()
                    .callpage(callpage)
                    .perpage(perpage)
                    .totalpage(totalpage)
                    .totalcount(totalcount)
                    .build();
        }
    }
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class PagedListResDto {
        Integer callpage; //호출한 페이지
        Integer perpage; // 한번에 본 페이지 수
        Integer totalpage; // 총 페이지 갯수
        Integer totalcount; // 총 글 갯수
        Object list; // 실제 글 목록
    }
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ScrolledListReqDto {
        Integer perpage; // 한번에 볼 페이지 수
        String orderby; //정렬기준
        String orderway; //정렬방향
        Long cursor;
        String cursorsearch;

        Boolean deleted;

        public void init(){
            Integer perpage = getPerpage();
            if(perpage == null || perpage <= 0){
                perpage = 10;
            }
            setPerpage(perpage);
            if(getOrderby() == null || getOrderby().isEmpty()){
                setOrderby("id");
            }
            if(getOrderway() == null || getOrderway().isEmpty()){
                setOrderway("DESC");
            }
        }
    }
}
