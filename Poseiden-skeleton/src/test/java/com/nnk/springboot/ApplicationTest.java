package com.nnk.springboot;

import com.nnk.springboot.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ApplicationTest {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@MockBean
	WebSecurityConfiguration WebsecurityConfiguration;

	@MockBean
	WebSecurityConfig WebSecurityConfig;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
