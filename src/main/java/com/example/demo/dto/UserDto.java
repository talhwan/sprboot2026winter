package com.example.demo.dto;

import com.example.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

public class UserDto {

    @Getter @Setter
    public static class LoginReqDto {
        String username;
        String password;
    }

    /**/

    @Getter
    @Setter
    public static class CreateReqDto {
        String username;
        String password;
        String name;
        String nickname;
        String phone;
        String birth;
        Integer gender;

        public User toEntity() {
            return User.of(username, password, name, nickname, phone, birth, gender);
        }
    }
    @Getter @Setter @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto {
        String password;
        String name;
        String nickname;
        String phone;
    }
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto {
        String username;
        String name;
        String nickname;
        String phone;
        String birth;
        Integer gender;
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class PagedListReqDto {
        Integer callpage; //호출할 페이지
        Integer perpage; // 한번에 볼 페이지 수
        Integer offset; // 실제 조회할 글 순번
        String orderby; //정렬기준
        String orderway; //정렬방향

        String username;
        String name;
    }
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class PagedListResDto {
        Integer callpage; //호출한 페이지
        Integer perpage; // 한번에 본 페이지 수
        Integer totalpage; // 총 페이지 갯수
        Integer totalcount; // 총 글 갯수
        List<UserDto.DetailResDto> list; // 실제 글 목록
    }


    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ScrolledListReqDto {
        Integer perpage; // 한번에 볼 페이지 수
        String orderby; //정렬기준
        String orderway; //정렬방향
        Long cursor;
        String cursorsearch;

        String username;
        String name;
    }
}
