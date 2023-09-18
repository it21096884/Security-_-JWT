package com.example.userservice;

import com.example.userservice.domain.Role;
import com.example.userservice.domain.Users;
import com.example.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

			userService.saveUser(new Users(null,"John Travlour","john","1234",new ArrayList<>()));
			userService.saveUser(new Users(null,"Vistor Travlour","vistor","3456",new ArrayList<>()));
			userService.saveUser(new Users(null,"Ann Travlour","ann","9876",new ArrayList<>()));
			userService.saveUser(new Users(null,"Ameli Travlour","ameli","0087",new ArrayList<>()));

			userService.addRoleToUSer("john","ROLE_USER");
			userService.addRoleToUSer("vistor","ROLE_MANAGER");
			userService.addRoleToUSer("ameli","ROLE_ADMIN");
			userService.addRoleToUSer("ameli","ROLE_SUPER_ADMIN");
			userService.addRoleToUSer("ameli","ROLE_MANAGER");
		};
	}

}
