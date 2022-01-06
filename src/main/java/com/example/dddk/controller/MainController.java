package com.example.dddk.controller;

import com.example.dddk.domain.User;
import com.example.dddk.dto.Userform;
import com.example.dddk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Controller
public class MainController {
    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/mysql")
    public String mysqlTest(){
        return "mysqlTest";
    }

    @PostMapping("/mysqlTesting")
    public String test(Userform userform, Model model){
        System.out.println("userform = " + userform);
        User user = new User(userform.getFirstname(), userform.getLastname());
        userService.save(user);
        model.addAttribute("user",user);
        return "mysqlResultTest";
    }

    @GetMapping("/")
    public String loginTest(){
        return "naverTest";
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "안녕하세요. 현재 서버시간은 " + new Date() + "입니다. \n";
    }

}
