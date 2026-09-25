package com.leetjourney.taskmanager.controller;

import com.leetjourney.taskmanager.entity.Task;
import com.leetjourney.taskmanager.service.TaskService;
import jakarta.validation.Valid;
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
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // CRUD - Create, read, update, delete
    // RequestBody - tells spring to cover the JSON in request body to Task object (Auto JSON parsing)
    // @Valid before Requestbody to validate annotations in Task.java
    @PostMapping // Handles HTTP post requests
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task savedTask = taskService.createTask(task); // save task to db
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask); // Tell client Task was successfully created
    }

    // Update
    @PutMapping("/{id}")
    public Task updateTasks(@PathVariable Long id, @Valid @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build(); //Tell user task was deleted which is why responseEntity needs to be returned
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
