package com.takima.race.registration.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.race.entities.Race;
import com.takima.race.race.services.RaceService;
import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.repositories.RegistrationRepository;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final RaceService raceService;

    public RegistrationService(RegistrationRepository registrationRepository, RaceService raceService) {
        this.registrationRepository = registrationRepository;
        this.raceService = raceService;
    }

    public List<Registration> getByRaceId(Long raceId) {
        return registrationRepository.findByRaceId(raceId);
    }

    public Registration create(long raceId, Registration registration) {
        if (registrationRepository.existsByRunnerIdAndRaceId(registration.getRunnerId(), raceId)) { //si boolean true donc coureur déjà inscrit à la course
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Runner already registered for this race");
        }
        Race race = raceService.getById(raceId); //récupérer la course 
        int max = race.getMaxParticipants(); //trouver le nbr max de participant
        if(registrationRepository.countByRaceId(raceId)>=max){ // Règle métier pour vérifier si nbr max de coureur pour une course a été atteint
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Race has reached maximum capacity");
        }
        registration.setRaceId(raceId);
        registration.setRegistrationDate(LocalDate.now());
        return registrationRepository.save(registration);
    }

}
