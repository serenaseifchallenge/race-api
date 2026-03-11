package com.takima.race.registration.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.services.RegistrationService;

@RestController
@RequestMapping("/races/{raceId}/registrations")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    // Lister les participants d'une course
    @GetMapping
    public List<Registration> getAllByRaceId(@PathVariable Long raceId) {
        return registrationService.getByRaceId(raceId);
    }

    // Inscrire un coureur à une course
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Registration create(@PathVariable long raceId, @RequestBody Registration registration) {
        return registrationService.create(raceId, registration);
    }
}
