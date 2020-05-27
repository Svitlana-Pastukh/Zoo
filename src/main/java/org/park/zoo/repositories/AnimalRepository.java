package org.park.zoo.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.park.zoo.animals.Animal;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface AnimalRepository {

    void createAnimalsTable() throws SQLException;

    void insertAnimals(List<Animal> list) throws SQLException, JsonProcessingException;

    List<Animal> selectAllAnimals() throws SQLException, JsonProcessingException;

    Animal selectAnimalById(String id) throws SQLException, JsonProcessingException;

    void insertAnimal(Animal animal) throws SQLException, JsonProcessingException;

    void deleteAnimal(String id) throws SQLException, JsonProcessingException;

}
