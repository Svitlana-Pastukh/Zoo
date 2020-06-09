package org.park.zoo.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.park.zoo.repositories.utils.ConnectionSingleton;
import org.park.zoo.workers.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.park.MapperUtil.createEmployeeFromJson;
import static org.park.MapperUtil.createJson;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final Connection connection;

    public EmployeeRepositoryImpl() {
        this.connection = ConnectionSingleton.getConnection();
    }

    public EmployeeRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createEmployeesTable() throws SQLException {

        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE employees(id VARCHAR(40) NOT NULL, employee TEXT, employee_type TEXT, PRIMARY KEY (id));");
    }

    @Override
    public void insertEmployees(List<Employee> list) throws SQLException, JsonProcessingException {
        Statement statement = connection.createStatement();
        for (Employee employee : list) {

            String json = createJson(employee);
            String b = String.format("MERGE INTO employees KEY (id) VALUES ('%s', '%s', '%s')", employee.getEmployeeId(), json, employee.getClass().getSimpleName());
            statement.execute(b);
        }
    }

    @Override
    public List<Employee> selectAllEmployees() throws SQLException, JsonProcessingException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT employee FROM employees");
        List<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            employees.add(createEmployeeFromJson(resultSet.getString(1)));
        }
        return employees;
    }

    @Override
    public Employee selectEmployeeById(String id) throws SQLException, JsonProcessingException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT employee FROM employees WHERE id='" + id + "';");
        if (resultSet.next()) {
            return createEmployeeFromJson(resultSet.getString(1));
        } else {
            return null;
        }
    }

    @Override
    public Employee insertEmployee(Employee employee) throws SQLException, JsonProcessingException {
        Statement statement = connection.createStatement();
        statement.execute(String.format("MERGE INTO employees KEY (id) VALUES ('%s', '%s', '%s')", employee.getEmployeeId(), createJson(employee), employee.getClass().getSimpleName()));
        return employee;
    }

    @Override
    public void deleteEmployee(String id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM employees WHERE id='" + id + "';");
    }

    @Override
    public Employee selectEmployeeByPosition(String employeeType) throws SQLException, JsonProcessingException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT employee FROM employees WHERE employee_type='" + employeeType + "';");
        if (resultSet.next()) {
            return createEmployeeFromJson(resultSet.getString(2));
        } else {
            return null;
        }
    }

    public void initialize() throws SQLException, JsonProcessingException {

        List<Employee> employees = new ArrayList<>();
        Director director = new Director("JJ", "Nest", 45, 5000);
        Vet vet = new Vet("Samm", "White", 27, 4500);
        Accountant accountant = new Accountant("Anna", "Gray", 22, 2500);
        AnimalExpert animalExpert = new AnimalExpert("Bob", "Ice", 32, 3750);
        employees.add(director);
        employees.add(vet);
        employees.add(accountant);
        employees.add(animalExpert);

        createEmployeesTable();
        insertEmployees(employees);

    }
}

