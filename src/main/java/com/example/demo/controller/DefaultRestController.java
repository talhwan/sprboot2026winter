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
import java.util.*;

@RequestMapping("/api/default")
@RestController
public class DefaultRestController {

    public String fileUpload(MultipartFile file){
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

        return finalFileName;
    }

    @PostMapping("/file")
    public ResponseEntity<String> file(MultipartFile file) {
        return ResponseEntity.ok(fileUpload(file));
    }
    @PostMapping("/files")
    public ResponseEntity<List<String>> files(List<MultipartFile> files) {
        List<String> fileNames = new ArrayList<>();
        for(MultipartFile file : files){
            String filename = fileUpload(file);
            fileNames.add(filename);
        }
        return ResponseEntity.ok(fileNames);
    }
}
