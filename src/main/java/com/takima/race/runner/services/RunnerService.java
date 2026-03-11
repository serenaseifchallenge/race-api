package com.takima.race.runner.services;

import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.repositories.RegistrationRepository;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RunnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RunnerService {

    private final RunnerRepository runnerRepository;
    private final RegistrationRepository registrationRepository;

    public RunnerService(RunnerRepository runnerRepository, RegistrationRepository registrationRepository) {
        this.runnerRepository = runnerRepository;
        this.registrationRepository = registrationRepository;
    }

    public List<Runner> getAll() {
        return runnerRepository.findAll();
    }

    public Runner getById(Long id) {
        return runnerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Runner %s not found", id)
                )
        );
    }

    public Runner create(Runner runner){
        if (!runner.getEmail().contains("@")) { // si le mail ne contient pas @ on affiche une erreur 409 avec un message
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                "Email must contain '@'"
            );
        }
        return runnerRepository.save(runner);
    }

    public void delete(Long id){
        runnerRepository.deleteById(id);
    }

    public List<Registration> listCourse(long runnerId){
        return registrationRepository.findByRunnerId(runnerId);
    }

}
