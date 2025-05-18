package com.hitss.springboot.taskmanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitss.springboot.taskmanager.models.Task;
import com.hitss.springboot.taskmanager.repositories.TaskManagerRepository;
import com.hitss.springboot.taskmanager.repositories.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskManagerRepository taskManagerRepository;

    public List<Task> getAllTasks() {
        return taskManagerRepository.listTasks();
    }

    public void createTask(Task task) {
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }
        taskRepository.createTask(task.getTitle(), task.getDescription());
    }
    
    public Task getTaskById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada con ID: " + id));
    }
    
    public List<Task> searchTasksByTitle(String title) {
        return taskManagerRepository.searchTasksByTitle(title);
    }
    
	public Task updateTask(Integer id, Task updatedTask) {
		Task existingTask = taskRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada con ID: " + id));

	        existingTask.setTitle(updatedTask.getTitle());
	        existingTask.setDescription(updatedTask.getDescription());
	        existingTask.setCompleted(updatedTask.getCompleted());
	        existingTask.setUpdatedAt(java.time.LocalDateTime.now());

	        return taskRepository.save(existingTask);
	}
	public void deleteTask(Integer id) {
	    Task task = taskRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada con ID: " + id));
	    taskRepository.delete(task);
	}
	public void markAsCompleted(Integer id) {
	    Task task = taskRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada con ID: " + id));
	    task.setCompleted(true);
	    task.setUpdatedAt(java.time.LocalDateTime.now());
	    taskRepository.save(task);
	}
}
