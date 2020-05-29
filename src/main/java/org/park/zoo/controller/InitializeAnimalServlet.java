package org.park.zoo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.park.zoo.services.AnimalService;
import org.park.zoo.services.AnimalServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/initialize")
public class InitializeAnimalServlet extends HttpServlet {
    private AnimalService service;

    {
        try {
            service = new AnimalServiceImpl();
        } catch (SQLException | JsonProcessingException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            service.initialize();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
