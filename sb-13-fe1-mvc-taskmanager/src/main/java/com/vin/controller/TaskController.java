package com.vin.controller;

import com.vin.entity.Task;
import com.vin.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/")
    public String dashboard(Model model) {

        model.addAttribute("tasks", taskService.getAllTasks());

        return "index";
    }

    @GetMapping("/add-task")
    public String addTaskPage(Model model) {

        model.addAttribute("task", new Task());

        return "add-task";
    }

    @PostMapping("/tasks")
    public String saveTask(
            @Valid @ModelAttribute("task") Task task,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            return "add-task";
        }

        taskService.saveTask(task);

        return "redirect:/";
    }

    @PostMapping("/tasks/complete/{id}")
    public String completeTask(@PathVariable Long id) {

        taskService.markCompleted(id);

        return "redirect:/";
    }

    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);

        return "redirect:/";
    }
}
