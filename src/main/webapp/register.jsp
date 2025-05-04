<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru-RU">
    <head>
        <title>Вход</title>
    </head>
    <body>
        <div>
            <h1>Регистрация</h1>
            <form action="" method="post">
                <div>
                    <label for="email">Электронная почта</label>
                    <input name="email" id="email" required>
                </div>
                <div>
                    <label for="username">Логин</label>
                    <input name="username" id="username" required>
                </div>
                <div>
                    <label for="password">Пароль</label>
                    <input name="password" id="password" required>
                </div>
                <div>
                    <input type="submit" value="Зарегистрироваться">
                </div>
            </form>
            <a href="/files/login">Уже есть аккаунт?</a>
        </div>
        <div>
            <p>${status}</p>
        </div>
    </body>
</html>