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
        /*
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("no data"));
        return UserDto.DetailResDto.builder()
                .id(user.getId())
                .deleted(user.getDeleted())
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())

                .username(user.getUsername())
                .name(user.getName())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .birth(user.getBirth())
                .gender(user.getGender())
                .build();
        */

        UserDto.DetailResDto res = userMapper.detail(id);
        return res;
    }

    @Override
    public UserDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        return get(param.getId());
    }

    @Override
    public List<UserDto.DetailResDto> list() {
        /*List<User> users = userRepository.findAll();
        List<UserDto.DetailResDto> res = new ArrayList<>();
        for(User user : users) {
            res.add(get(user.getId()));
        }*/

        List<UserDto.DetailResDto> res = userMapper.list();
        return res;
    }

    @Override
    public UserDto.PagedListResDto pagedList(UserDto.PagedListReqDto param) {
        int totalcount = userMapper.listCount();
        Integer perpage = param.getPerpage();
        if(perpage == null || perpage <= 0){
            perpage = 10;
        }

        int totalpage = totalcount / perpage;
        if(totalcount % perpage > 0) {totalpage++;}

        Integer callpage = param.getCallpage();
        if(callpage == null || callpage <= 0){
            callpage = 1;
        }
        if(callpage > totalpage){
            callpage = totalpage;
        }

        int offset = (callpage - 1) * perpage;
        param.setPerpage(perpage);
        param.setOffset(offset);

        if(param.getOrderby() == null || param.getOrderby().isEmpty()){
            param.setOrderby("id");
        }
        if(param.getOrderway() == null || param.getOrderway().isEmpty()){
            param.setOrderway("DESC");
        }
        System.out.println(param.getOrderby());
        System.out.println(param.getOrderway());

        List<UserDto.DetailResDto> list = userMapper.pagedList(param);

        return UserDto.PagedListResDto.builder()
                .callpage(callpage)
                .perpage(perpage)
                .totalpage(totalpage)
                .totalcount(totalcount)
                .list(list)
                .build();
    }
}
