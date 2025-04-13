package org.example.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FileDTO {
    final String name;
    final String size;
    final String lastUpdated;

    public FileDTO(Path file){
        this.name = file.getFileName().toString();
        this.lastUpdated = GetFileTime(file);
        this.size = CalculateSize(file);
    }

    private String GetFileTime(Path file){
        try {
            FileTime fileTime = Files.getLastModifiedTime(file);
            DateFormat date = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss");
            return date.format(fileTime.toMillis());
        } catch (IOException e) {
            return "-";
        }
    }

    private String CalculateSize(Path file){
        if (Files.isDirectory(file)){
            return "-";
        }
        try {
            long size = Files.size(file);
            if (size < Math.pow(2, 10)){
                return String.valueOf(size) + " B";
            } else if (size < Math.pow(2, 20)){
                return String.valueOf(size / (long)Math.pow(2, 10)) + " MB";
            }
            else return String.valueOf(size / (long)Math.pow(2, 20)) + " GB";
        }
        catch (IOException e) {
            return "-";
        }
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }
}

