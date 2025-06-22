package com.darcalzadilla.moongym.repository;

import com.darcalzadilla.moongym.entity.ClassSession;
import com.darcalzadilla.moongym.entity.Reservation;
import com.darcalzadilla.moongym.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByUserAndClassSession(User user, ClassSession classSession);
    List<Reservation> findByUser(User user);
}
