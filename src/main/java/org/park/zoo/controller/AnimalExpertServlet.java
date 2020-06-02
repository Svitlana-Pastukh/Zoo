package org.park.zoo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.zoo.animals.Animal;
import org.park.zoo.animals.exceptions.AnimalDoesNotExist;
import org.park.zoo.animals.exceptions.AnimalNotFound;
import org.park.zoo.animals.exceptions.EmployeeNotFound;
import org.park.zoo.services.AnimalService;
import org.park.zoo.services.AnimalServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.park.App.createJson;

@WebServlet("/animal/feed")
public class AnimalExpertServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(AnimalExpertServlet.class);

    private final AnimalService service;

    public AnimalExpertServlet(AnimalService service) {
        this.service = service;
    }

    public AnimalExpertServlet() {
        this.service = AnimalServiceImpl.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            Animal animal = service.selectAnimalById(id);
            service.feedAnimal(animal);
            service.updateAnimal(animal);
            ServletUtils.setBody(resp, createJson(animal));
        } catch (AnimalNotFound | AnimalDoesNotExist exception) {
            resp.setStatus(404);
            ServletUtils.setBody(resp, "Animal not found");
        } catch (EmployeeNotFound exception) {
            resp.setStatus(404);
            ServletUtils.setBody(resp, "Employee not found ");
        } catch (SQLException | IOException exception) {
            logger.error(exception);
        }
    }
}

