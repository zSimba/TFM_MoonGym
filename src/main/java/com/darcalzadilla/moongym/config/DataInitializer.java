package com.darcalzadilla.moongym.config;

import com.darcalzadilla.moongym.entity.Role;
import com.darcalzadilla.moongym.entity.User;
import com.darcalzadilla.moongym.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdmin(IUserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            String adminUsername = "admin";
            if (userRepo.findByUsername(adminUsername).isEmpty()) {
                User admin = new User();
                admin.setUsername(adminUsername);
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                userRepo.save(admin);
                log.info("Se ha creado un nuevo usuario ADMIN: {}", adminUsername);
            }
        };
    }
}