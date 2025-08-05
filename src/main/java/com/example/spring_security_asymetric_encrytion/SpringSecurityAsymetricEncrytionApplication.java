package com.example.spring_security_asymetric_encrytion;

import com.example.spring_security_asymetric_encrytion.role.Role;
import com.example.spring_security_asymetric_encrytion.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.Optional;

@SpringBootApplication

public class SpringSecurityAsymetricEncrytionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAsymetricEncrytionApplication.class, args);


	}
	@Bean
	public CommandLineRunner commandLineRunner(final RoleRepository roleRepository) {
		return args -> {
			final Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
			if (userRole.isEmpty()) {
				final Role role = new Role();
				role.setName("ROLE_USER");
				role.setCreatedBy("admin");
				roleRepository.save(role);
			}
		};
	}


}
