package com.course.innopolis.mytaskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.course.innopolis.mytaskmanager.adapters.UserAdapter;
import com.course.innopolis.mytaskmanager.controls.UserManager;
import com.course.innopolis.mytaskmanager.models.User;

import java.util.List;

public class AdminActivity extends AppCompatActivity implements OnListItemCallback{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mRecyclerView = (RecyclerView) findViewById(R.id.users_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        UserManager um = UserManager.getInstance();

        // Получение списка пользователей
        List<User> users = um.getAllUsers();

        mAdapter = new UserAdapter(this, users, this);
        mRecyclerView.setAdapter(mAdapter);

        for (User user : users) {
            String s = "id: " + user.getId() + " , Login: " + user.getLogin()
                    + " Pas: " + user.getPassword()
                    + " Unsuccessful: " + user.getUnsuccessful_login_count()
                    + " Active: " + user.getActive();
            Log.i("LOG ", s);
        }
    }



    @Override
    public void onClick(User user) {

    }
}
