package org.park;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.zoo.animals.*;
import org.park.zoo.workers.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static final Logger logger = LogManager.getLogger(App.class);
    private static final ObjectMapper mapper = new ObjectMapper();


    public static String createJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static Animal createAnimalFromJson(String animal) throws JsonProcessingException {
        return mapper.readValue(animal, Animal.class);
    }

    public static Employee createEmployeeFromJson(String employee) throws JsonProcessingException {
        return mapper.readValue(employee, Employee.class);
    }
}