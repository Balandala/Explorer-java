package org.example;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.User;
import org.example.model.Users;
import org.example.services.DatabaseService;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("username");
        session.removeAttribute("password");
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password =req.getParameter("password");
        System.out.printf("Данные о логине успшно получены!\nusername: %s\npassword: %s\n", username, password);

        if (!DatabaseService.IsVerified(username, password)){
            req.setAttribute("status","Неверный логин или пароль");
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("username", username);
        session.setAttribute("password", password);

        System.out.printf("Данные о логине успшно отправлены!\nusername: %s\npassword: %s\n", session.getAttribute("username"), session.getAttribute("password"));

        resp.sendRedirect("/files/");
    }
}
