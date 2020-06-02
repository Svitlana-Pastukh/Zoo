package org.park.zoo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.zoo.services.AnimalService;
import org.park.zoo.services.AnimalServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.park.App.createJson;

@WebServlet("/animals")
public class AnimalsServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(AnimalsServlet.class);

    private final AnimalService service;

    public AnimalsServlet(AnimalService service) {
        this.service = service;
    }

    public AnimalsServlet() {
        this.service = AnimalServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String animalsJson = createJson(service.selectAllAnimals());
            ServletUtils.setBody(resp, animalsJson);
        } catch (SQLException exception) {
            logger.error(exception);
        }
    }
}
