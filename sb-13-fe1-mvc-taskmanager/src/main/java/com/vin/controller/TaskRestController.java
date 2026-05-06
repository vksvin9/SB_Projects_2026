package com.vin.controller;

import com.vin.entity.Task;
import com.vin.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TaskRestController {

    private final TaskService taskService;

    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public void completeTask(@PathVariable Long id) {
        taskService.markCompleted(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}