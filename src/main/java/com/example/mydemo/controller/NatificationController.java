package com.example.mydemo.controller;

import com.example.mydemo.dto.NotificationDTO;
import com.example.mydemo.enums.NotificationTypeEnum;
import com.example.mydemo.model.User;
import com.example.mydemo.service.NotificationService;
import com.example.mydemo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NatificationController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;   //模型放问题和回复者   故而需要提供service

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name="id") Long id
    ) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id, user);
        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
                || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()) {
            return "redirect:/question/" + notificationDTO.getOuterid();
        } else {
            return "redirect:/";
        }
    }
}
