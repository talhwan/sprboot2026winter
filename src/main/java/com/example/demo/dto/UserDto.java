package com.example.demo.dto;

import com.example.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
}
