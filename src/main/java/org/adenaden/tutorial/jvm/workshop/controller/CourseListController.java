package org.adenaden.tutorial.jvm.workshop.controller;

import org.adenaden.tutorial.jvm.workshop.entity.Account;
import org.adenaden.tutorial.jvm.workshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/course")
public class CourseListController {
	
	@Autowired
	private AccountService accountService;
	
//	@GetMapping("/course-list")
//	public ModelAndView login(@RequestParam("email") String email) {
//		Account account =  accountService.findByEmail(email);
//
//		ModelMap model = new ModelMap();
//		model.addAttribute("account", account);
//
//		return new ModelAndView("course-list", model);
//	}
	
	@GetMapping("list")
	public ModelMap list() {
		return new ModelMap();
	}
	
	@GetMapping("/enroll")
	public ModelMap displayEnrollment() {
		return new ModelMap();
	}
	
	@PostMapping("/enroll")
	public String processEnrollment() {
		return "redirect:enrollment_confirmation";
	}
	
	@GetMapping("/enrollment_confirmation")
	public ModelMap displayEnrollmentConfirmation() {
		return new ModelMap();
	}
}
