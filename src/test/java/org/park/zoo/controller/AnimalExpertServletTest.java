package org.park.zoo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.park.zoo.animals.Animal;
import org.park.zoo.animals.Giraffe;
import org.park.zoo.animals.exceptions.AnimalDoesNotExist;
import org.park.zoo.animals.exceptions.AnimalNotFound;
import org.park.zoo.animals.exceptions.EmployeeNotFound;
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

class AnimalExpertServletTest {
    AnimalService animalService = mock(AnimalServiceImpl.class);
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    AnimalExpertServlet servlet = new AnimalExpertServlet(animalService);


    @Test
    public void doPost() throws AnimalNotFound, SQLException, IOException, AnimalDoesNotExist, EmployeeNotFound {
        String id = "ca35c178-10e2-42a5-9cf2-23bf272892ec";
        Animal giraffe = new Giraffe("ca35c178-10e2-42a5-9cf2-23bf272892ec", "Tim", 1, "Africa", 15, 50, 800);
        when(request.getParameter("id")).thenReturn(id);
        when(animalService.selectAnimalById(id)).thenReturn(giraffe);
        when(animalService.feedAnimal(giraffe)).thenReturn(true);
        when(animalService.updateAnimal(giraffe)).thenReturn(giraffe);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertEquals(createJson(giraffe), stringWriter.toString());
        verify(request, times(1)).getParameter("id");
        verify(animalService, times(1)).selectAnimalById(id);
        verify(animalService, times(1)).feedAnimal(giraffe);
        verify(animalService, times(1)).updateAnimal(giraffe);
        verify(response, times(1)).getWriter();
        verify(response, times(1)).setContentType("application/json");
        verify(response, times(1)).setCharacterEncoding("UTF-8");
    }

    @Test
    public void doPostExceptionNotFoundAnimal() throws SQLException, IOException, AnimalNotFound {
        String id = "ca35c17";
        when(request.getParameter("id")).thenReturn(id);
        when(animalService.selectAnimalById(id)).thenThrow(AnimalNotFound.class);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertEquals("Animal not found", stringWriter.toString());
        verify(request, times(1)).getParameter("id");
        verify(animalService, times(1)).selectAnimalById(id);
        verify(response, times(1)).setStatus(404);
        verify(response, times(1)).getWriter();
        verify(response, times(1)).setContentType("application/json");
        verify(response, times(1)).setCharacterEncoding("UTF-8");
    }

    @Test
    public void doPostExceptionNotFoundEmployee() throws AnimalNotFound, SQLException, IOException, AnimalDoesNotExist, EmployeeNotFound {
        String id = "ca35c178-10e2-42a5-9cf2-23bf272892ec";
        Animal giraffe = null;
        when(request.getParameter("id")).thenReturn(id);
        when(animalService.selectAnimalById(id)).thenReturn(giraffe);
        when(animalService.feedAnimal(giraffe)).thenThrow(EmployeeNotFound.class);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        assertEquals("Employee not found", stringWriter.toString());
        verify(response, times(1)).setStatus(404);
        verify(response, times(1)).getWriter();
        verify(response, times(1)).setContentType("application/json");
        verify(response, times(1)).setCharacterEncoding("UTF-8");
    }

}
