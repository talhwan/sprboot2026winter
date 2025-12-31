package com.example.demo.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SampleController {

    @ResponseBody
    @RequestMapping("/test")
    public Map<String, Object> test(){

        //변수 선언
        int int_a0 = 0;//정수
        double double_a0 = 0.0;//실수
        String str_a0 = "";//문자열
        boolean bool_a0 = false; //논리형

        //배열
        //반복문
        int[] arr_a0 = new int[10];
        int[] arr_a1 = {0,1,2,3,4,5,6,7,8,9};
        int sum_a0 = 0;
        for(int i=0;i<arr_a0.length;i++){
            int each = arr_a0[i];
            sum_a0 += each;
        }
        for(int each : arr_a1){
            sum_a0 += each;
        }
        System.out.println("sum_a0="+sum_a0);

        //조건문
        if(sum_a0==0){

        } else if(sum_a0 > 0){

        } else {

        }

        // 자료구조
        // Map
        Map<String, Object> a_map = new HashMap<>();
        a_map.put("a", sum_a0);
        System.out.println("1a ? ="+a_map.get("a"));

        Object aaa = a_map.get("a");
        int aaa1 = (int) a_map.get("a");
        int aaa2 = Integer.parseInt(aaa + "");

        a_map.remove("a");
        a_map.remove("b");

        System.out.println("2 a ? ="+a_map.get("a"));
        System.out.println("2-1 a ? ="+a_map.get("b"));


        // List
        List<String> aaaList = new ArrayList<>();
        aaaList.add("11");
        aaaList.add("22");
        aaaList.add("33");
        aaaList.remove(1);
        for(int i=0;i<aaaList.size();i++){
            String each = aaaList.get(i);
            System.out.println(each);
        }
        for(String each : aaaList){
            System.out.println(each);
        }
        return a_map;
    }

    /* 과제1

    1) 스프링 전체 구조 복습

    2) 구구단 출력
    //assignment1 이라는 메서드를 만들고, url은 /assignment 로 지정
    //구구단을 1단부터 9단까지 출력하고, 리턴값은 '완료' 라는 한글을 담아주세요!

     */

}
