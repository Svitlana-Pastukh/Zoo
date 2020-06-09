package org.park.zoo.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Any;
import org.park.zoo.animals.Animal;
import org.park.zoo.animals.Bear;
import org.park.zoo.animals.Giraffe;
import org.park.zoo.animals.Wolf;
import org.park.zoo.services.EmployeeService;
import org.park.zoo.services.EmployeeServiceImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AnimalRepositoryImplTest {

    Connection connection = mock(Connection.class);
    Statement statement = mock(Statement.class);
    ResultSet resultSet = mock(ResultSet.class);
    AnimalRepository repository = new AnimalRepositoryImpl(connection);

    @BeforeEach
    void prepare() throws SQLException {
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(any())).thenReturn(resultSet);
    }

    @Test
    void createAnimalsTable() throws SQLException {
        String query = "CREATE TABLE animals(id VARCHAR(40) NOT NULL, animal TEXT, PRIMARY KEY (id));";
        repository.createAnimalsTable();
        verify(connection,times(1)).createStatement();
        verify(statement, times(1)).execute(query);
    }

    @Test
    void insertAnimalsOneAnimal() throws SQLException, JsonProcessingException {
        List<Animal> l = new ArrayList<>();
        Giraffe giraffe = new Giraffe("ca35c178-10e2-42a5-9cf2-23bf272892ec", "Tim", 1, "Africa", 15, 50, 800);
        l.add(giraffe);
        String s = "MERGE INTO animals KEY (id) VALUES ('ca35c178-10e2-42a5-9cf2-23bf272892ec', " +
                "'{\"@type\":\"Giraffe\",\"id\":\"ca35c178-10e2-42a5-9cf2-23bf272892ec\"," +
                "\"name\":\"Tim\",\"age\":1,\"country\":\"Africa\",\"minTemperature\":15," +
                "\"maxTemperature\":50,\"weight\":800,\"lastVetVisit\":0}')";
        repository.insertAnimals(l);

        verify(statement, times(1)).execute(s);
    }

    @Test
    void insertAnimals() throws SQLException, JsonProcessingException {
        List<Animal> l = new ArrayList<>();
        Giraffe giraffe = new Giraffe("ca35c178-10e2-42a5-9cf2-23bf272892ec", "Tim", 1, "Africa", 15, 50, 800);
        Wolf wolf = new Wolf("86192991-ee6e-4aed-a593-8cce9e21ea61", "Moon", 2, "North America", -10, 25, 50, "Gray");
        Bear bear = new Bear("a4d49700-b72d-42b3-bce7-28d9bb80d25b", "Fred", 5, "USA", -10, 25, 400, "Black");
        l.add(bear);
        l.add(giraffe);
        l.add(wolf);

        String b = "MERGE INTO animals KEY (id) VALUES ('a4d49700-b72d-42b3-bce7-28d9bb80d25b', " +
                "'{\"@type\":\"Bear\",\"id\":\"a4d49700-b72d-42b3-bce7-28d9bb80d25b\"," +
                "\"name\":\"Fred\",\"age\":5,\"country\":\"USA\",\"minTemperature\":-10," +
                "\"maxTemperature\":25,\"weight\":400,\"lastVetVisit\":0,\"color\":\"Black\",\"hibernating\":false}')";

        String v = "MERGE INTO animals KEY (id) VALUES ('ca35c178-10e2-42a5-9cf2-23bf272892ec', " +
                "'{\"@type\":\"Giraffe\",\"id\":\"ca35c178-10e2-42a5-9cf2-23bf272892ec\"," +
                "\"name\":\"Tim\",\"age\":1,\"country\":\"Africa\",\"minTemperature\":15," +
                "\"maxTemperature\":50,\"weight\":800,\"lastVetVisit\":0}')";

        String w = "MERGE INTO animals KEY (id) VALUES ('86192991-ee6e-4aed-a593-8cce9e21ea61'," +
                " '{\"@type\":\"Wolf\",\"id\":\"86192991-ee6e-4aed-a593-8cce9e21ea61\"," +
                "\"name\":\"Moon\",\"age\":2,\"country\":\"North America\",\"minTemperature\":-10,\"" +
                "maxTemperature\":25,\"weight\":50,\"lastVetVisit\":0,\"color\":\"Gray\"}')";

        repository.insertAnimals(l);
        verify(statement, times(1)).execute(b);
        verify(statement, times(1)).execute(v);
        verify(statement, times(1)).execute(w);
    }

    @Test
    void selectAllAnimals() throws SQLException, JsonProcessingException {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Bear("a4d49700-b72d-42b3-bce7-28d9bb80d25b", "Fred", 5, "USA", -10, 25, 400, "Black"));
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString(1)).thenReturn(
                "{\"@type\":\"Bear\",\"id\":\"a4d49700-b72d-42b3-bce7-28d9bb80d25b\"," +
                        "\"name\":\"Fred\",\"age\":5,\"country\":\"USA\",\"minTemperature\":-10," +
                        "\"maxTemperature\":25,\"weight\":400,\"lastVetVisit\":0,\"color\":\"Black\",\"hibernating\":false})");
        List<Animal> animals1 = repository.selectAllAnimals();
        assertEquals(animals, animals1);
        verify(statement, times(1)).executeQuery(any());
        verify(resultSet, times(2)).next();
        verify(resultSet, times(1)).getString(1);
    }

    @Test
    void selectAnimalById() throws SQLException, JsonProcessingException {
        String id = "a4d49700-b72d-42b3-bce7-28d9bb80d25b";
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(1)).thenReturn(
                "{\"@type\":\"Bear\",\"id\":\"a4d49700-b72d-42b3-bce7-28d9bb80d25b\"," +
                        "\"name\":\"Fred\",\"age\":5,\"country\":\"USA\",\"minTemperature\":-10," +
                        "\"maxTemperature\":25,\"weight\":400,\"lastVetVisit\":0,\"color\":\"Black\",\"hibernating\":false})");
        Animal animal = repository.selectAnimalById("a4d49700-b72d-42b3-bce7-28d9bb80d25b");
        assertEquals(id, animal.getId());
        verify(statement, times(1)).executeQuery(any());
        verify(resultSet, times(1)).next();
        verify(resultSet, times(1)).getString(1);
    }

    @Test
    void insertAnimal() throws SQLException, JsonProcessingException {
        Giraffe giraffe = new Giraffe("ca35c178-10e2-42a5-9cf2-23bf272892ec", "Tim", 1, "Africa", 15, 50, 800);
        String s = "MERGE INTO animals KEY (id) VALUES ('ca35c178-10e2-42a5-9cf2-23bf272892ec', " +
                "'{\"@type\":\"Giraffe\",\"id\":\"ca35c178-10e2-42a5-9cf2-23bf272892ec\"," +
                "\"name\":\"Tim\",\"age\":1,\"country\":\"Africa\",\"minTemperature\":15," +
                "\"maxTemperature\":50,\"weight\":800,\"lastVetVisit\":0}')";
        assertEquals(repository.insertAnimal(giraffe), giraffe);
        verify(statement, times(1)).execute(s);
        verify(connection, times(1)).createStatement();
    }

    @Test
    void deleteAnimal() throws SQLException, JsonProcessingException {
        Giraffe giraffe = new Giraffe("ca35c178-10e2-42a5-9cf2-23bf272892ec", "Tim", 1, "Africa", 15, 50, 800);
        String id = "ca35c178-10e2-42a5-9cf2-23bf272892ec";
        assertEquals(id, giraffe.getId());
        repository.deleteAnimal(id);
        verify(connection, times(1)).createStatement();
        verify(statement, times(1)).execute(any());
    }
    @Test
    void initialize() throws SQLException, JsonProcessingException {
        repository.initialize();
        verify(connection, times(2)).createStatement();
        verify(statement,times(6)).execute(any());
    }
}