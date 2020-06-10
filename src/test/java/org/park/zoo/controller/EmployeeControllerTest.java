package org.park.zoo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.park.zoo.controller.ServletUtils;
import org.park.zoo.services.AnimalService;
import org.park.zoo.services.AnimalServiceImpl;
import org.park.zoo.services.EmployeeService;
import org.park.zoo.services.EmployeeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.park.MapperUtil.createJson;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {
    private final EmployeeService service = EmployeeServiceImpl.getInstance();

    public static void main(String[] args) {
        HttpServletRequest a = mock(HttpServletRequest.class);
        HttpServletResponse s = mock(HttpServletResponse.class);
    }
}



