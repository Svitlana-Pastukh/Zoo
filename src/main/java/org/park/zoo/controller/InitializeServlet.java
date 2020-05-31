package org.park.zoo.controller;

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
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/initialize")
public class InitializeServlet extends HttpServlet {
    private final AnimalService animalService = AnimalServiceImpl.getInstance();
    private final EmployeeService employeeService = EmployeeServiceImpl.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            animalService.initialize();
            employeeService.initialize();
            PrintWriter printWriter = resp.getWriter();
            printWriter.write("initialize Animal done!!!");
            printWriter.write("initialize Employee done!!!");
            printWriter.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
