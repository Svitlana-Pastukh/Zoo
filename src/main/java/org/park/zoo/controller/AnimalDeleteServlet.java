package org.park.zoo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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

@WebServlet("/animal/delete")
public class AnimalDeleteServlet extends HttpServlet {

    private final AnimalService service;

    public AnimalDeleteServlet(AnimalService service) {
        this.service = service;
    }

    public AnimalDeleteServlet() {
        this.service = AnimalServiceImpl.getInstance();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp){

        PrintWriter printWriter;
        try {

            String id = req.getParameter("id");
            service.deleteAnimal(id);

            printWriter = resp.getWriter();
            printWriter.write("Animal deleted");
            printWriter.close();
        } catch (AnimalNotFound exception) {

            resp.setStatus(404);

        } catch (SQLException | IOException exception){
            exception.printStackTrace();
        }
    }
}
