package com.darcalzadilla.moongym.service;

import com.darcalzadilla.moongym.entity.User;
import com.darcalzadilla.moongym.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    private final IUserRepository userRepo;

    public UserServiceImpl(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

}
