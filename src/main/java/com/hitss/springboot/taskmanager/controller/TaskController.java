package com.hitss.springboot.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.springboot.taskmanager.models.Task;
import com.hitss.springboot.taskmanager.services.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/tasks")
	public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

	@PostMapping("/tasks")
	public void createTask(@RequestBody Task task) {
        taskService.createTask(task);
    }
	
	@PutMapping("/tasks/{id}")
	public Task updateTask(@PathVariable Integer id, @RequestBody Task updatedTask) {
	    return taskService.updateTask(id, updatedTask);
	}
	
	@PutMapping("/tasks/{id}/complete")
	public void markTaskAsCompleted(@PathVariable Integer id) {
	    taskService.markAsCompleted(id);
	}
	
	@GetMapping("/tasks/{id}")
	public Task getTaskById(@PathVariable Integer id) {
	    return taskService.getTaskById(id);
	}
	
	@GetMapping("/tasks/search")
	public List<Task> searchTasksByTitle(@RequestParam String title) {
	    return taskService.searchTasksByTitle(title);
	}
	
	@DeleteMapping("/tasks/{id}")
	public void deleteTask(@PathVariable Integer id) {
	    taskService.deleteTask(id);
	}

}
