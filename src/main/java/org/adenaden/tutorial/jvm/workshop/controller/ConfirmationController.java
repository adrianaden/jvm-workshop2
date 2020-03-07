package org.adenaden.tutorial.jvm.workshop.controller;

import org.adenaden.tutorial.jvm.workshop.entity.Account;
import org.adenaden.tutorial.jvm.workshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmationController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/confirmation")
	public ModelAndView confirmation(@RequestParam("email") String email){
		Account account = accountService.findByEmail(email);
		
		ModelMap model =  new ModelMap();
		model.addAttribute("user", account);
		
		return new ModelAndView("confirmation", model);
	}
}
