package org.park.zoo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.park.App;
import org.park.zoo.animals.Animal;
import org.park.zoo.animals.Bear;
import org.park.zoo.animals.Wolf;
import org.park.zoo.animals.Zebra;
import org.park.zoo.animals.exceptions.AnimalNotFound;
import org.park.zoo.repositories.AnimalRepository;
import org.park.zoo.repositories.AnimalRepositoryImpl;
import org.park.zoo.repositories.EmployeeRepository;
import org.park.zoo.repositories.EmployeeRepositoryImpl;
import org.park.zoo.workers.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnimalServiceImplTest {

    AnimalRepository animalRepository = mock(AnimalRepositoryImpl.class);
    EmployeeRepository employeeRepository = mock(EmployeeRepositoryImpl.class);
    EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);
    AnimalService animalService = new AnimalServiceImpl(animalRepository, employeeRepository);


    @Test
    void createAnimal() throws JsonProcessingException, SQLException {
        Animal animal = new Animal();
        assertEquals(animalService.createAnimal(App.createJson(animal)).getId(), animal.getId());
    }

    @Test
    void selectAllAnimals() throws SQLException, JsonProcessingException {
        List<Animal> animals = new ArrayList<>();
        Wolf wolf = new Wolf();
        Zebra zebra = new Zebra();
        animals.add(wolf);
        animals.add(zebra);
        when(animalRepository.selectAllAnimals()).thenReturn(animals);
        assertEquals(animals, animalService.selectAllAnimals());
    }

    @Test
    void selectAnimalById() throws SQLException, JsonProcessingException, AnimalNotFound {
        Animal animal = new Animal();
        String a = animal.getId();
        when(animalRepository.selectAnimalById(a)).thenReturn(animal);
        assertEquals(animal, animalService.selectAnimalById(a));
    }

    @Test
    void updateAnimal() throws SQLException, JsonProcessingException {
        Animal animal = new Animal();
        when(animalRepository.insertAnimal(animal)).thenReturn(animal);
        assertEquals(animal, animalService.updateAnimal(animal));
        verify(animalRepository, times(1)).insertAnimal(animal);
    }

    @Test
    void deleteAnimal() throws AnimalNotFound, SQLException, JsonProcessingException {
        Animal animal = new Animal();
        animalService.deleteAnimal(animal.getId());
        verify(animalRepository, times(1)).deleteAnimal(animal.getId());
    }

//    @Test
//    void changeHibernationState() throws AnimalNotFound, SQLException, JsonProcessingException {
//        Bear bear = new Bear();
//        String a = bear.getId();
//        animalService.changeHibernationState(a, "True");
//    }

    @Test
    void giveWaterToAnimalById() throws SQLException, JsonProcessingException {
        Animal animal = new Animal();
        String a = animal.getId();
        when(animalRepository.selectAnimalById(a)).thenReturn(animal);
        assertTrue(animalService.giveWaterToAnimalById(a));
        verify(animalRepository,times(1)).selectAnimalById(a);
    }

    @Test
    void sendToVet() {
    }

    @Test
    void addAnimalToEnclosure() {
    }

    @Test
    void feedAnimal() {
    }

    @Test
    void initialize() {
    }

    @Test
    void getInstance() {
    }
}