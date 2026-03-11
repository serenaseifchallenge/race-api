package com.takima.race.registration.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.takima.race.registration.entities.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByRaceId(Long raceId); //trouver toutes les inscriptions par course
    List<Registration> findByRunnerId(long runnerId); // trouver toutes les inscriptions par coureur
    long countByRaceId(Long raceId); // compter le nombre d'inscription par course
    boolean existsByRunnerIdAndRaceId(Long runnerId, Long raceId); //boolean true si un coureur s'inscrit 2 fois à la même course

}