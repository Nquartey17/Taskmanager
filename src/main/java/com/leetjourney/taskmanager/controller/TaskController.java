package com.leetjourney.taskmanager.controller;

import com.leetjourney.taskmanager.entity.Task;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Controllers are essentially entry points to the app
@RestController // @Controller and @ResponseBody
@RequestMapping("/api/v1/tasks")
public class TaskController {

    // Temporary in-memory task list
    private final List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    // @PathVariable means "get a value from the URL path and give it to my Java method."
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        // Using stream to find task or return null
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);

    }

    // CRUD - Create, read, update, delete
    // RequestBody - tells spring to cover the JSON in request body to Task object (Auto JSON parsing)
    @PostMapping // Handles HTTP post requests
    public Task createTask(@RequestBody Task task) {
        task.setId(nextId++);
        task.setCreatedAt(LocalDateTime.now());
        task.setCompleted(false);
        tasks.add(task);

        return task;
    }

    // Update
    @PutMapping("/{id}")
    public Task updateTasks(@PathVariable Long id, @RequestBody Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getId().equals(id)) {
                updatedTask.setId(id);
                updatedTask.setCreatedAt(task.getCreatedAt());
                tasks.set(i, updatedTask);
                return updatedTask;
            }
        }
        return null;
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }


}
