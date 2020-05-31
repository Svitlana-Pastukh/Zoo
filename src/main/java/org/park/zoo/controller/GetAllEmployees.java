package org.park.zoo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.park.zoo.services.AnimalService;
import org.park.zoo.services.AnimalServiceImpl;
import org.park.zoo.services.EmployeeService;
import org.park.zoo.services.EmployeeServiceImpl;
import org.park.zoo.workers.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import static org.park.App.createJson;

@WebServlet("/employees")
public class GetAllEmployees extends HttpServlet {
    private final EmployeeService service;

    public GetAllEmployees(EmployeeService service) {
        this.service = service;
    }

    public GetAllEmployees() {
        this.service = EmployeeServiceImpl.getInstance();
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<Employee> employees = service.selectAllEmployees();

            String employeesJson = createJson(employees);
            resp.setContentType("application/json");
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(employeesJson);
            printWriter.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
