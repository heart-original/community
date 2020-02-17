package com.example.mydemo.controller;


import com.example.mydemo.dto.PaginationDTO;
import com.example.mydemo.mapper.UserMapper;
import com.example.mydemo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "5") Integer size,
                        @RequestParam(name="search",required = false) String seacrh){
        PaginationDTO pagination=questionService.list(seacrh,page,size);
        model.addAttribute("pagination",pagination);
        model.addAttribute("search",seacrh);
        return "index";
    }
}
