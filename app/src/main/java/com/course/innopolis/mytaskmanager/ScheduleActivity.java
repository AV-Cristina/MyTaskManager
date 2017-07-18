package com.course.innopolis.mytaskmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ScheduleActivity extends AppCompatActivity {
    Button mAddTask;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        context = this;

        mAddTask = (Button)findViewById(R.id.mAddTask);
        mAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, CategorySelectActivity.class);
                Intent intent = new Intent(context, AddTaskActivity.class);
                context.startActivity(intent);
            }
        });
    }

    //  Меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Toast.makeText(this, "logout", Toast.LENGTH_LONG).show();
                return true;
            case R.id.profile:
                Toast.makeText(this, "profile", Toast.LENGTH_LONG).show();
                return true;
            case R.id.settings:
                Intent intent = new Intent(context,SettingsActivity.class);
                context.startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
