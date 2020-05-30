package org.park.zoo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.park.zoo.animals.Animal;
import org.park.zoo.animals.exceptions.AnimalNotFound;
import org.park.zoo.animals.exceptions.EmployeeNotFound;
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

import static org.park.App.createJson;


@WebServlet("/vet")
public class VetServlet extends HttpServlet {
    private AnimalService service;

    {
        try {
            service = new AnimalServiceImpl();
        } catch (SQLException | JsonProcessingException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            Animal animal = service.selectAnimalById(id);
            service.sendToVet(animal);
            service.updateAnimal(animal);

            String animalJson = createJson(animal);

            resp.setContentType("application/json");

            PrintWriter printWriter = resp.getWriter();
            printWriter.write(animalJson);
            printWriter.close();

        } catch (SQLException | AnimalNotFound | EmployeeNotFound throwables) {
            throwables.printStackTrace();
        }
    }
}