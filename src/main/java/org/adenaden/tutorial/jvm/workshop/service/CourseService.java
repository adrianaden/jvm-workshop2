package org.adenaden.tutorial.jvm.workshop.service;

import lombok.extern.slf4j.Slf4j;
import org.adenaden.tutorial.jvm.workshop.entity.*;
import org.adenaden.tutorial.jvm.workshop.repository.BillRepository;
import org.adenaden.tutorial.jvm.workshop.repository.CourseRepository;
import org.adenaden.tutorial.jvm.workshop.repository.MemberRepository;
import org.adenaden.tutorial.jvm.workshop.repository.RegistrationRepository;
import org.adenaden.tutorial.jvm.workshop.service.request.DokuHostedRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Slf4j
@Service
public class CourseService {
	@Autowired
	private RegistrationRepository registrationRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private DokuService dokuService;
	
	public List<Course> findAll(){
		return courseRepository.findAll();
	}
	
	public Course findById(Long id) {
		return courseRepository.findById(id).orElse(null);
	}
	
	public DokuHostedRequestDTO enroll( Long courseId) {
		Member member = memberRepository.findById(1L).orElse(null);
		Course course = courseRepository.findById(courseId).orElse(null);
		
		Registration registration = new Registration();
		registration.setMember(member);
		registration.setCourse(course);
		registration.setHasPay(Boolean.FALSE);
		registrationRepository.save(registration);
		
		Bill bill = new Bill();
		bill.setAccount_name("Adrian Adendrata");
		bill.setAccount_number("1234567890");
		bill.setBank("CBA");
		bill.setCreateDate(LocalDateTime.now());
		bill.setExpiredDate(LocalDateTime.now().plusDays(3));
		bill.setHasPaid(Boolean.FALSE);
		bill.setInvoiceNumber(System.currentTimeMillis() +  "");
		bill.setRegistration(registration);
		bill.setDescription("Lorem Ipsum");
		billRepository.save(bill);
		
		
		String content = String.format(
			"Anda telah mendaftar %s dengan jumlah %d, silahkan melakukan pembayaran di %s",
			course.getName(),
			course.getPrice(),
			"www.google.com"
		);
		log.info(content);
		return dokuService.createDokuRequest(bill);
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
