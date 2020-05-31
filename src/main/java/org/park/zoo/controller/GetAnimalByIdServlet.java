package org.park.zoo.controller;

import org.park.zoo.animals.Animal;
import org.park.zoo.animals.exceptions.AnimalNotFound;
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

@WebServlet("/animal")
public class GetAnimalByIdServlet extends HttpServlet {

    private final AnimalService service;

    public GetAnimalByIdServlet(AnimalService service) {
        this.service = service;
    }

    public GetAnimalByIdServlet() {
        this.service = AnimalServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String id = req.getParameter("id");

            Animal animal = service.selectAnimalById(id);

            String animalJson = createJson(animal);

            resp.setContentType("application/json");

            PrintWriter printWriter = resp.getWriter();
            printWriter.write(animalJson);
            printWriter.close();

        } catch (SQLException | AnimalNotFound throwables) {
            throwables.printStackTrace();
        }
    }

}

