package org.adenaden.tutorial.jvm.workshop.controller;

import org.adenaden.tutorial.jvm.workshop.entity.Course;
import org.adenaden.tutorial.jvm.workshop.model.RegistrationDTO;
import org.adenaden.tutorial.jvm.workshop.service.CourseService;
import org.adenaden.tutorial.jvm.workshop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseListController {
	
	@Autowired
	private MemberService accountService;
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("list")
	public ModelAndView list() {
		List<Course> courses = courseService.findAll();
		
		
		ModelMap model =  new ModelMap();
		model.addAttribute("courses", courses);
		
		return new ModelAndView("course/list", model);
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
