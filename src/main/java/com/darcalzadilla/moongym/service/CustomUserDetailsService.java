package com.darcalzadilla.moongym.service;

import com.darcalzadilla.moongym.entity.User;
import com.darcalzadilla.moongym.repository.IUserRepository;
import com.darcalzadilla.moongym.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final IUserRepository userRepo;

    public CustomUserDetailsService(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        return new CustomUserDetails(user);
    }
}
