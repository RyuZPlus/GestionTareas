package com.hitss.springboot.taskmanager.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hitss.springboot.taskmanager.models.Task;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class TaskManagerRepository {
	@PersistenceContext
    private EntityManager entityManager;

    public List<Task> listTasks() {
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("Task.listTask");
        return query.getResultList();
    }
    
    public List<Task> searchTasksByTitle(String title) {
        StoredProcedureQuery query = entityManager
            .createNamedStoredProcedureQuery("Task.searchByTitle")
            .setParameter("title", title);
        return query.getResultList();
    }
}
