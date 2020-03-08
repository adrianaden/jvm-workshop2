package org.adenaden.tutorial.jvm.workshop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity(name = "payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "bill_id")
	private Bill bill;
	
	@NotNull
	private LocalDateTime paymentTime;
	
	@NotEmpty
	private String reference;
	
	private String dokuResponseCode;
	
	
}
