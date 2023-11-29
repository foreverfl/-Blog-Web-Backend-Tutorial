package com.example.springboot_example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springboot_example.model.Comment;
import com.example.springboot_example.model.User;
import com.example.springboot_example.repository.CommentRepository;
import com.example.springboot_example.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    // 예시 페이지
    @GetMapping("/hello")
    @ResponseBody // 메소드의 리턴 값을 HTTP 응답 본문으로 사용
    public String hello() {
        return "Hello, Spring Boot!";
    }

    // 로그인
    @GetMapping("/")
    public String index() {
        return "sign-in";
    }

    @PostMapping("/sign_in_process")
    public String signInProcess(@RequestParam String username, @RequestParam String password, HttpSession session,
            Model model) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/comment";
        } else {
            model.addAttribute("loginError", "아이디 또는 패스워드가 일치하지 않습니다");
            return "sign-in";
        }

    }

    // 회원가입
    @GetMapping(value = "/sign_up")
    public String signUp() {
        return "sign-up";
    }

    @PostMapping("/sign_up_process")
    public String signUpProcess(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/check_username")
    @ResponseBody
    public boolean checkUsername(@RequestParam String username) {
        User user = userRepository.findByUsername(username); // 사용자 이름으로 User 객체 찾기
        return user != null; // 사용자 이름이 이미 존재하면 true, 그렇지 않으면 false 반환
    }

    // 댓글 페이지
    @GetMapping(value = "/comment")
    public String comment(Model model, HttpSession session) {
        List<Comment> comments = commentRepository.findAll();
        model.addAttribute("comments", comments);
        return "comment";
    }

    @PostMapping("/add_comment")
    public String addComment(@RequestParam String text, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Comment comment = new Comment();
            comment.setUser(user);
            comment.setText(text);
            commentRepository.save(comment);
        }
        return "redirect:/comment";
    }

    @GetMapping("/delete_comment/{commentId}")
    public String deleteComment(@PathVariable Long commentId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (user != null && comment != null && user.getId().equals(comment.getUser().getId())) {
            commentRepository.delete(comment);
        }
        return "redirect:/comment";
    }

    @GetMapping("/withdrawal")
    public String withdrawal(HttpSession session) {
        User user = (User) session.getAttribute("user");
        userRepository.delete(user);

        return "redirect:/";
    }

    @GetMapping("/sign_out")
    public String signOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
