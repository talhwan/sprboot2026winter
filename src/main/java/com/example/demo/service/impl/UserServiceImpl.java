package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.dto.DefaultDto;
import com.example.demo.dto.UserDto;
import com.example.demo.mapper.UserMapper;
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
    final UserMapper userMapper;

    /**/

    @Override
    public DefaultDto.CreateResDto login(UserDto.LoginReqDto param) {
        //로그인 할때 해야 하는것?!
        // username이랑, password 가 저장된 정보와 일치하는지 확인!
        /*
        // 1번 방법
        User user = userRepository.findByUsername(param.getUsername());
        if(user != null){
            if(user.getPassword().equals(param.getPassword())) {
                // 로그인 성공!!
                return DefaultDto.CreateResDto.builder().id(user.getId()).build();
            }
        }
        return DefaultDto.CreateResDto.builder().id((long) -200).build(); // 로그인 실패!

        // 2-1번 방법
        User user = userRepository.findByUsernameAndPassword(param.getUsername(), param.getPassword());
        if (user != null) {
            // 로그인 성공!!
            return DefaultDto.CreateResDto.builder().id(user.getId()).build();
        }
        return DefaultDto.CreateResDto.builder().id((long) -200).build(); // 로그인 실패!

        // 2-2번 방법
        User user = userRepository.findByUsernameAndPassword(param.getUsername(), param.getPassword()).orElseThrow(
                () -> new RuntimeException("username and password not matched")
        );
        return DefaultDto.CreateResDto.builder().id(user.getId()).build(); // 로그인 성공!
         */

        User user = userRepository.findByUsernameAndPassword(param.getUsername(), param.getPassword());
        if (user != null) {
            // 로그인 성공!!
            return DefaultDto.CreateResDto.builder().id(user.getId()).build();
        }
        return DefaultDto.CreateResDto.builder().id((long) -200).build(); // 로그인 실패!
    }

    @Override
    public DefaultDto.CreateResDto create(UserDto.CreateReqDto param) {

        //username 중복인지 확인하는 코드
        User user = userRepository.findByUsername(param.getUsername());
        if(user != null){
            /*
            // 1번 방법
            throw new RuntimeException("username already exists");
             */
            // 2번 방법
            return DefaultDto.CreateResDto.builder().id((long) -100).build(); //-100 : 아이디 이미 사용했을 경우!
        }

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
        UserDto.DetailResDto res = userMapper.detail(id);
        //~~~연산 추가 가능성 있음!?
        return res;
    }

    @Override
    public UserDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        return get(param.getId());
    }

    public List<UserDto.DetailResDto> detailList(List<UserDto.DetailResDto> list) {
        List<UserDto.DetailResDto> newList = new ArrayList<>();
        for(UserDto.DetailResDto each : list){
            newList.add(get(each.getId()));
        }
        return newList;
    }

    @Override
    public List<UserDto.DetailResDto> list() {
        List<UserDto.DetailResDto> res = userMapper.list();
        return detailList(res);
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(UserDto.PagedListReqDto param) {
        DefaultDto.PagedListResDto res = param.init(userMapper.listCount(param));
        res.setList(detailList(userMapper.pagedList(param)));
        return res;
    }

    @Override
    public List<UserDto.DetailResDto> scrolledList(UserDto.ScrolledListReqDto param) {
        param.init();
        if(param.getCursor() != null && "name".equals(param.getOrderby())){
            Long id = param.getCursor();
            User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("no data"));
            param.setCursorsearch(user.getName() + "_" + id);
        }
        List<UserDto.DetailResDto> list = userMapper.scrolledList(param);
        return detailList(list);
    }
}
