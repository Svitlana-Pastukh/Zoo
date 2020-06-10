package org.park.zoo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.park.MapperUtil;
import org.park.zoo.animals.Animal;
import org.park.zoo.animals.Bear;
import org.park.zoo.animals.Wolf;
import org.park.zoo.animals.Zebra;
import org.park.zoo.animals.exceptions.AnimalDoesNotExist;
import org.park.zoo.animals.exceptions.AnimalNotFound;
import org.park.zoo.animals.exceptions.EmployeeNotFound;
import org.park.zoo.repositories.AnimalRepository;
import org.park.zoo.repositories.AnimalRepositoryImpl;
import org.park.zoo.repositories.EmployeeRepository;
import org.park.zoo.repositories.EmployeeRepositoryImpl;
import org.park.zoo.workers.AnimalExpert;
import org.park.zoo.workers.Vet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnimalServiceImplTest {

    AnimalRepository animalRepository = mock(AnimalRepositoryImpl.class);
    EmployeeRepository employeeRepository = mock(EmployeeRepositoryImpl.class);
    AnimalService animalService = new AnimalServiceImpl(animalRepository, employeeRepository);

    @Test
    void createAnimal() throws JsonProcessingException, SQLException {
        Animal animal = new Animal();
        assertEquals(animalService.createAnimal(MapperUtil.createJson(animal)).getId(), animal.getId());
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
    void selectAnimalWithIncorrectId() throws SQLException, JsonProcessingException {
        String a = "asd123";
        when(animalRepository.selectAnimalById(a)).thenReturn(null);
        assertThrows(AnimalNotFound.class, () -> animalService.selectAnimalById(a), "Correct id");
        verify(animalRepository, times(1)).selectAnimalById(a);
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
        String a = animal.getId();
        when(animalRepository.selectAnimalById(a)).thenReturn(animal);
        animalService.deleteAnimal(animal.getId());
        verify(animalRepository, times(1)).deleteAnimal(animal.getId());
    }

    @Test
    void deleteAnimalNegative() throws SQLException, JsonProcessingException {
        String a = "123asd";
        when(animalRepository.selectAnimalById(a)).thenReturn(null);
        assertThrows(AnimalNotFound.class, () -> animalService.deleteAnimal(a), "Correct id");
        verify(animalRepository, times(1)).selectAnimalById(a);
        verify(animalRepository, times(0)).deleteAnimal(a);
    }

    @Test
    void deleteAnimalWithNull() throws SQLException, JsonProcessingException {
        String a = null;
        when(animalRepository.selectAnimalById(a)).thenReturn(null);
        assertThrows(AnimalNotFound.class, () -> animalService.deleteAnimal(a), "Id not null");
        verify(animalRepository, times(0)).selectAnimalById(a);
        verify(animalRepository, times(0)).deleteAnimal(a);
    }

    @Test
    void changeHibernationStateStart() throws AnimalNotFound, SQLException, JsonProcessingException {
        Animal bear = new Bear();
        String a = bear.getId();
        when(animalRepository.selectAnimalById(a)).thenReturn(bear);
        animalService.changeHibernationState(a, "Start");
        verify(animalRepository, times(1)).selectAnimalById(a);
    }

    @Test
    void changeHibernationStateStop() throws AnimalNotFound, SQLException, JsonProcessingException {
        Animal bear = new Bear();
        String a = bear.getId();
        when(animalRepository.selectAnimalById(a)).thenReturn(bear);
        animalService.changeHibernationState(a, "Stop");
        verify(animalRepository, times(1)).selectAnimalById(a);
    }

    @Test
    void changeHibernationStateBadAction() throws SQLException, JsonProcessingException {
        Animal bear = new Bear();
        String a = bear.getId();
        when(animalRepository.selectAnimalById(a)).thenReturn(bear);
        assertThrows(IllegalArgumentException.class, () -> animalService.changeHibernationState(a, "True"), "Bad action argument");
        verify(animalRepository, times(1)).selectAnimalById(a);
    }

    @Test
    void changeHibernationStateIncorrectAnimal() throws SQLException, JsonProcessingException {
        Animal zebra = new Zebra();
        String a = zebra.getId();
        when(animalRepository.selectAnimalById(a)).thenReturn(zebra);
        assertThrows(AnimalNotFound.class, () -> animalService.changeHibernationState(a, "Start"), "Provided animal is not bear");
        verify(animalRepository, times(1)).selectAnimalById(a);
    }

    @Test
    void giveWaterToAnimalById() throws SQLException, JsonProcessingException {
        Animal animal = new Animal();
        String a = animal.getId();
        when(animalRepository.selectAnimalById(a)).thenReturn(animal);
        assertTrue(animalService.giveWaterToAnimalById(a));
        verify(animalRepository, times(1)).selectAnimalById(a);
    }

    @Test
    void sendToVet() throws SQLException, JsonProcessingException, EmployeeNotFound {
        Animal animal = new Animal();
        Vet vet = new Vet();
        when(employeeRepository.selectEmployeeByPosition(Vet.class.getSimpleName())).thenReturn(vet);
        animalService.sendToVet(animal);
        assertTrue(animal.getLastVetVisit() > 0);
        verify(employeeRepository, times(1)).selectEmployeeByPosition(Vet.class.getSimpleName());
    }

    @Test
    void SendToVetNegative() throws SQLException, JsonProcessingException {
        Animal animal = new Animal();
        when(employeeRepository.selectEmployeeByPosition(Vet.class.getSimpleName())).thenReturn(null);
        assertThrows(EmployeeNotFound.class, () -> animalService.sendToVet(animal), "Cannot find Vet employee");
        assertEquals(animal.getLastVetVisit(), 0);
        verify(employeeRepository, times(1)).selectEmployeeByPosition(Vet.class.getSimpleName());
    }

    @Test
    void addAnimalToEnclosure() {
    }

    @Test
    void feedAnimalNegative() throws SQLException, JsonProcessingException, AnimalDoesNotExist, EmployeeNotFound {
        Animal zebra = new Zebra("Po ", 4, "Africa", 15, 45, 200);
        AnimalExpert animalExpert = new AnimalExpert();
        when(employeeRepository.selectEmployeeByPosition(AnimalExpert.class.getSimpleName())).thenReturn(animalExpert);
        assertTrue(animalService.feedAnimal(zebra));
        verify(employeeRepository, times(1)).selectEmployeeByPosition(AnimalExpert.class.getSimpleName());
    }

    @Test
    void feedAnimal() throws SQLException, JsonProcessingException {
        Animal zebra = new Zebra();
        when(employeeRepository.selectEmployeeByPosition(AnimalExpert.class.getSimpleName())).thenReturn(null);
        assertThrows(EmployeeNotFound.class, () -> animalService.feedAnimal(zebra), "Cannot find an AnimalExpert employee");
        verify(employeeRepository, times(1)).selectEmployeeByPosition(AnimalExpert.class.getSimpleName());
    }

    @Test
    void initialize() throws SQLException, JsonProcessingException {
        animalService.initialize();
        verify(animalRepository, times(1)).initialize();
    }

    @Test
    void getInstance() {
        assertNotNull(AnimalServiceImpl.getInstance());
    }
}
