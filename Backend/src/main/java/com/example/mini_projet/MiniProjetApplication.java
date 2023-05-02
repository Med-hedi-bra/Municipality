package com.example.mini_projet;

import com.example.mini_projet.Auth.AuthenticationService;
import com.example.mini_projet.Dto.Request.RegisterRequest;
import com.example.mini_projet.Enums.Role;
import com.example.mini_projet.User.User;
import com.example.mini_projet.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication

public class MiniProjetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniProjetApplication.class, args);

	}
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service,
			UserRepository userRepository,
			PasswordEncoder passwordEncoder
	)
	{
		return args -> {
			var admin = User.builder()
					.firstname("admin")
					.lastname("admin")
					.cin("00000000")
					.role(Role.ADMIN)
					.password(passwordEncoder.encode("1234"))
					.build();
			userRepository.save(admin);

			System.out.println("admin created");

			var subadmin = User.builder()
					.firstname("subadmin")
					.lastname("subadmin")
					.cin("11111111")
					.role(Role.SUBADMIN)
					.password(passwordEncoder.encode("1234"))
					.build();
			userRepository.save(subadmin);
			System.out.println("subadmin created");
		};
	}



}
