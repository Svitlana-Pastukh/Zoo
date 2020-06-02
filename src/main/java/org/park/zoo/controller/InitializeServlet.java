package org.park.zoo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.zoo.services.AnimalService;
import org.park.zoo.services.AnimalServiceImpl;
import org.park.zoo.services.EmployeeService;
import org.park.zoo.services.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/initialize")
public class InitializeServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(InitializeServlet.class);
    private final AnimalService animalService = AnimalServiceImpl.getInstance();
    private final EmployeeService employeeService = EmployeeServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            animalService.initialize();
            employeeService.initialize();
            ServletUtils.setBody(resp, "initialize Animal done!!! \n initialize Employee done!!!");
        } catch (SQLException exception) {
            logger.error(exception);
        }
    }
}
