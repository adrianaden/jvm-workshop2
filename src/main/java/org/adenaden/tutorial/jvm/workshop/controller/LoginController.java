package org.adenaden.tutorial.jvm.workshop.controller;

import org.adenaden.tutorial.jvm.workshop.entity.Member;
import org.adenaden.tutorial.jvm.workshop.model.LoginDTO;
import org.adenaden.tutorial.jvm.workshop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@Autowired
	private MemberService accountService;
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelMap model = new ModelMap();
		model.addAttribute("login", new LoginDTO());
		
		return new ModelAndView("login", model);
	}
	
	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute LoginDTO login) {
		Member member = accountService.findByEmail(login.getEmail());
		
		ModelMap model =  new ModelMap();
		model.addAttribute("email", member.getEmail());
		
		return new ModelAndView("redirect:/course/list", model);
	}
}
