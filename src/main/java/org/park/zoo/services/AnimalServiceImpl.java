package org.park.zoo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.zoo.animals.Animal;
import org.park.zoo.animals.Bear;
import org.park.zoo.animals.exceptions.AnimalDoesNotExist;
import org.park.zoo.animals.exceptions.AnimalNotFound;
import org.park.zoo.animals.exceptions.EmployeeNotFound;
import org.park.zoo.repositories.AnimalRepository;
import org.park.zoo.repositories.AnimalRepositoryImpl;
import org.park.zoo.repositories.EmployeeRepository;
import org.park.zoo.repositories.EmployeeRepositoryImpl;
import org.park.zoo.workers.AnimalExpert;
import org.park.zoo.workers.Employee;
import org.park.zoo.workers.Vet;

import java.sql.SQLException;
import java.util.List;

import static org.park.MapperUtil.createAnimalFromJson;

public class AnimalServiceImpl implements AnimalService {

    private static final Logger logger = LogManager.getLogger(AnimalServiceImpl.class);

    private final AnimalRepository animalRepository;

    private final EmployeeRepository employeeRepository;

    private static final AnimalService INSTANCE = new AnimalServiceImpl();

    public AnimalServiceImpl(AnimalRepository animalRepository, EmployeeRepository employeeRepository) {

        this.animalRepository = animalRepository;
        this.employeeRepository = employeeRepository;
    }

    public AnimalServiceImpl() {

        this.animalRepository = new AnimalRepositoryImpl();
        this.employeeRepository = new EmployeeRepositoryImpl();
    }


    @Override
    public Animal createAnimal(String animalJson) throws JsonProcessingException, SQLException {
        Animal animal = createAnimalFromJson(animalJson);
        animalRepository.insertAnimal(animal);
        return animal;
    }

    @Override
    public List<Animal> selectAllAnimals() throws SQLException, JsonProcessingException {
        return animalRepository.selectAllAnimals();
    }

    @Override
    public Animal selectAnimalById(String id) throws SQLException, JsonProcessingException, AnimalNotFound {

        Animal animal = animalRepository.selectAnimalById(id);

        if (animal != null) {
            return animal;
        } else {
            throw new AnimalNotFound("Cannot find animal");
        }
    }

    @Override
    public Animal updateAnimal(Animal animal) throws SQLException, JsonProcessingException {
        return animalRepository.insertAnimal(animal);
    }

    @Override
    public void deleteAnimal(String id) throws SQLException, JsonProcessingException, AnimalNotFound {
        if (id != null && !id.isBlank() && animalRepository.selectAnimalById(id) != null) {
            animalRepository.deleteAnimal(id);
        } else {
            throw new AnimalNotFound("inappropriate id");
        }
    }

    @Override
    public void changeHibernationState(String id, String action) throws SQLException, JsonProcessingException, AnimalNotFound {

        Animal animal = animalRepository.selectAnimalById(id);

        if (animal instanceof Bear && action.equalsIgnoreCase("start")) {
            ((Bear) animal).startHibernate();
            logger.info("Bear " + animal.getName() + " started hibernating");
        } else if (animal instanceof Bear && action.equalsIgnoreCase("stop")) {
            ((Bear) animal).stopHibernate();
            logger.info("Bear " + animal.getName() + " stop hibernating");
        } else if (!action.equalsIgnoreCase("start") && !action.equalsIgnoreCase("stop")) {
            throw new IllegalArgumentException("Bad action argument");
        } else {
            throw new AnimalNotFound("Provided animal is not bear");
        }
    }


    @Override
    public boolean giveWaterToAnimalById(String id) throws SQLException, JsonProcessingException {
        Animal animal = animalRepository.selectAnimalById(id);
        animal.drinkWater();
        return true;
    }

    @Override
    public boolean sendToVet(Animal animal) throws SQLException, JsonProcessingException, EmployeeNotFound {
        Employee vet = employeeRepository.selectEmployeeByPosition(Vet.class.getSimpleName());
        if (vet instanceof Vet) {
            ((Vet) vet).checkAnimal(animal);
            return true;
        } else {
            throw new EmployeeNotFound("Cannot find Vet employee");
        }
    }

    @Override
    public boolean feedAnimal(Animal animal) throws AnimalDoesNotExist, SQLException, JsonProcessingException, EmployeeNotFound {
        Employee animalExpert = employeeRepository.selectEmployeeByPosition(AnimalExpert.class.getSimpleName());
        if (animalExpert instanceof AnimalExpert) {
            ((AnimalExpert) animalExpert).feedAnimal(animal);
            return true;
        } else {
            throw new EmployeeNotFound("Cannot find an AnimalExpert employee");
        }
    }

    @Override
    public void initialize() throws SQLException, JsonProcessingException {
        animalRepository.initialize();
    }

    public static AnimalService getInstance() {
        return INSTANCE;
    }
}





