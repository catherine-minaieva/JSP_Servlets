package ua.goit.controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello! This is Project Management System";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
    }
}