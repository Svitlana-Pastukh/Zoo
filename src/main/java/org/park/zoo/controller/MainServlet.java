//package org.park.zoo.controller;
//
//import org.park.App;
//import org.park.zoo.animals.Animal;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.SQLException;
//import java.util.List;
//
//import static org.park.App.createJson;
//
//@WebServlet("/animals")
//public class MainServlet extends HttpServlet {
//
//    private final ZooRepository repository;
//
//    public MainServlet() {
//        repository = App.getZooRepository();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        try {
//            List<Animal> animals = repository.selectAllAnimals();
//            String animalsJson = createJson(animals);
//            resp.setContentType("application/json");
//            PrintWriter printWriter = resp.getWriter();
//            printWriter.write(animalsJson);
//            printWriter.close();
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//}
