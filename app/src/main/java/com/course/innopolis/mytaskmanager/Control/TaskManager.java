package com.course.innopolis.mytaskmanager.Control;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.course.innopolis.mytaskmanager.Model.Task;
import com.course.innopolis.mytaskmanager.Model.data.MyTaskManagerDBHelper;

import java.util.List;

/**
 * Created by Cristina on 13.07.2017.
 */

public class TaskManager {

    private static MyTaskManagerDBHelper mDBHelper;

    private TaskManager(){
        this.mDBHelper = MyTaskManagerDBHelper.getInstance();
    }

    private static class TaskManagerHolder{
        private final static TaskManager instance = new TaskManager();
    }

    public static TaskManager getInstance(){
        return TaskManagerHolder.instance;
    }

    public void addTask(Task task) {
        SQLiteDatabase db = this.mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

    }

    public List<Task> getAllTasks() {
        return null;
    }
}
