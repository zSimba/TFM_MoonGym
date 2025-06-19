package com.darcalzadilla.moongym.service;

import com.darcalzadilla.moongym.entity.User;

public interface IUserService {

    /** Verifica si existe un usuario por username */
    boolean existsByUsername(String username);

    /** Guarda un nuevo usuario */
    User save(User user);

    /** Busca un usuario por username */
    User findByUsername(String username);
}
