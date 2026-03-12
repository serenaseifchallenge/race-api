package com.takima.race.runner.services;

import com.takima.race.race.entities.Race;
import com.takima.race.race.services.RaceService;
import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.repositories.RegistrationRepository;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RunnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class RunnerService {

    private final RunnerRepository runnerRepository;
    private final RegistrationRepository registrationRepository;
    private final RaceService raceService;

    public RunnerService(RunnerRepository runnerRepository, RegistrationRepository registrationRepository,
            RaceService raceService) {
        this.runnerRepository = runnerRepository;
        this.registrationRepository = registrationRepository;
        this.raceService = raceService;
    }

    public List<Runner> getAll() {
        return runnerRepository.findAll();
    }

    public Runner getById(Long id) {
        return runnerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Runner %s not found", id)));
    }

    public Runner create(Runner runner) {
        if (!runner.getEmail().contains("@")) { // si le mail ne contient pas @ on affiche une erreur 409 avec un
                                                // message
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email must contain '@'");
        }
        return runnerRepository.save(runner);
    }

    public void delete(Long id) {
        List<Registration> registrationsCoureur = registrationRepository.findByRunnerId(id);
        if (!registrationsCoureur.isEmpty()) {
            for (Registration r : registrationsCoureur) {
                long registrationId = r.getId();
                registrationRepository.deleteById(registrationId);

            }
        }
        runnerRepository.deleteById(id);
    }

    public List<Race> listCourse(long runnerId) {
        List<Registration> registrationsCoureur = registrationRepository.findByRunnerId(runnerId);
        List<Race> racesRunner = new ArrayList<>();
        for (Registration r : registrationsCoureur) {
            long raceId = r.getRaceId();
            Race race = raceService.getById(raceId);
            racesRunner.add(race);
        }
        return racesRunner;

    }

}
