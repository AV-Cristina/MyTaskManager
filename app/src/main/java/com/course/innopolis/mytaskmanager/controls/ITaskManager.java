package com.course.innopolis.mytaskmanager.controls;

import com.course.innopolis.mytaskmanager.models.Task;

import java.util.List;

/**
 * Created by Cristina on 13.07.2017.
 */

public interface ITaskManager {
    void addTask(Task task);
    List<Task> getAllTasks();
}
