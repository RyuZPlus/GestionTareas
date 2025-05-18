package com.hitss.springboot.taskmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hitss.springboot.taskmanager.models.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	
    @Procedure(name = "Task.createTask")
    void createTask(
        @Param("title") String title,
        @Param("description") String description
    );
    
	List<Task> findByTitle(String title);
}
