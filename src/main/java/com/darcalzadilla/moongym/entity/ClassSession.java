package com.darcalzadilla.moongym.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "class_session")
public class ClassSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private int capacity;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "intensity_level", nullable = false)
    private int intensityLevel;

    @OneToMany(mappedBy = "classSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    public int getIntensityLevel() {
        return intensityLevel;
    }

    public void setIntensityLevel(int intensityLevel) {
        this.intensityLevel = intensityLevel;
    }


    public void setIntensity(int intensity) {
        this.intensityLevel = intensity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

}
