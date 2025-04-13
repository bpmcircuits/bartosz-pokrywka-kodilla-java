package com.kodilla.hibernate.task.dao;

import com.kodilla.hibernate.task.TaskList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskListDao extends CrudRepository<TaskList, Integer> {
    List<TaskList> findByDescription(String description);
}
