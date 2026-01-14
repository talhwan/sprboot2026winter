package com.example.demo.domain;

import com.example.demo.dto.DefaultDto;
import com.example.demo.dto.UserDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends AuditingFields {
    String username; // 일반적인 아이디라고 보시면 됩니다!
    String password; // 비밀번호!

    String name;
    String nickname;
    String phone;
    String birth;
    Integer gender;// 10은 여성, 20은 남성

    protected User() {}
    private User(Boolean deleted, String username, String password, String name, String nickname, String phone, String birth, Integer gender) {
        this.deleted = deleted;
        this.username = username;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.birth = birth;
        this.gender = gender;
    }

    public static User of(String username, String password, String name, String nickname, String phone, String birth, Integer gender){
        return new User(false ,username, password, name, nickname, phone, birth, gender);
    }

    public DefaultDto.CreateResDto toCreateResDto(){
        /*DefaultDto.CreateResDto res = new DefaultDto.CreateResDto();
        res.setId(id);*/
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
    public void update(UserDto.UpdateReqDto param){
        if(param.getDeleted() != null){ setDeleted(param.getDeleted()); }
        if(param.getPassword() != null) { setPassword(param.getPassword()); }
        if(param.getName() != null) { setName(param.getName()); }
        if(param.getNickname() != null) { setNickname(param.getNickname()); }
        if(param.getPhone() != null) { setPhone(param.getPhone()); }
    }
}
