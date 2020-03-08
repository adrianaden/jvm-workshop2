package org.adenaden.tutorial.jvm.workshop.service;

import org.adenaden.tutorial.jvm.workshop.entity.EmailVerification;
import org.adenaden.tutorial.jvm.workshop.entity.Member;
import org.adenaden.tutorial.jvm.workshop.repository.EmailVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EmailVerificationService {
	@Autowired
	private EmailVerificationRepository emailVerificationRepository;
	
	@Transactional
	public Boolean verify(String token){
		EmailVerification emailVerification = emailVerificationRepository.findByToken(token);
		if(emailVerification != null){
			Member member = emailVerification.getMember();
			member.setVerified(Boolean.TRUE);
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
}
