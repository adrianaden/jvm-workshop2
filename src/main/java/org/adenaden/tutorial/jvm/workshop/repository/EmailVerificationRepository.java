package org.adenaden.tutorial.jvm.workshop.repository;

import org.adenaden.tutorial.jvm.workshop.entity.Bill;
import org.adenaden.tutorial.jvm.workshop.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long > {
	
}
