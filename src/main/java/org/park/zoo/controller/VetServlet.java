package org.park.zoo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.sql.SQLException;

import static org.park.App.createJson;


@WebServlet("/vet/check")
public class VetServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(VetServlet.class);

    private final AnimalService service;

    public VetServlet(AnimalService service) {
        this.service = service;
    }

    public VetServlet() {
        this.service = AnimalServiceImpl.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        try {
            String id = req.getParameter("id");
            Animal animal = service.selectAnimalById(id);
            service.sendToVet(animal);
            service.updateAnimal(animal);
            ServletUtils.setBody(resp, createJson(animal));

        } catch (AnimalNotFound exception) {
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
