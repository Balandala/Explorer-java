package org.example;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.FileDTO;
import org.example.services.ExplorerService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/")
public class ExplorerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        if (session == null || session.getAttribute("username") == null){
            resp.sendRedirect("/files/login");
            return;
        }
        String username = (String) session.getAttribute("username");

        String root = "/home/" + username;
        if (!Files.exists(Path.of(root))){
            Files.createDirectory(Path.of(root));
        }

        String urlPath = req.getParameter("path");
        if (urlPath == null){
            urlPath = "/";
        }

        Path relativePath = Paths.get(urlPath).normalize();
        String parentPath = relativePath.getParent() != null? relativePath.getParent().toString().replace("\\","/") : "/";
        Path path = Paths.get(root, relativePath.toString());


        ArrayList<FileDTO> files = new ArrayList<>();
        if (Files.isDirectory(path)) {
            ExplorerService.getFiles(files, path);
        }
        else {
            ExplorerService.downloadFile(resp, path);
        }

        req.setAttribute("time", DateFormat.getDateTimeInstance().format(new Date()));
        if (!relativePath.toString().equals("\\")) {
            req.setAttribute("path", relativePath.toString().replace("\\", "/"));
        }
        req.setAttribute("parentPath", parentPath);
        req.setAttribute("files", files);

        RequestDispatcher dispatcher = req.getRequestDispatcher("explorer.jsp");
        dispatcher.forward(req, resp);
    }
}
