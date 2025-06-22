package com.darcalzadilla.moongym.repository;

import com.darcalzadilla.moongym.entity.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IClassSessionRepository extends JpaRepository<ClassSession, Long> {

    List<ClassSession> findTop3ByOrderByDateTimeAsc();

    List<ClassSession> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

    List<ClassSession> findByRecurringWeekdaysTrueAndDateTimeBefore(LocalDateTime localDateTime);

    boolean existsByNameAndDateTime(String name, LocalDateTime newDateTime);

    @Query("SELECT DISTINCT cs.name FROM ClassSession cs")
    List<String> findDistinctNames();

    List<ClassSession> findByNameAndDateTimeBetween(String name, LocalDateTime from, LocalDateTime to);

    @Query("""
      SELECT cs
        FROM ClassSession cs
       WHERE cs.name          = :name
         AND cs.dateTime     >= :start
         AND cs.dateTime     <= :end
       ORDER BY cs.dateTime ASC
    """)
    List<ClassSession> findByNameAndDateTimeBetweenOrderByDateTime(
            @Param("name")  String name,
            @Param("start") LocalDateTime start,
            @Param("end")   LocalDateTime end
    );

    List<ClassSession> findByName(String className);

    List<ClassSession> findByNameAndRecurringWeekdaysTrue(String className);


}
