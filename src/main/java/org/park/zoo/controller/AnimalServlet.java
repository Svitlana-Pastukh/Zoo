package org.park.zoo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.zoo.animals.Animal;
import org.park.zoo.animals.exceptions.AnimalNotFound;
import org.park.zoo.services.AnimalService;
import org.park.zoo.services.AnimalServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.park.App.createJson;

@WebServlet("/animal")
public class AnimalServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(AnimalServlet.class);

    private final AnimalService service;

    public AnimalServlet(AnimalService service) {
        this.service = service;
    }

    public AnimalServlet() {
        this.service = AnimalServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            String id = req.getParameter("id");
            Animal animal = service.selectAnimalById(id);
            String animalJson = createJson(animal);
            ServletUtils.setBody(resp, animalJson);
        } catch (SQLException | AnimalNotFound exception) {
            logger.error(exception);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {

        try {
            String id = req.getParameter("id");
            service.deleteAnimal(id);
            ServletUtils.setBody(resp, "Animal delete");
        } catch (AnimalNotFound exception) {
            resp.setStatus(404);
            ServletUtils.setBody(resp, "Animal not found");
        } catch (SQLException | IOException exception) {
            logger.error(exception);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        String body = ServletUtils.getBody(req);
        try {
            service.createAnimal(body);
            resp.setStatus(201);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            resp.setStatus(400);
            ServletUtils.setBody(resp, "Bad request body ");

        } catch (SQLException exception) {
            logger.error(exception);
        }
    }
}


