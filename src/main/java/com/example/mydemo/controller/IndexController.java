package com.example.mydemo.controller;


import com.example.mydemo.cache.HotTagCache;
import com.example.mydemo.dto.PaginationDTO;
import com.example.mydemo.mapper.UserMapper;
import com.example.mydemo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private HotTagCache hotTagCache;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "5") Integer size,
                        @RequestParam(name="search",required = false) String seacrh,
                        @RequestParam(name="tag",required = false) String tag){
        PaginationDTO pagination=questionService.list(seacrh,tag,page,size);
        List<String> tags = hotTagCache.getHots();
        Map<String,Integer> nums=hotTagCache.getNums();
        model.addAttribute("pagination",pagination);
        model.addAttribute("search",seacrh);
        model.addAttribute("tags",tags);
        model.addAttribute("tag",tag);
        model.addAttribute("nums",nums);
        return "index";
    }
}
