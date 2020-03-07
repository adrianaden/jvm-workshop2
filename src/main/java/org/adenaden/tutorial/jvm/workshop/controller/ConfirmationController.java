package org.adenaden.tutorial.jvm.workshop.controller;

import org.adenaden.tutorial.jvm.workshop.entity.Member;
import org.adenaden.tutorial.jvm.workshop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmationController {
	
	@Autowired
	private MemberService accountService;
	
	@GetMapping("/confirmation")
	public ModelAndView confirmation(@RequestParam("email") String email){
		Member member = accountService.findByEmail(email);
		
		ModelMap model =  new ModelMap();
		model.addAttribute("user", member);
		
		return new ModelAndView("confirmation", model);
	}
}
