package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import java.util.List;

public interface UserMapper {
    //조회 하는데 사용될 다오!
    UserDto.DetailResDto detail(Long id);
    List<UserDto.DetailResDto> list();
}