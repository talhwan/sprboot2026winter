package com.example.demo.controller;

import com.example.demo.ParamTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/default")
@RestController
public class DefaultRestController {

    @PostMapping("/file")
    public ResponseEntity<String> file(MultipartFile file) {
        System.out.println("filename : " + file.getOriginalFilename());
        String path = "/Users/a01/workspace/uploadfile/demo/";
        String fileName = file.getOriginalFilename();
        Date date = new Date();
        String tempTime = date.getTime() + "";
        String finalFileName = null;

        try{
            finalFileName = tempTime + "_" + fileName;
            FileCopyUtils.copy(file.getBytes(), new File(path + finalFileName));
        } catch(Exception e) {}

        return ResponseEntity.ok(finalFileName);
    }
}
