package org.park.zoo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.zoo.animals.Animal;
import org.park.zoo.animals.Bear;
import org.park.zoo.animals.exceptions.EmployeeNotFound;
import org.park.zoo.repositories.AnimalRepository;
import org.park.zoo.repositories.AnimalRepositoryImpl;
import org.park.zoo.repositories.EmployeeRepository;
import org.park.zoo.repositories.EmployeeRepositoryImpl;
import org.park.zoo.workers.Employee;
import org.park.zoo.workers.Vet;

import java.sql.SQLException;
import java.util.List;

public class AnimalServiceImpl implements AnimalService {

    private static final Logger logger = LogManager.getLogger(AnimalServiceImpl.class);

    AnimalRepository animalRepository = new AnimalRepositoryImpl();

    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();


    @Override
    public Animal createAnimal(String animalJson) {
        return null;
    }

    @Override
    public List<Animal> selectAllAnimals() throws SQLException, JsonProcessingException {
        return null;
    }

    @Override
    public Animal selectAnimalById(String id) throws SQLException, JsonProcessingException {
        return null;
    }

    @Override
    public void updateAnimal(Animal animal) throws SQLException, JsonProcessingException {

    }

    @Override
    public void deleteAnimal(String id) throws SQLException, JsonProcessingException {

    }

    @Override
    public void startHibernate() throws SQLException, JsonProcessingException {
        List<Animal> animals = animalRepository.selectAllAnimals();
        for (Animal a : animals) {
            if (a instanceof Bear) {
                System.out.println("Bear " + a.getName() + " started hibernating");
            } else {
                throw new IllegalArgumentException("Provided animal is not bear");
            }
        }
    }

    @Override
    public void stopHibernate() throws SQLException, JsonProcessingException {
        List<Animal> animals = animalRepository.selectAllAnimals();
        for (Animal a : animals) {
            if (a instanceof Bear) {
                ((Bear) a).stopHibernate();
                logger.info("Bear " + a.getName() + " stop hibernating");
            } else {
                throw new IllegalArgumentException("Provided animal is not bear");
            }
        }

    }

    @Override
    public void drinkWater() {

    }

    @Override
    public void sendToVet(Animal animal) throws SQLException, JsonProcessingException, EmployeeNotFound {
        List<Employee> employees = employeeRepository.selectAllEmployees();
        for (Employee a : employees) {
            if (a instanceof Vet) {
                ((Vet) a).checkAnimal(animal);
                logger.info(a.getName() + " checked animal: " + animal.getName());
            } else {
                throw new EmployeeNotFound("Cannot find a Vet employee");
            }
        }


    }

    @Override
    public void addAnimal(Animal animal) {

    }

    @Override
    public void feedAnimal(Animal animal) {


    }
}

