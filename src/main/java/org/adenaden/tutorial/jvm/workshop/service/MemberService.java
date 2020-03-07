package org.adenaden.tutorial.jvm.workshop.service;

import org.adenaden.tutorial.jvm.workshop.entity.EmailVerification;
import org.adenaden.tutorial.jvm.workshop.entity.Member;
import org.adenaden.tutorial.jvm.workshop.model.RegistrationDTO;
import org.adenaden.tutorial.jvm.workshop.repository.EmailVerificationRepository;
import org.adenaden.tutorial.jvm.workshop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private EmailVerificationRepository emailVerificationRepository;
	public Member create(RegistrationDTO registration) {
		Member member = new Member();
		member.setName(registration.getName());
		member.setEmail(registration.getEmail());
		member.setVerified(Boolean.FALSE);
		create(member);
		
		EmailVerification emailVerification = new EmailVerification();
		emailVerification.setMember(member);
		emailVerification.setExpiredDate(LocalDateTime.now().plusDays(5));
		emailVerification.setToken(UUID.randomUUID().toString());
		emailVerificationRepository.save(emailVerification);
		
		return member;
	}
	
	public Member create(Member member) {
		return memberRepository.save(member);
	}
	
	public Member findByEmail(String email) {
		return memberRepository.findTopByEmail(email);
	}
	
	public Member findByEmailAndPassword(String email, String password) {
		return memberRepository.findByEmailAndPassword(email, password);
	}
	
}
