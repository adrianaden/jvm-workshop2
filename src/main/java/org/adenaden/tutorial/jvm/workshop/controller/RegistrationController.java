package org.adenaden.tutorial.jvm.workshop.controller;

import org.adenaden.tutorial.jvm.workshop.entity.Member;
import org.adenaden.tutorial.jvm.workshop.model.RegistrationDTO;
import org.adenaden.tutorial.jvm.workshop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("registration")
public class RegistrationController {
	
	@Autowired
	private MemberService accountService;
	
	@GetMapping("/form")
	public ModelAndView registration(){
		ModelMap model =  new ModelMap();
		model.addAttribute("registration", new RegistrationDTO());
		
		return new ModelAndView("registration/form", model);
	}
	
	@PostMapping("/form")
	public ModelAndView registration(@ModelAttribute RegistrationDTO registration) {
		Member account = accountService.create(registration);
		
		ModelMap model =  new ModelMap();
		model.addAttribute("email", account.getEmail());
		
		return new ModelAndView("redirect:/confirmation", model);
	}
	
	@GetMapping("verified")
	public void emailTerverifikasi() {
	
	}
	
}
