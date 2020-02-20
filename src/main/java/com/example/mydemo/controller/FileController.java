package com.example.mydemo.controller;

import com.example.mydemo.dto.FileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class FileController {
    @GetMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(){
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);//Markdown  success为1时代表图片上传成功
        fileDTO.setUrl("/image/bagua.jpg");
        return fileDTO;
    }
}
