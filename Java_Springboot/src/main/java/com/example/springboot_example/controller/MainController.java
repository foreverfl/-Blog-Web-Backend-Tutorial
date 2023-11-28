package com.example.springboot_example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springboot_example.model.User;
import com.example.springboot_example.repository.UserRepository;
import com.example.springboot_example.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    @ResponseBody // 메소드의 리턴 값을 HTTP 응답 본문으로 사용
    public String hello() {
        return "Hello, Spring Boot!";
    }

    @GetMapping(value = "/sign_up")
    public String signUp() {
        return "sign-up";
    }

    @PostMapping("/signUpProcess")
    public String signUpProcess(@RequestParam String username, @RequestParam String name,
            @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setPassword(password);
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/sign_in")
    public String signIn() {
        return "sign-in";
    }

    @PostMapping("/sign_in_process")
    public String signInProcess(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            return "redirect:/sign_in";
        }

    }

    @GetMapping("/sign_out")
    public String signOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
