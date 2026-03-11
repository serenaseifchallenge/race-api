package com.takima.race.race.controllers;

import com.takima.race.race.entities.Race;
import com.takima.race.race.services.RaceService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/races")
public class RaceController {
    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    //Lister les courses
    @GetMapping
    public List<Race> getAll(@RequestParam(required = false) String location) {
        return raceService.getAll(location);
    }

    // Récupérer une course
    @GetMapping("/{id}")
    public Race getById(@PathVariable Long id) {
        return raceService.getById(id);
    }

    // Créer une course
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Race create(@RequestBody Race race) {
        return raceService.create(race);
    }

    // compter le nombre de participants par course
    @GetMapping("/{raceId}/participants/count")
    public Map<String, Long> countByRaceId(@PathVariable Long raceId) {

        raceService.getById(raceId); // verifier si elle existe
        long count = raceService.countByRaceId(raceId); // compter nombre de participant par course
        return Collections.singletonMap("count", count); // afficher sous format [count : " "]
    }

}
