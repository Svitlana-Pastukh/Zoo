package org.park.zoo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.park.zoo.animals.Animal;
import org.park.zoo.animals.exceptions.AnimalDoesNotExist;
import org.park.zoo.animals.exceptions.AnimalNotFound;
import org.park.zoo.animals.exceptions.EmployeeNotFound;
import org.park.zoo.workers.AnimalExpert;

import java.sql.SQLException;
import java.util.List;

public interface AnimalService {

    Animal createAnimal(String animalJson) throws SQLException, JsonProcessingException;

    List<Animal> selectAllAnimals() throws SQLException, JsonProcessingException;

    Animal selectAnimalById(String id) throws SQLException, JsonProcessingException, AnimalNotFound;

    Animal updateAnimal(Animal animal) throws SQLException, JsonProcessingException;

    void deleteAnimal(String id) throws SQLException, JsonProcessingException, AnimalNotFound;

    void changeHibernationState(String id, String action) throws SQLException, JsonProcessingException, AnimalNotFound;

    boolean giveWaterToAnimalById(String id) throws SQLException, JsonProcessingException;

    boolean sendToVet(Animal animal) throws SQLException, JsonProcessingException, EmployeeNotFound;

    boolean feedAnimal(Animal animal) throws AnimalDoesNotExist, SQLException, JsonProcessingException, EmployeeNotFound;

    void initialize() throws SQLException, JsonProcessingException;

}
