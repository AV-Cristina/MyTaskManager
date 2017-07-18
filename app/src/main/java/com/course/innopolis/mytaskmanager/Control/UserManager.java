package com.course.innopolis.mytaskmanager.Control;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.course.innopolis.mytaskmanager.Model.User;
import com.course.innopolis.mytaskmanager.Model.data.MyTaskManagerContract;
import com.course.innopolis.mytaskmanager.Model.data.MyTaskManagerDBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristina on 12.07.2017.
 */

public class UserManager{

    private static MyTaskManagerDBHelper mDBHelper;

    private UserManager(){
        this.mDBHelper = MyTaskManagerDBHelper.getInstance();
    }

    private static class UserManagerHolder{
        private final static UserManager instance = new UserManager();
    }

    public static UserManager getInstance(){
        return UserManagerHolder.instance;
    }

    private static final String SQL_SELECT_COUNT_FOR_LOGIN =
            "SELECT COUNT(*) FROM " + MyTaskManagerContract.UserEntry.TABLE_NAME
            + " WHERE " + MyTaskManagerContract.UserEntry.COLUMN_LOGIN + "=?";


    // Поиск пользователя по логину
    public Boolean findUser(String login){
        SQLiteDatabase db = this.mDBHelper.getReadableDatabase();
        long count = DatabaseUtils.longForQuery(db, SQL_SELECT_COUNT_FOR_LOGIN,
                new String[] {login});
        return (count > 0);
    }


    // Проверка права на вход пользователя
    public User getValidUser(String login, String password){
        User user = null;
        SQLiteDatabase db = this.mDBHelper.getReadableDatabase();

        String[] projection = {
                MyTaskManagerContract.UserEntry._ID,
                MyTaskManagerContract.UserEntry.COLUMN_LOGIN,
                MyTaskManagerContract.UserEntry.COLUMN_PASSWORD,
                MyTaskManagerContract.UserEntry.COLUMN_UNSUCCESSFUL_LOGIN_COUNT,
                MyTaskManagerContract.UserEntry.COLUMN_ISACTIVE
        };

        String selection = MyTaskManagerContract.UserEntry.COLUMN_LOGIN + "=?";
        String[] selectionArgs = {login};

        Cursor c = db.query(
                MyTaskManagerContract.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c != null){
            if (c.moveToFirst()){
                Boolean isActive = (Integer.parseInt(c.getString(c.getColumnIndex(MyTaskManagerContract.UserEntry.COLUMN_ISACTIVE)))!= 0);
                String validPassword = c.getString(c.getColumnIndex(MyTaskManagerContract.UserEntry.COLUMN_PASSWORD));
                Integer user_id= Integer.parseInt(c.getString(c.getColumnIndex(MyTaskManagerContract.UserEntry._ID)));
                Integer unsuccessful_login_count = Integer.parseInt(c.getString(c.getColumnIndex(MyTaskManagerContract.UserEntry.COLUMN_UNSUCCESSFUL_LOGIN_COUNT)));

                if (!isActive){
                    throw new IllegalArgumentException("Пользователь " + login + " заблокирован, обратитесь к системному администратору");}

                else if (!validPassword.equals(password)) {
                    // увеличение количества неуспешных попыток входа
                    updateUser(user_id, isActive, unsuccessful_login_count);//
                    throw new IllegalArgumentException("Введен неверный пароль");}
                else {
                    user = new User();
                    user.setId(user_id);
                    user.setLogin(c.getString(c.getColumnIndex(MyTaskManagerContract.UserEntry.COLUMN_LOGIN)));
                    user.setPassword(c.getString(c.getColumnIndex(MyTaskManagerContract.UserEntry.COLUMN_PASSWORD)));
                    user.setUnsuccessful_login_count(unsuccessful_login_count);
                    user.setActive(isActive);
                }
            }
            else throw new IllegalArgumentException("Пользователь " + login + " не зарегистрирован в системе");
        }
        return user;
    }


    // Добавление пользователя
    public void addUser(String login, String password) {
        // если пользователь с таким логином ещё не существует
        if (!findUser(login)) {
            SQLiteDatabase db = this.mDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(MyTaskManagerContract.UserEntry.COLUMN_LOGIN, login);
            values.put(MyTaskManagerContract.UserEntry.COLUMN_PASSWORD, password);
            values.put(MyTaskManagerContract.UserEntry.COLUMN_UNSUCCESSFUL_LOGIN_COUNT, MyTaskManagerContract.UserEntry.NONE);
            values.put(MyTaskManagerContract.UserEntry.COLUMN_ISACTIVE, MyTaskManagerContract.UserEntry.ACTIVE);
            db.insert(MyTaskManagerContract.UserEntry.TABLE_NAME, null, values);
            db.close();
        }
        else throw new IllegalArgumentException("Логин " + login + " уже используется, введите другой");
    }


    // Возвращает список всех пользователей
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT * FROM " + MyTaskManagerContract.UserEntry.TABLE_NAME;

        SQLiteDatabase db = this.mDBHelper.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()){
            do {
                User user = new User();
                user.setId(Integer.parseInt(c.getString(c.getColumnIndex(MyTaskManagerContract.UserEntry._ID))));
                user.setLogin(c.getString(c.getColumnIndex(MyTaskManagerContract.UserEntry.COLUMN_LOGIN)));
                user.setLogin(c.getString(c.getColumnIndex(MyTaskManagerContract.UserEntry.COLUMN_PASSWORD)));
                Integer isActive = Integer.parseInt(c.getString(c.getColumnIndex(MyTaskManagerContract.UserEntry.COLUMN_ISACTIVE)));
                user.setActive(isActive == 1 ? true : false);
                userList.add(user);
                //Toast.makeText(this, user.getLogin(), Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
        return userList;
    }


    // Блокировка/разблокировка пользователя, изменение кол-ва попыток входа
    public int updateUser(Integer user_id, Boolean isActive, Integer unsuccessful_login_count) {
        SQLiteDatabase db = this.mDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        // увеличение количества неуспешных попыток входа
        if (unsuccessful_login_count != null){
            values.put(MyTaskManagerContract.UserEntry.COLUMN_UNSUCCESSFUL_LOGIN_COUNT, unsuccessful_login_count);
            // блокировка пользователя, если кол-во неуспешных попыток достигло 5
            if (unsuccessful_login_count == 5)
            {
                values.put(MyTaskManagerContract.UserEntry.COLUMN_ISACTIVE,
                        MyTaskManagerContract.UserEntry.INACTIVE);
            }
        }
        // разблокировка пользователя
        else if (isActive){
            values.put(MyTaskManagerContract.UserEntry.COLUMN_UNSUCCESSFUL_LOGIN_COUNT, 0);
            values.put(MyTaskManagerContract.UserEntry.COLUMN_ISACTIVE,
                    MyTaskManagerContract.UserEntry.ACTIVE);
        }
            // блокировка пользователя
            else {
                values.put(MyTaskManagerContract.UserEntry.COLUMN_ISACTIVE,
                        MyTaskManagerContract.UserEntry.INACTIVE);
            }

        String selection = MyTaskManagerContract.UserEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(user_id)};

        int count = db.update(
                MyTaskManagerContract.UserEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }


    public User getUser(int id) {
        return null;
    }


    public void deleteUser(User user) {
    }


    public int getUsersCount() {
        return 0;
    }

}
