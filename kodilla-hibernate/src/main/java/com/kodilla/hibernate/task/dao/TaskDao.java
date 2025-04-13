package com.kodilla.hibernate.task.dao;

import com.kodilla.hibernate.task.Task;
import com.kodilla.hibernate.task.TaskList;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface TaskDao extends CrudRepository<Task, Integer> {
    List<Task> findByDuration(int duration);

    interface TaskListDao extends CrudRepository<TaskList, Integer> {
        List<TaskList> findByListName(String listName);
    }
}
