package com.example.assignmentrest.api.controller;

import com.example.assignmentrest.api.model.Animal;
import com.example.assignmentrest.persistance.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/Animal/")
public class AnimalController {

    private AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Animal>> getAllAnimals(){
        return new ResponseEntity<>(animalService.getAllAnimals(), HttpStatus.OK);
    }

    @GetMapping("registrationNr/")
    public ResponseEntity<Animal> getAnimalByRegistrationNr(double registrationNr){
        Optional<Animal> existing = animalService.getAnimalByRegistrationNr(registrationNr);
        if (existing.isPresent())
            return new ResponseEntity<>(existing.get(),HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("date/")
    public ResponseEntity<ArrayList<Animal>> getAnimalsByDate(String date){
        return new ResponseEntity<>(animalService.getAllAnimalsByDate(date),HttpStatus.OK);
    }

    @GetMapping("origin/")
    public ResponseEntity<ArrayList<Animal>> getAnimalsByOrigin(String origin){
        return new ResponseEntity<>(animalService.getAllAnimalsByOrigin(origin),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Animal> addNewAnimal(@RequestBody Animal animal){
        boolean success = animalService.addAnimal(animal);
        if (success)
        {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
