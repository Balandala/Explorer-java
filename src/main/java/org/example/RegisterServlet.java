package org.example;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.Users;
import org.example.services.DatabaseService;

import java.io.IOException;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        if (username.isBlank() || password.isBlank() || email.isBlank()){
            req.setAttribute("status", "Все поля должны быть заполнены");
            RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        if (!DatabaseService.IsExists(username)){
            DatabaseService.CreateNewUser(username, email, password);
            req.setAttribute("status","Пользователь успшно создан!");
            resp.sendRedirect("/files/login");
        }
        else {
            req.setAttribute("status","Пользователь уже существует");
            RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
            dispatcher.forward(req, resp);
        }

    }
}
