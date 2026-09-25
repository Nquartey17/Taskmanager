package com.leetjourney.taskmanager.controller;

import com.leetjourney.taskmanager.entity.Task;
import com.leetjourney.taskmanager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controllers are essentially entry points to the app
@RestController // @Controller and @ResponseBody
@RequestMapping("/api/v1/tasks")
public class TaskController {

    // Singleton - Bean managed by Spring, can be injected as constructor to use
    private final TaskService taskService;

    /* Constructor injection (when Spring provides a bean's required dependencies through its contructor
    *  ensuring the object is fully initialized and immutable at creation */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // @PathVariable means "get a value from the URL path and give it to my Java method."
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok) // If task exists, return HTTP 200 with the task
                .orElse(ResponseEntity.notFound().build()); // Else return HTTP 404
    }

    // CRUD - Create, read, update, delete
    // RequestBody - tells spring to cover the JSON in request body to Task object (Auto JSON parsing)
    @PostMapping // Handles HTTP post requests
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskService.createTask(task); // save task to db
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask); // Tell client Task was successfully created
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTasks(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/completed/{status}")
    public List<Task> getTasksByCompletions(@PathVariable boolean status) {
        return taskService.getTasksByCompletionStatus(status);
    }

    @GetMapping("/search")
    public List<Task> searchTasksByTitle(@RequestParam String title) {
        return taskService.searchTasksByTitle(title);
    }




}
