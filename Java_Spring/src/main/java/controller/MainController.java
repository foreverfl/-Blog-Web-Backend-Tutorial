package main.java.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("/hello")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", "Hello, Spring!");
		mav.setViewName("hello");
		return mav;
	}
}
