package org.park.zoo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.park.zoo.animals.Animal;

import java.sql.SQLException;
import java.util.List;

public interface AnimalService {
    void createAnimalsTable() throws SQLException;

    void insertAnimals(List<Animal> list) throws SQLException, JsonProcessingException;

    List<Animal> selectAllAnimals() throws SQLException, JsonProcessingException;

    Animal selectAnimalById(String id) throws SQLException, JsonProcessingException;

    void insertAnimal(Animal animal) throws SQLException, JsonProcessingException;

    void deleteAnimal(String id) throws SQLException, JsonProcessingException;

    void startHibernate();

    void stopHibernate();

    void drinkWater();


}
