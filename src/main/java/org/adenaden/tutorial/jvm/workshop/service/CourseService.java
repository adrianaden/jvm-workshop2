package org.adenaden.tutorial.jvm.workshop.service;

import lombok.extern.slf4j.Slf4j;
import org.adenaden.tutorial.jvm.workshop.entity.Course;
import org.adenaden.tutorial.jvm.workshop.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Slf4j
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
	
	public void enroll(Long courseId) {
		
		Course course = findById( courseId);
		String content = String.format(
			"Anda telah mendaftar %s dengan jumlah %d, silahkan melakukan pembayaran di %s",
			course.getName(),
			course.getPrice(),
			"www.google.com"
		);
		log.info(content);
		
		try{
			sendEmail("Pembayaran", content);
		} catch(Exception e){
			log.error(e.getMessage(), e);
		}
		
	}
	
	
	void sendEmail(String subject, String content) throws UnirestException {
		HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/YOUR_DOMAIN.mailgun.org/messages")
			.basicAuth("api", "<YOUR_API>")
			.field("from", "Excited User <moka@mokapos.com>")
			.field("to", "a.adendrata@gmail.com")
			.field("subject", subject)
			.field("text", content)
			.asJson();
		request.getBody();
	}
}
