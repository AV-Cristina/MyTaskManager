package com.course.innopolis.mytaskmanager.controls;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.course.innopolis.mytaskmanager.models.Task;
import com.course.innopolis.mytaskmanager.models.data.MyTaskManagerContract;
import com.course.innopolis.mytaskmanager.models.data.MyTaskManagerDBHelper;

import java.util.ArrayList;
import java.util.Date;
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

    // не используется
    private static final String SQL_SELECT_TASKS_FOR_USER =
            "SELECT * FROM " + MyTaskManagerContract.TaskEntry.TABLE_NAME
            + " WHERE " + MyTaskManagerContract.TaskEntry.COLUMN_USER_ID + "=?";


    // Добавление задачи
    public void addTask(String taskName, Boolean isAllDay, String taskDescription, Date dateBeg, Date dateEnd, String taskPeriodicity, String taskColor, Integer userId) {
/*        SQLiteDatabase db = this.mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyTaskManagerContract.TaskEntry.COLUMN_NAME, taskName);
        values.put(MyTaskManagerContract.TaskEntry.COLUMN_ISALLDAY, MyTaskManagerContract.TaskEntry.ISNOTALLDAY);
        values.put(MyTaskManagerContract.TaskEntry.COLUMN_DESCRIPTION, taskDescription);
        values.put(MyTaskManagerContract.TaskEntry.COLUMN_DATEBEG, dateBeg); // DATETIME
        values.put(MyTaskManagerContract.TaskEntry.COLUMN_DATEEND, dateEnd);
        values.put(MyTaskManagerContract.TaskEntry.COLUMN_PERIODICITY, taskPeriodicity);
        values.put(MyTaskManagerContract.TaskEntry.COLUMN_COLOR, taskColor);
        values.put(MyTaskManagerContract.TaskEntry.COLUMN_USER_ID, userId);
        db.insert(MyTaskManagerContract.TaskEntry.TABLE_NAME, null, values);
        db.close();*/
    }


    // Получение списка задачь одного пользователя
    public List<Task> getAllTasks() {
            List<Task> taskList = new ArrayList<>();
            taskList = null; ///
/*            SQLiteDatabase db = this.mDBHelper.getWritableDatabase();
            Cursor c = db.rawQuery(SQL_SELECT_TASKS_FOR_USER, null);

            if (c.moveToFirst()) {
                do {
                    Task task = new Task();
                    task.setId(Integer.parseInt(c.getString(c.getColumnIndex(MyTaskManagerContract.TaskEntry._ID))));
                    task.setTaskName(c.getString(c.getColumnIndex(MyTaskManagerContract.TaskEntry.COLUMN_NAME)));
                    Integer isAllDay = Integer.parseInt(c.getString(c.getColumnIndex(MyTaskManagerContract.TaskEntry.COLUMN_ISALLDAY)));
                    task.setAllDay(isAllDay == 1 ? true : false);
                    task.setTaskDescription(c.getString(c.getColumnIndex(MyTaskManagerContract.TaskEntry.COLUMN_DESCRIPTION)));
                    task.getDateBeg();
                    task.getDateEnd();
                    task.setTaskPeriodicity(c.getString(c.getColumnIndex(MyTaskManagerContract.TaskEntry.COLUMN_PERIODICITY)));
                    task.setTaskColor(c.getString(c.getColumnIndex(MyTaskManagerContract.TaskEntry.COLUMN_COLOR)));
                    taskList.add(task);
                } while (c.moveToNext());
            }*/
            return taskList;
        }


}
