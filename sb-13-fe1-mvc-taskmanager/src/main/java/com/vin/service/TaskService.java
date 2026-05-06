package com.vin.service;

import com.vin.entity.Task;

import java.util.List;

public interface TaskService {

    Task saveTask(Task task);

    List<Task> getAllTasks();

    void markCompleted(Long id);

    void deleteTask(Long id);
}