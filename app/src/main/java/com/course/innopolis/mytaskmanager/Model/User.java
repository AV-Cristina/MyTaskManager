package com.course.innopolis.mytaskmanager.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cristina on 11.07.2017.
 */

public class User  implements Parcelable {
    private Integer id;
    private String login;
    private String password;   // TODO: хранить хэш пароля
    private Integer unsuccessful_login_count;
    private Boolean isActive;

    public User(){
    }

    // Обычный конструктор
    public User(Integer id, String login, String password, Integer unsuccessful_login_count, Boolean isActive){
        this.id = id;
        this.login = login;
        this.password = password;
        this.unsuccessful_login_count = unsuccessful_login_count;
        this.isActive = isActive;
    }

    // Конструктор нового пользователя
    public User(String login, String password, Boolean isActive){
        this.login = login;
        this.password = password;
        this.unsuccessful_login_count = 0;
        this.isActive = isActive;
    }

    @Override
    // Define the kind of object that you gonna parcel,
    // You can use hashCode() here
    // Конструктор, в котором можно вызывать hashCode()???
    //TODO: зачем???
    public int describeContents() {
        return 0;
    }

    @Override
    // Упаковка объекта в Parcel
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(login);
        parcel.writeString(password);
        parcel.writeInt(unsuccessful_login_count);
        parcel.writeInt(isActive ? 1 : 0);
    }

    // Creator необходим, чтобы создавать новые объекты или массивы объектов
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    // Конструктор, считывающий данные из Parcel
    private User(Parcel parcel){
        id = parcel.readInt();
        login = parcel.readString();
        password = parcel.readString();
        unsuccessful_login_count = parcel.readInt();
        isActive = (parcel.readInt() != 0);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getUnsuccessful_login_count() {
        return unsuccessful_login_count;
    }

    public void setUnsuccessful_login_count(Integer unsuccessful_login_count) {
        this.unsuccessful_login_count = unsuccessful_login_count;
    }

    @Override
    public int hashCode() {
        return (int) (21 + id * 42);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof User)) return false;
        if (this.id == ((User)obj).id) return true;
        return super.equals(obj);
    }
}
