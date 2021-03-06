package org.park.zoo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.park.zoo.animals.exceptions.AnimalNotFound;
import org.park.zoo.services.AnimalService;
import org.park.zoo.services.AnimalServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/animal/hibernation")
public class HibernatingServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(HibernatingServlet.class);
    private final AnimalService service;

    public HibernatingServlet(AnimalService service) {
        this.service = service;
    }

    public HibernatingServlet() {
        this.service = AnimalServiceImpl.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            String action = req.getParameter("action");
            service.changeHibernationState(id, action);
        } catch (AnimalNotFound exception) {
            resp.setStatus(400);
            ServletUtils.setBody(resp, "Bad request");
        } catch (IOException | SQLException exception) {
            logger.error(exception);
        }
    }
}
