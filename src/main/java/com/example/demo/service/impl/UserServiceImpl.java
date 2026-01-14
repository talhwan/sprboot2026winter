package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.dto.DefaultDto;
import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    @Override
    public DefaultDto.CreateResDto create(UserDto.CreateReqDto param) {
        /*
        User user = param.toEntity();
        user = userRepository.save(user);
        DefaultDto.CreateResDto res = user.toCreateResDto();
        */
        return userRepository.save(param.toEntity()).toCreateResDto();
    }
    @Override
    public void update(UserDto.UpdateReqDto param) {
        User user = userRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        user.update(param);
        userRepository.save(user);
    }

    @Override
    public void delete(UserDto.UpdateReqDto param) {
        update(UserDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    public UserDto.DetailResDto get(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("no data"));
        UserDto.DetailResDto res = UserDto.DetailResDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .birth(user.getBirth())
                .gender(user.getGender())
                .build();
        return res;
    }

    @Override
    public UserDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        return get(param.getId());
    }

    @Override
    public List<UserDto.DetailResDto> list() {
        List<User> users = userRepository.findAll();
        List<UserDto.DetailResDto> res = new ArrayList<>();
        for(User user : users) {
            res.add(get(user.getId()));
        }
        return res;
    }
}
