package com.darcalzadilla.moongym.entity;

import jakarta.persistence.*;

@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private ClassSession classSession;
}
