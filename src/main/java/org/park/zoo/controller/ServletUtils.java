package org.park.zoo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class ServletUtils {
    private static final Logger logger = LogManager.getLogger(ServletUtils.class);

    static void setBody(HttpServletResponse response, String body) {

        try (PrintWriter printWriter = response.getWriter()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            printWriter.write(body);
            printWriter.flush();
        } catch (IOException e) {
            logger.error("Could not add body to response", e);
        }
    }

    static String getBody(HttpServletRequest request) {
        try {
            return request.getReader().lines().collect(Collectors.joining());
        } catch (IOException e) {
            logger.error("Could not add body from request", e);
        }
        return null;
    }

}
