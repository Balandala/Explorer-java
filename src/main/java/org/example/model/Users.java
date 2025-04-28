package org.example.model;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Users {
    private final static String filename = "\\home\\Users.bin";

    public static boolean AreUserExists(String login){
        ArrayList<User> users = Deserialize();
        boolean bool = users.stream().anyMatch(user -> user.getLogin().equals(login));
        System.out.printf("Существует ли пользователь с логином %s? - %s\n", login, bool);
        return bool;
    }

    public static void Create(String login, String password, String email){
        if (!AreUserExists(login)) {
            ArrayList<User> users = Deserialize();
            users.add(new User(login, password, email));
            Serialize(users);
        }
    }

    public static User GetUser(String login){
        if (AreUserExists(login)){
            ArrayList<User> users = Deserialize();
            User user = users.stream().filter(u -> u.getLogin().equals(login)).findFirst().get();
            System.out.printf("Получен юзер с логином: %s\n",user.getLogin());
            return user;
        }
        else return null;
    }

    private static void Serialize(ArrayList<User> users){
        try (FileOutputStream fileOut = new FileOutputStream(filename)) {
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(users);
            objectOut.close();

        }  catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("Файл по пути: %s не найден.\n", filename));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Ошибка при чтении файла %s\n", filename));
        }
    }

    private static ArrayList<User> Deserialize(){
        ArrayList<User> users = new ArrayList<>();
        System.out.println("Начинается процессс десериализации...");
        try (FileInputStream fileIn = new FileInputStream(filename)) {
            System.out.println("FileInputStream успешно открыт");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            System.out.println("ObjectInputStream успешно открыт");

            System.out.println("Попытка прочитать данные о пользователях...");
            users = (ArrayList<User>) objIn.readObject();
            System.out.println("Данные о пользователях успешно прочитаны");
            objIn.close();
            Print(users);
            return users;

        }  catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Файл по пути: %s не найден.\n", filename));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Ошибка при чтении файла %s\n", filename));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private static void Print(ArrayList<User> users){
        for (User user : users){
            System.out.printf("%s\t%s\t%s\n", user.getLogin(), user.getPassword(), user.getEmail());
        }
    }

}
