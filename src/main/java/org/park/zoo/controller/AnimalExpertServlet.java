package org.park.zoo.controller;

import org.park.App;
import org.park.zoo.animals.Animal;
import org.park.zoo.repositories.ZooRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/animalExpert")

public class AnimalExpertServlet extends HttpServlet {
    private final ZooRepository repository;

    public AnimalExpertServlet() {
        repository = App.getZooRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String id = req.getParameter("id");
            Animal animal = repository.selectAnimalById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

