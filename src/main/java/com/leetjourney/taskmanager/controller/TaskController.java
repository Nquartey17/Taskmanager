package com.leetjourney.taskmanager.controller;

import com.leetjourney.taskmanager.entity.Task;
import com.leetjourney.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Controllers are essentially entry points to the app
@RestController // @Controller and @ResponseBody
@RequestMapping("/api/v1/tasks")
public class TaskController {

    // Singleton - Bean managed by Spring, can be injected as constructor to use
    private final TaskRepository taskRepository;

    /* Constructor injection (when Spring provides a bean's required dependencies through its contructor
    *  ensuring the object is fully initialized and immutable at creation */
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // @PathVariable means "get a value from the URL path and give it to my Java method."
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(ResponseEntity::ok) // If task exists, return HTTP 200 with the task
                .orElse(ResponseEntity.notFound().build()); // Else return HTTP 404
    }

    // CRUD - Create, read, update, delete
    // RequestBody - tells spring to cover the JSON in request body to Task object (Auto JSON parsing)
    @PostMapping // Handles HTTP post requests
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskRepository.save(task); // save task to db
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask); // Tell client Task was successfully created
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTasks(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setCompleted(updatedTask.getCompleted());
                    Task savedTask = taskRepository.save(task);
                    return ResponseEntity.ok(savedTask);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.delete(task);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/completed/{status}")
    public List<Task> getTasksByCompletions(@PathVariable boolean status) {
        return taskRepository.findByCompleted(status);
    }

    @GetMapping("/search")
    public List<Task> searchTasksByTitle(@RequestParam String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }




}
