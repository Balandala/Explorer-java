package org.example.services;

import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.FileDTO;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ExplorerService {
    public static void getFiles(ArrayList<FileDTO> files, Path dir){
        if (!Files.isDirectory(dir)) {
            throw new RuntimeException(String.format("Объект по пути %s не является папкой", dir.toString()));
        }
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            stream.forEach(x -> {
                files.add(new FileDTO(x));
            });
        } catch (Exception e) {
            throw new RuntimeException(String.format("Попытка получить данные о файлах в папке %s привела к исключению: %s", dir.toString(), e.getMessage()));
        }
    }
    public static void downloadFile(HttpServletResponse response, Path path){
        try {
            String mimeType = Files.probeContentType(path);
            if (mimeType == null){
                mimeType = "application/octet-stream";
            }

            String fileName =  sanitizeASCII(path.getFileName().toString());

            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
            response.setContentType(mimeType);
            response.setContentLengthLong(Files.size(path));

            OutputStream outputStream = response.getOutputStream();

            Files.copy(path, outputStream);
            outputStream.flush();

        } catch (IOException e){
            throw  new RuntimeException("Не удалось скачать файл");
        }
    }

    private static String sanitizeASCII(String string){
       return string.replaceAll("[^\\x20-\\x7e]", "_").replace(" ","_");
    }
}
