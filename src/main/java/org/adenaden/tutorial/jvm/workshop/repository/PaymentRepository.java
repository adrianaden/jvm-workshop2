package org.adenaden.tutorial.jvm.workshop.repository;

import org.adenaden.tutorial.jvm.workshop.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long > {
	
}
