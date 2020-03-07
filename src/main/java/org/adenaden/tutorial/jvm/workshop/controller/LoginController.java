package org.adenaden.tutorial.jvm.workshop.controller;

import org.adenaden.tutorial.jvm.workshop.entity.Account;
import org.adenaden.tutorial.jvm.workshop.model.Login;
import org.adenaden.tutorial.jvm.workshop.service.AccountService;
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
	private AccountService accountService;
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelMap model = new ModelMap();
		model.addAttribute("login", new Login());
		
		return new ModelAndView("login", model);
	}
	
	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute Login login) {
		Account account = accountService.findByEmailAndPassword(login.getEmail(), login.getPassword());
		
		ModelMap model =  new ModelMap();
		model.addAttribute("email", account.getEmail());
		
		return new ModelAndView("redirect:/course-list", model);
	}
}
