package com.example.assignmentrest.persistance;

import com.example.assignmentrest.api.model.Animal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AnimalService {

    private ArrayList<Animal> animals;

    public AnimalService(ArrayList<Animal> animals) {
        this.animals = animals;

        animals.add(new Animal("2023-04-01",250,123,"Horsens farmhouse"));
        animals.add(new Animal("2023-04-02",200,456,"Horsens farmhouse"));
        animals.add(new Animal("2023-04-02",350,789,"Aarhus"));
        animals.add(new Animal("2023-04-01",300,100,"Kopenhagen"));
    }

    public ArrayList<Animal> getAllAnimals()
    {
        return animals;
    }

    public Optional getAnimalByRegistrationNr(double registrationId)
    {
        Optional<Animal> existing = Optional.empty();
        for (Animal animal:animals) {
            if (animal.getRegistrationNr()==registrationId)
                existing = Optional.of(animal);
        }
        return existing;
    }

    public ArrayList<Animal> getAllAnimalsByDate(String date)
    {
        ArrayList<Animal> returnList = new ArrayList<>();
        for (Animal animal:animals) {
            if (animal.getDate().equals(date))
                returnList.add(animal);
        }
        return returnList;
    }

    public ArrayList<Animal> getAllAnimalsByOrigin(String origin)
    {
        ArrayList<Animal> returnList= new ArrayList<>();
        for (Animal animal:animals) {
            if (animal.getOrigin().equals(origin))
                returnList.add(animal);
        }
        return returnList;
    }

    public boolean addAnimal(Animal animal)
    {
        Optional<Animal> existing = getAnimalByRegistrationNr(animal.getRegistrationNr());
        if (existing.isPresent()){
            return false;
        }
        else{
            animals.add(animal);
            return true;
        }
    }
}
