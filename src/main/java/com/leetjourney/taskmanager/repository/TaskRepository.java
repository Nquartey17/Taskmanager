package com.leetjourney.taskmanager.repository;

import com.leetjourney.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// A Spring bean is an object that is created, managed and wired by Spring IoC container
// A singleton is a design pattern where only one instance of a class exists and is shared throughout the app
// JpaRepository will generate methods for Task objects and IDs
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Custom Query
    // Code below: SELECT * FROM tasks WHERE completed = :completed (parameter)
    List<Task> findByCompleted(boolean completed);

    List<Task> findByTitleContainingIgnoreCase(String title);

    // JPQL query
    // completed parameter will be parameter in JPQL query (@Param)
    @Query("SELECT t FROM Task t WHERE t.completed = :completed")
    List<Task> findTasksByCompletionStatus(@Param("completed") boolean completed);
}
