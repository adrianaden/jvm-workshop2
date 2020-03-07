package org.adenaden.tutorial.jvm.workshop.repository;

import org.adenaden.tutorial.jvm.workshop.entity.Bill;
import org.adenaden.tutorial.jvm.workshop.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long > {
	
}
