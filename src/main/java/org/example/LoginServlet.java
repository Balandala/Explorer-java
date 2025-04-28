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

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("login");
        session.removeAttribute("password");
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password =req.getParameter("password");
        System.out.printf("Данные о логине успшно получены!\nlogin: %s\npassword: %s\n", login, password);


        User user = Users.GetUser(login);
        if (user == null || !user.getPassword().equals(password)){
            req.setAttribute("status","Неверный логин или пароль");
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        }

        HttpSession session = req.getSession();
        session.setAttribute("login", login);
        session.setAttribute("password", password);

        System.out.printf("Данные о логине успшно отправлены!\nlogin: %s\npassword: %s\n", session.getAttribute("login"), session.getAttribute("password"));

        resp.sendRedirect("/files/");
    }
}
