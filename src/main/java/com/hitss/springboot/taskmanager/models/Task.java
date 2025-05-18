package com.hitss.springboot.taskmanager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.ParameterMode;
import lombok.Data;

@Entity
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		    name = "Task.createTask",
		    procedureName = "sp_create_task",
		    parameters = {
		        @StoredProcedureParameter(mode = ParameterMode.IN, name = "title", type = String.class),
		        @StoredProcedureParameter(mode = ParameterMode.IN, name = "description", type = String.class)
		    }
		),
	@NamedStoredProcedureQuery(
	        name = "Task.listTask",
	        procedureName = "sp_list_tasks",
	        resultClasses = Task.class
	    ),
	@NamedStoredProcedureQuery(
	        name = "Task.searchByTitle",
	        procedureName = "sp_show_task_title",
	        parameters = {
	            @StoredProcedureParameter(mode = ParameterMode.IN, name = "title", type = String.class)
	        },
	        resultClasses = Task.class
	    )
})

@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private Boolean completed;

    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}
