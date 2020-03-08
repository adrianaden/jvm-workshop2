package org.adenaden.tutorial.jvm.workshop.dummy;

import org.adenaden.tutorial.jvm.workshop.entity.Course;
import org.adenaden.tutorial.jvm.workshop.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CourseDummy {
	@Autowired
	private CourseRepository courseRepository;
	
	@Bean
	public void createDummy(){
		List<Course> courseList = new ArrayList<>();
		courseList.add(new Course("JVM-001", "Workshop 1", 20000L));
		courseList.add(new Course("JVM-002", "Workshop 2", 30000L));
		courseList.add(new Course("JVM-003", "Workshop 3", 40000L));
		courseList.add(new Course("JVM-004", "Workshop 4", 50000L));
		courseList.forEach(course -> courseRepository.save(course));
	}
}
