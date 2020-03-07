package org.adenaden.tutorial.jvm.workshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity(name = "bill")
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "registration_id")
	private Registration registration;
	
	private String invoiceNumber;
	
	private String bank;
	private String account_number;
	private String account_name;
	private String description;
	private LocalDateTime createDate;
	private LocalDateTime expiredDate;
	private Boolean hasPaid;
}
