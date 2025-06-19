package com.darcalzadilla.moongym.repository;

import com.darcalzadilla.moongym.entity.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IClassSessionRepository extends JpaRepository<ClassSession, Long> {

    /**
     * Obtiene las tres próximas clases ordenadas por fecha ascendente.
     */
    List<ClassSession> findTop3ByOrderByDateTimeAsc();

    /**
     * Busca clases dentro de un rango de fecha y hora.
     * @param start inicio del rango (inclusive)
     * @param end fin del rango (inclusive)
     */
    List<ClassSession> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

}
