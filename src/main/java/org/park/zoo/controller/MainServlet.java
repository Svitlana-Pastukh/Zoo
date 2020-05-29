package org.park.zoo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.park.zoo.animals.Animal;
import org.park.zoo.services.AnimalService;
import org.park.zoo.services.AnimalServiceImpl;

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

@WebServlet("/animals")
public class MainServlet extends HttpServlet {

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

        List<Animal> animals = null;
        try {
            animals = service.selectAllAnimals();

            String animalsJson = createJson(animals);
            resp.setContentType("application/json");

            PrintWriter printWriter = resp.getWriter();
            printWriter.write(animalsJson);
            printWriter.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
