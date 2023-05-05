package com.example.mini_projet;

import com.example.mini_projet.Auth.AuthenticationService;
import com.example.mini_projet.Enums.Role;
import com.example.mini_projet.Municipality.Municipality;
import com.example.mini_projet.Municipality.MunicipalityRepository;
import com.example.mini_projet.User.User;
import com.example.mini_projet.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootApplication

public class MiniProjetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniProjetApplication.class, args);

	}
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service,
			UserRepository userRepository,
			MunicipalityRepository municipalityRepository,
			PasswordEncoder passwordEncoder
	)
	{
		return args -> {
			var municipality = Municipality.builder()
					.idMun(1L)
					.maitre("wassef")
					.president("wassef")
					.secritaire("wassef")
					.build();
			municipalityRepository.save(municipality);
			System.out.println("municipality 1 created");


			var admin = User.builder()
					.firstname("admin")
					.lastname("admin")
					.cin("00000000")
					.role(Role.ADMIN)
					.password(passwordEncoder.encode("1234"))
					.municipality(municipality)
					.build();
			userRepository.save(admin);

			System.out.println("admin created");

			var subadmin = User.builder()
					.firstname("subadmin")
					.lastname("subadmin")
					.cin("11111111")
					.role(Role.SUBADMIN)
					.password(passwordEncoder.encode("1234"))
					.municipality(municipality)
					.build();
			userRepository.save(subadmin);
			System.out.println("subadmin created");


		};
	}



}
