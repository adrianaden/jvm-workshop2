package org.adenaden.tutorial.jvm.workshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@Entity(name = "course")
@NoArgsConstructor
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 100)
	private String code;
	
	@NotNull
	@Size(min = 3, max = 100)
	private String name;
	
	private Long price;
	
	public Course(String code, String name, Long price){
		this.code = code;
		this.name = name;
		this.price = price;
	}
}

