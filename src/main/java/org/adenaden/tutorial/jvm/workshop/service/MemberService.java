package org.adenaden.tutorial.jvm.workshop.service;

import org.adenaden.tutorial.jvm.workshop.entity.Member;
import org.adenaden.tutorial.jvm.workshop.model.RegistrationDTO;
import org.adenaden.tutorial.jvm.workshop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	public Member create(RegistrationDTO registration) {
		Member member = new Member();
		member.setName(registration.getName());
		member.setEmail(registration.getEmail());
		member.setPassword(System.currentTimeMillis() + "");
		return create(member);
	}
	
	public Member create(Member member) {
		return memberRepository.save(member);
	}
	
	public Member findByEmail(String email){
		return memberRepository.findTopByEmail(email);
	}
	public Member findByEmailAndPassword(String email, String password){
		return memberRepository.findByEmailAndPassword(email, password);
	}
	
}
