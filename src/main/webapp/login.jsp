<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru-RU">
    <head>
        <title>Вход</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <div>
            <h1>Вход</h1>
            <form action="" method="post">
                <div>
                    <label for="login">Логин</label>
                    <input name="login" id="login" required>
                </div>
                <div>
                    <label for="password">Пароль</label>
                    <input name="password" id="password" required>
                </div>
                <div>
                    <input type="submit" value="Войти">
                </div>
            </form>
            <a href="/files/register">Зарегистрироваться</a>
        </div>
        <div>
            <p>${status}</p>
        </div>
    </body>
</html>