package com.course.innopolis.mytaskmanager.Control;

import com.course.innopolis.mytaskmanager.Model.Task;

import java.util.List;

/**
 * Created by Cristina on 13.07.2017.
 */

public interface ITaskManager {
    void addTask(Task task);
    List<Task> getAllTasks();
}
