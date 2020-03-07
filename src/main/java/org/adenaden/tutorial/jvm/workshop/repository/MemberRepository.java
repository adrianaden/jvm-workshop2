package org.adenaden.tutorial.jvm.workshop.repository;

import org.adenaden.tutorial.jvm.workshop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findTopByEmail(String email);
	
	Member findByEmailAndPassword(String email, String password);
	
}
