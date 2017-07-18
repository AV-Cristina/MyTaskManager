package com.course.innopolis.mytaskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.course.innopolis.mytaskmanager.Control.UserManager;
import com.course.innopolis.mytaskmanager.Model.User;

import java.util.List;

public class AdminActivity extends AppCompatActivity {

    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        tv1 = (TextView)findViewById(R.id.tv1);

        UserManager um = UserManager.getInstance();

        // Получение списка пользователей
        List<User> users = um.getAllUsers();
        for (User user : users) {
            String s = "id: " + user.getId() + " , Login: " + user.getLogin()
                    + " Pas: " + user.getPassword()
                    + " Unsuccessful: " + user.getUnsuccessful_login_count()
                    + " Active: " + user.getActive();
            Log.i("LOG ", s);
        }

    }
}
