package org.park.zoo.controller;

import org.park.zoo.animals.exceptions.EmployeeNotFound;
import org.park.zoo.services.EmployeeService;
import org.park.zoo.services.EmployeeServiceImpl;
import org.park.zoo.workers.Employee;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.park.App.createJson;

@WebServlet("/employee")
public class GetEmployeeByIdServlet extends HttpServlet {
    private final EmployeeService service;

    public GetEmployeeByIdServlet(EmployeeService service) {
        this.service = service;
    }

    public GetEmployeeByIdServlet() {
        this.service = EmployeeServiceImpl.getInstance();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");

            Employee employee = service.selectEmployeeById(id);

            String employeeJson = createJson(employee);

            resp.setContentType("application/json");
            ServletUtils.setBody(resp, employeeJson);

        } catch (EmployeeNotFound employeeNotFound) {
            resp.setStatus(404);

        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

    }
}
