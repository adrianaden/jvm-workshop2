package org.adenaden.tutorial.jvm.workshop.controller;

import org.adenaden.tutorial.jvm.workshop.entity.Account;
import org.adenaden.tutorial.jvm.workshop.model.Registration;
import org.adenaden.tutorial.jvm.workshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/registration")
	public ModelAndView registration(){
		ModelMap model =  new ModelMap();
		model.addAttribute("registration", new Registration());
		
		return new ModelAndView("registration", model);
	}
	
	@PostMapping("/registration")
	public ModelAndView registration(@ModelAttribute Registration registration) {
		Account account = accountService.create(registration);
		
		ModelMap model =  new ModelMap();
		model.addAttribute("email", account.getEmail());
		
		return new ModelAndView("redirect:/confirmation", model);
	}
	@GetMapping("registration/verified")
	public void emailTerverifikasi() {
	
	}
	
}
