<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<language=ru>
<html lang="ru-RU">
<head>
    <title>Explorer</title>
<head>
<body>
    <p>${time}</p>
    <h1>${path}</h1>
    <a href="?path=${parentPath}">Вверх</a>
    <table border="0">
        <thead>
            <tr>
                <td>
                    <b>Имя<b>
                </td>
                <td>
                    <b>Размер<b>
                </td>
                <td>
                    <b>Дата изменения<b>
                </td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${files}" var="file">
                <tr>
                    <td><a href="?path=${path}${file.name}">${file.name}</a><td>
                    <td>${file.size}</td>
                    <td>${file.lastUpdated}</td>
                </tr>
            </c:forEach>
        <tbody>
<body>
<html>