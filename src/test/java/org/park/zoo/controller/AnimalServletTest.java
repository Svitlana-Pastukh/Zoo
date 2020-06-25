package org.park.zoo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.park.zoo.animals.Animal;
import org.park.zoo.animals.Bear;
import org.park.zoo.animals.exceptions.AnimalNotFound;
import org.park.zoo.services.AnimalService;
import org.park.zoo.services.AnimalServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.park.MapperUtil.createJson;

class AnimalServletTest {
    AnimalService animalService = mock(AnimalServiceImpl.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    AnimalServlet animalServlet = new AnimalServlet(animalService);

    @Test
    void doGet() throws AnimalNotFound, SQLException, IOException {
        String id = "ca35c178-10e2-42a5-9cf2-23bf272892ec";
        Bear bear = new Bear("Fred", 5, "USA", -10, 25, 400, "Black");
        when(request.getParameter("id")).thenReturn(id);
        when(animalService.selectAnimalById(id)).thenReturn(bear);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        animalServlet.doGet(request, response);
        assertEquals(createJson(bear), stringWriter.toString());

        verify(request, times(1)).getParameter("id");
        verify(animalService, times(1)).selectAnimalById(id);
    }

    @Test
    void doDelete() {
    }

    @Test
    void doPost() {
    }
}