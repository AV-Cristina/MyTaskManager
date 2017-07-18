package com.course.innopolis.mytaskmanager.Control;

import com.course.innopolis.mytaskmanager.Model.User;

import java.util.List;

/**
 * Created by Cristina on 13.07.2017.
 */

public interface IUserManager {
    void addUser(String login, String password); // Добавление пользователя
    Boolean findUser(String login); // Поиск пользователя по логину
    User getValidUser(String login, String password); // Возвращает пользователя, если введены верные логин/пароль
    User getUser(int id);
    List<User> getAllUsers(); // Возвращает список всех пользователей
    int updateUser(User user); // Блокировка/разблокировка пользователя, изменение кол-ва неуспешных попыток входа

    int getUsersCount();
    void deleteUser(User user);
    //void deleteAll();
}
