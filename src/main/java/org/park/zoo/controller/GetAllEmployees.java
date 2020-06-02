package org.park.zoo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.zoo.services.EmployeeService;
import org.park.zoo.services.EmployeeServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.park.App.createJson;

@WebServlet("/employees")
public class GetAllEmployees extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(GetAllEmployees.class);

    private final EmployeeService service;

    public GetAllEmployees(EmployeeService service) {
        this.service = service;
    }

    public GetAllEmployees() {
        this.service = EmployeeServiceImpl.getInstance();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            ServletUtils.setBody(resp, createJson(service.selectAllEmployees()));
        } catch (SQLException exception) {
            logger.error(exception);
        }
    }
}
