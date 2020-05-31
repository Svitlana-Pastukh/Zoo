package org.park.zoo.controller;

import org.park.zoo.animals.Animal;
import org.park.zoo.animals.exceptions.AnimalDoesNotExist;
import org.park.zoo.animals.exceptions.AnimalNotFound;
import org.park.zoo.services.AnimalService;
import org.park.zoo.services.AnimalServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/animal/hibernation")
public class HibernatingServlet extends HttpServlet {


    private final AnimalService service;

    public HibernatingServlet(AnimalService service) {
        this.service = service;
    }

    public HibernatingServlet() {
        this.service = AnimalServiceImpl.getInstance();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String id = req.getParameter("id");

            String action = req.getParameter("action");
            if (action.equalsIgnoreCase("start")) {
                service.startHibernating(id);
            } else if (action.equalsIgnoreCase("stop")) {
                service.stopHibernating(id);
            } else throw new AnimalDoesNotExist("inappropriate animal");


        } catch (SQLException | AnimalDoesNotExist throwables) {
            throwables.printStackTrace();
        }

    }

}
