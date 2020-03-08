package org.adenaden.tutorial.jvm.workshop.controller;

import org.adenaden.tutorial.jvm.workshop.entity.Course;
import org.adenaden.tutorial.jvm.workshop.model.RegistrationDTO;
import org.adenaden.tutorial.jvm.workshop.service.CourseService;
import org.adenaden.tutorial.jvm.workshop.service.DokuService;
import org.adenaden.tutorial.jvm.workshop.service.MemberService;
import org.adenaden.tutorial.jvm.workshop.service.request.DokuHostedRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseListController {
	
	@Autowired
	private MemberService accountService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	DokuService dokuService;
	
	@GetMapping("list")
	public ModelAndView list() {
		List<Course> courses = courseService.findAll();
		
		
		ModelMap model =  new ModelMap();
		model.addAttribute("courses", courses);
		
		return new ModelAndView("course/list", model);
	}
	
	@GetMapping("/enroll")
	public ModelAndView displayEnroll(@RequestParam Long id) {
		
		Course course = courseService.findById(id);
		
		ModelMap model =  new ModelMap();
		model.addAttribute("course", course);
		
		return new ModelAndView("course/enroll", model);
	}
	
	@PostMapping("/enroll")
	public String processEnrollment(@RequestParam Long id, RedirectAttributes redir) {
		
		DokuHostedRequestDTO dokuRequest = courseService.enroll(id);
		
		ModelMap model =  new ModelMap();
		redir.addFlashAttribute("dokuUrl", dokuService.getDokuUrl()+"Receive");
		redir.addFlashAttribute("redirect", dokuRequest);
		
		return "redirect:/doku/continue";
	}
	
	@GetMapping("/enrollment_confirmation")
	public ModelAndView displayEnrollmentConfirmation(@RequestParam Long id) {
		
		Course course = courseService.findById(id);
		ModelMap model =  new ModelMap();
		model.addAttribute("course", course);
		
		return new ModelAndView("course/enrollment_confirmation", model);
	}
}
