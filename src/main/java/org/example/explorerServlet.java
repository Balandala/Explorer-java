package org.example;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.FileDTO;
import org.example.services.ExplorerService;

import java.io.IOException;
import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/")
public class explorerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String root = "D:/dev/labs_java";
        String urlPath = req.getParameter("path");
        if (urlPath == null || urlPath.equals("/"))
            urlPath = "";

        Path relativePath = Path.of(urlPath);
        String parentPath = relativePath.getParent() != null? relativePath.getParent().normalize().toString().replace("\\","/") : null;

        Path path = Path.of(root + urlPath);
        ArrayList<FileDTO> files = new ArrayList<>();
        if (Files.isDirectory(path)) {
            ExplorerService.getFiles(files, path);
        }
        else {
            ExplorerService.downloadFile(resp, path);
        }


        req.setAttribute("time", DateFormat.getDateTimeInstance().format(new Date()));
        req.setAttribute("path", urlPath);
        req.setAttribute("parentPath", parentPath);
        req.setAttribute("files", files);

        RequestDispatcher dispatcher = req.getRequestDispatcher("explorer.jsp");
        dispatcher.forward(req, resp);
    }
}
