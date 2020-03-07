package org.adenaden.tutorial.jvm.workshop.repository;

import org.adenaden.tutorial.jvm.workshop.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long > {
	
}
