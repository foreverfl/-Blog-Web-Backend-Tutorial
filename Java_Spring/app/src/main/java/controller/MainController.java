package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.CommentDao;
import dao.UserDao;
import jakarta.servlet.http.HttpSession;
import model.Comment;
import model.User;

@Controller
public class MainController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CommentDao commentDao;

    // 예시 페이지
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Hello! Spring");
        return "Hello";
    }

    // 로그인
    @GetMapping("/")
    public String index() {
        return "sign-in";
    }

    @PostMapping("/sign_in_process")
    public String signInProcess(@RequestParam("username") String username, @RequestParam("password") String password,
            HttpSession session,
            Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        User user = userDao.findByUsernameAndPassword(params);
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
        userDao.save(user);
        return "redirect:/";
    }

    @GetMapping("/check_username")
    @ResponseBody
    public boolean checkUsername(@RequestParam("username") String username) {
        User user = userDao.findByUsername(username); // 사용자 이름으로 User 객체 찾기
        System.out.println(user.toString());
        return user != null; // 사용자 이름이 이미 존재하면 true, 그렇지 않으면 false 반환
    }

    // 댓글 페이지
    @GetMapping(value = "/comment")
    public String comment(Model model, HttpSession session) {
        List<Comment> comments = commentDao.findAll();
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
            commentDao.save(comment);
        }
        return "redirect:/comment";
    }

    @GetMapping("/delete_comment/{commentId}")
    public String deleteComment(@PathVariable Long commentId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Comment comment = commentDao.findById(commentId);
        if (user != null && comment != null && user.getId().equals(comment.getUser().getId())) {
            commentDao.delete(commentId);
        }
        return "redirect:/comment";
    }

    @GetMapping("/withdrawal")
    public String withdrawal(HttpSession session) {
        User user = (User) session.getAttribute("user");
        userDao.delete(user);
        return "redirect:/";
    }

    @GetMapping("/sign_out")
    public String signOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
