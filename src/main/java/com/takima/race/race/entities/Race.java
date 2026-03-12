package com.takima.race.race.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String name;
    private LocalDate date;
    private String location;
    private int maxParticipants;

    public Race(Long id, String name, LocalDate date, String location, int maxParticipants) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.maxParticipants = maxParticipants;
    }

    public Race() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return Objects.equals(id, race.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id); 
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }
}