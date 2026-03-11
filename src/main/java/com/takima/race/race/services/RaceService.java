package com.takima.race.race.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.race.entities.Race;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.registration.repositories.RegistrationRepository;

@Service
public class RaceService {

    private final RaceRepository raceRepository;
    private final RegistrationRepository registrationRepository;

    public RaceService(RaceRepository raceRepository, RegistrationRepository registrationRepository) {
        this.raceRepository = raceRepository;
        this.registrationRepository = registrationRepository;
    }

    public List<Race> getAll(String location) {
        if (location != null && !location.isEmpty()) { // si on ajoute une adresse (trier par adresse)
            return raceRepository.findByLocation(location); //on affiche la liste des courses par adresse
        }
        return raceRepository.findAll(); // sinon on affiche la liste de toute les courses
    }

    public Race getById(Long id) {
        return raceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Race %s not found", id)));
    }

    public Race create(Race race) {
        return raceRepository.save(race);
    }

    // on donne à registration le raceId pour qu'il compte le nombre de coureur pour une course quelconque 
    public long countByRaceId(long RaceId) {
        return registrationRepository.countByRaceId(RaceId);
    }

}
