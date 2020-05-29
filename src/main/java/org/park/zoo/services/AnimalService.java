package org.park.zoo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.park.zoo.animals.Animal;
import org.park.zoo.animals.exceptions.AnimalDoesNotExist;
import org.park.zoo.animals.exceptions.EmployeeNotFound;

import java.sql.SQLException;
import java.util.List;

public interface AnimalService {

    Animal createAnimal(String animalJson);

    List<Animal> selectAllAnimals() throws SQLException, JsonProcessingException;

    Animal selectAnimalById(String id) throws SQLException, JsonProcessingException;

    void updateAnimal(Animal animal) throws SQLException, JsonProcessingException;

    void deleteAnimal(String id) throws SQLException, JsonProcessingException;

    void startHibernate() throws SQLException, JsonProcessingException, AnimalDoesNotExist;

    void stopHibernate() throws SQLException, JsonProcessingException;

    void drinkWater();

    void sendToVet(Animal animal) throws SQLException, JsonProcessingException, EmployeeNotFound;  //vet

    void addAnimal(Animal animal);  //Enclosure

    void feedAnimal(Animal animal); //AnimalExpert


}
