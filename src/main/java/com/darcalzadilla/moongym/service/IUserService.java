package com.darcalzadilla.moongym.service;

import com.darcalzadilla.moongym.entity.User;

public interface IUserService {

    boolean existsByUsername(String username);
    User save(User user);
}
