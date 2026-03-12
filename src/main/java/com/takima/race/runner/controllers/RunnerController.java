package com.takima.race.runner.controllers;

import com.takima.race.race.entities.Race;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.services.RunnerService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/runners")
public class RunnerController {
    private final RunnerService runnerService;

    public RunnerController(RunnerService runnerService) {
        this.runnerService = runnerService;
    }

    //Lister les coureurs
    @GetMapping
    public List<Runner> getAll() {
        return runnerService.getAll();
    }

    // Récupérer un coureur
    @GetMapping("/{id}")
    public Runner getById(@PathVariable Long id) {
        return runnerService.getById(id);
    }

    // Créer un coureur
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Runner create(@RequestBody Runner runner) {
        return runnerService.create(runner);
    }

    // Supprimer un coureur
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        runnerService.delete(id);
    }

    // Modifier un coureur
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Runner update(@PathVariable Long id, @RequestBody Runner runner) {
        // Vérifier si le Runner avec l'id existe
        runnerService.getById(id);
        runner.setId(id); // s'il existe on va set l'Id du coureur à modifier et sinon une erreur 404 s'affichera
        return runnerService.create(runner);
    }

    // Lister les courses d'un coureur
    @GetMapping("/{runnerId}/races")
    public List<Race> listCourse(@PathVariable long runnerId){
        return runnerService.listCourse(runnerId);
    }

}
