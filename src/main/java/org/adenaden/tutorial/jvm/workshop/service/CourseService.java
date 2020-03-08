package org.adenaden.tutorial.jvm.workshop.service;

import org.adenaden.tutorial.jvm.workshop.entity.Course;
import org.adenaden.tutorial.jvm.workshop.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;
	
	public List<Course> findAll(){
		return courseRepository.findAll();
	}
	
	public Course findById(Long id) {
		return courseRepository.findById(id).orElse(null);
	}
}
