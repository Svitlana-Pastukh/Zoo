package org.park.zoo.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.park.zoo.workers.Director;
import org.park.zoo.workers.Employee;
import org.park.zoo.workers.Vet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeRepositoryImplTest {
    Connection connection = mock(Connection.class);
    Statement statement = mock(Statement.class);
    ResultSet resultSet = mock(ResultSet.class);
    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl(connection);

    @BeforeEach
    void prepare() throws SQLException {
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(any())).thenReturn(resultSet);
    }

    @Test
    void createEmployeesTable() throws SQLException {
        String a = "CREATE TABLE employees(id VARCHAR(40) NOT NULL, employee TEXT, employee_type TEXT, PRIMARY KEY (id));";
        employeeRepository.createEmployeesTable();
        verify(connection, times(1)).createStatement();
        verify(statement, times(1)).execute(a);
    }

    @Test
    void insertEmployeesOneEmployee() throws SQLException, JsonProcessingException {
        List<Employee> list = new ArrayList<>();
        Vet vet = new Vet("ed0f6104-d986-4276-9412-bfe682b2a7ea", "Samm", "White", 27, 4500);
        list.add(vet);
        String a = "MERGE INTO employees KEY (id) VALUES ('ed0f6104-d986-4276-9412-bfe682b2a7ea'," +
                " '{\"@type\":\"Vet\",\"employeeId\":\"ed0f6104-d986-4276-9412-bfe682b2a7ea\"," +
                "\"name\":\"Samm\",\"surname\":\"White\",\"age\":27,\"salary\":4500,\"workedHours\":0}', 'Vet')";

        employeeRepository.insertEmployees(list);
        verify(statement, times(1)).execute(a);
    }

    @Test
    void insertEmployees() throws SQLException, JsonProcessingException {
        List<Employee> list = new ArrayList<>();
        Vet vet = new Vet("ed0f6104-d986-4276-9412-bfe682b2a7ea", "Samm", "White", 27, 4500);
        Director director = new Director("b6f2d317-a7f6-4c83-a59f-1b95b7f6db03", "JJ", "Nest", 45, 5000);
        list.add(vet);
        list.add(director);
        String v = "MERGE INTO employees KEY (id) VALUES ('ed0f6104-d986-4276-9412-bfe682b2a7ea'," +
                " '{\"@type\":\"Vet\",\"employeeId\":\"ed0f6104-d986-4276-9412-bfe682b2a7ea\"," +
                "\"name\":\"Samm\",\"surname\":\"White\",\"age\":27,\"salary\":4500,\"workedHours\":0}', 'Vet')";
        String d = "MERGE INTO employees KEY (id) VALUES ('b6f2d317-a7f6-4c83-a59f-1b95b7f6db03', " +
                "'{\"@type\":\"Director\",\"employeeId\":\"b6f2d317-a7f6-4c83-a59f-1b95b7f6db03\"," +
                "\"name\":\"JJ\",\"surname\":\"Nest\",\"age\":45,\"salary\":5000,\"workedHours\":0}', 'Director')";
        employeeRepository.insertEmployees(list);
        verify(statement, times(1)).execute(v);
        verify(statement, times(1)).execute(d);
    }

    @Test
    void selectAllEmployees() throws SQLException, JsonProcessingException {
        List<Employee> list = new ArrayList<>();
        list.add(new Vet("ed0f6104-d986-4276-9412-bfe682b2a7ea", "Samm", "White", 27, 4500));
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString(1)).thenReturn("{\"@type\":\"Vet\",\"employeeId\":\"ed0f6104-d986-4276-9412-bfe682b2a7ea\"," +
                "\"name\":\"Samm\",\"surname\":\"White\",\"age\":27,\"salary\":4500,\"workedHours\":0}', 'Vet)");
        List<Employee> list1 = employeeRepository.selectAllEmployees();
        assertEquals(list, list1);
        verify(statement, times(1)).executeQuery(any());
        verify(resultSet, times(2)).next();
        verify(resultSet, times(1)).getString(1);
    }

    @Test
    void selectEmployeeById() throws SQLException, JsonProcessingException {
        String id = "ed0f6104-d986-4276-9412-bfe682b2a7ea";
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(1)).thenReturn("{\"@type\":\"Vet\",\"employeeId\":\"ed0f6104-d986-4276-9412-bfe682b2a7ea\"," +
                "\"name\":\"Samm\",\"surname\":\"White\",\"age\":27,\"salary\":4500,\"workedHours\":0}");
        Employee employee = employeeRepository.selectEmployeeById(id);
        assertEquals(id, employee.getEmployeeId());
        verify(statement, times(1)).executeQuery(any());
        verify(resultSet, times(1)).next();
        verify(resultSet, times(1)).getString(1);
    }

    @Test
    void selectEmployeeByIdReturnNull() throws SQLException, JsonProcessingException {
        String id = "ed0f6104-d986-4276-9412-bfe682b2a7ea";
        when(resultSet.next()).thenReturn(false);
        employeeRepository.selectEmployeeById(id);
        verify(resultSet, times(1)).next();
        verify(resultSet, times(0)).getString(1);
    }

    @Test
    void insertEmployee() throws SQLException, JsonProcessingException {
        Vet vet = new Vet("ed0f6104-d986-4276-9412-bfe682b2a7ea", "Samm", "White", 27, 4500);
        String v = "MERGE INTO employees KEY (id) VALUES ('ed0f6104-d986-4276-9412-bfe682b2a7ea'," +
                " '{\"@type\":\"Vet\",\"employeeId\":\"ed0f6104-d986-4276-9412-bfe682b2a7ea\"," +
                "\"name\":\"Samm\",\"surname\":\"White\",\"age\":27,\"salary\":4500,\"workedHours\":0}', 'Vet')";
        assertEquals(employeeRepository.insertEmployee(vet), vet);
        verify(statement, times(1)).execute(v);
        verify(connection, times(1)).createStatement();
    }

    @Test
    void deleteEmployee() throws SQLException, JsonProcessingException {
        Vet vet = new Vet("ed0f6104-d986-4276-9412-bfe682b2a7ea", "Samm", "White", 27, 4500);
        String id = "ed0f6104-d986-4276-9412-bfe682b2a7ea";
        assertEquals(id, vet.getEmployeeId());
        employeeRepository.deleteEmployee(id);
        verify(connection, times(1)).createStatement();
        verify(statement, times(1)).execute(any());
    }

    @Test
    void selectEmployeeByPosition() throws SQLException, JsonProcessingException {
        Vet vet = new Vet("ed0f6104-d986-4276-9412-bfe682b2a7ea", "Samm", "White", 27, 4500);
        String employeeType = "Vet";
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(2)).thenReturn("{\"@type\":\"Vet\",\"employeeId\":\"ed0f6104-d986-4276-9412-bfe682b2a7ea\"," +
                "\"name\":\"Samm\",\"surname\":\"White\",\"age\":27,\"salary\":4500,\"workedHours\":0}");
        Employee employee = employeeRepository.selectEmployeeByPosition(employeeType);
        assertEquals(employee, vet);
        verify(statement, times(1)).executeQuery(any());
        verify(resultSet, times(1)).next();
        verify(resultSet, times(1)).getString(2);
    }

    @Test
    void selectEmployeeByPositionReturnNull() throws SQLException, JsonProcessingException {
        String employeeType = "Vet";
        when(resultSet.next()).thenReturn(false);
        employeeRepository.selectEmployeeByPosition(employeeType);
        verify(resultSet, times(1)).next();
        verify(resultSet, times(0)).getString(2);

    }

    @Test
    void initialize() throws SQLException, JsonProcessingException {
        employeeRepository.initialize();
        verify(connection, times(2)).createStatement();
        verify(statement, times(5)).execute(any());
    }
}