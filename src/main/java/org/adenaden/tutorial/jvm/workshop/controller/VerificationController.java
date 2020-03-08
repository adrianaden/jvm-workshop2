package org.adenaden.tutorial.jvm.workshop.controller;

import org.adenaden.tutorial.jvm.workshop.service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {
	
	@Autowired
	private EmailVerificationService emailVerificationService;
	
	@GetMapping("verify")
	public Boolean verify(@RequestParam String token) {
		return emailVerificationService.verify(token);
	}
	
}
