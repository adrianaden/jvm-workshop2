package org.adenaden.tutorial.jvm.workshop.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LayoutDialectConfig {
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
}
