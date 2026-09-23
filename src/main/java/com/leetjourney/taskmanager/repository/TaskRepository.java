package com.leetjourney.taskmanager.repository;

import com.leetjourney.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

// A Spring bean is an object that is created, managed and wired by Spring IoC container
// A singleton is a design pattern where only one instance of a class exists and is shared throughout the app
// JpaRepository will generate methods for Task objects and IDs
public interface TaskRepository extends JpaRepository<Task, Long> {
}
