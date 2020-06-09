package org.park.zoo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import static org.park.MapperUtil.createJson;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(EmployeeServlet.class);

    private final EmployeeService service;

    public EmployeeServlet(EmployeeService service) {
        this.service = service;
    }

    public EmployeeServlet() {
        this.service = EmployeeServiceImpl.getInstance();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            Employee employee = service.selectEmployeeById(id);
            String employeeJson = createJson(employee);
            ServletUtils.setBody(resp, employeeJson);
        } catch (EmployeeNotFound employeeNotFound) {
            resp.setStatus(404);
            ServletUtils.setBody(resp, "Employee not found");
        } catch (SQLException | IOException exception) {
            logger.error(exception);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String body = ServletUtils.getBody(req);
        try {
            service.createEmployee(body);
            resp.setStatus(201);
        } catch (JsonProcessingException e) {
            resp.setStatus(400);
            ServletUtils.setBody(resp, "Bad request body ");
        } catch (SQLException exception) {
            logger.error(exception);
        }
    }
}
