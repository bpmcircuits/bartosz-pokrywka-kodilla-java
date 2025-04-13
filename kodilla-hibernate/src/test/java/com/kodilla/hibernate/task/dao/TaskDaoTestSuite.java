package com.kodilla.hibernate.task.dao;

import com.kodilla.hibernate.task.Task;
import com.kodilla.hibernate.task.TaskFinancialDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskDaoTestSuite {

    @Autowired
    private TaskDao taskDao;
    private static final String DESCRIPTION = "Test: Learn Hibernate";

    @Test
    void testTaskDaoSave() {
        //Given
        Task task = new Task(DESCRIPTION, 7);

        //When
        taskDao.save(task);

        //Then
        int id = task.getId();
        Optional<Task> readTask = taskDao.findById(id);
        assertTrue(readTask.isPresent());

        //CleanUp
        taskDao.deleteById(id);
    }

    @Test
    void testTaskDaoFindByDuration() {
        //given
        Task task = new Task(DESCRIPTION, 7);
        taskDao.save(task);
        int duration = task.getDuration();
        //when
        List<Task> readTasks = taskDao.findByDuration(duration);
        //then
        assertEquals(1, readTasks.size());
        //clean up
        int id = readTasks.get(0).getId();
        taskDao.deleteById(id);
    }

    @Test
    void testTaskDaoSaveWithFinancialDetails() {
        //given
        Task task = new Task(DESCRIPTION, 30);
        task.setTaskFinancialDetails(new TaskFinancialDetails(new BigDecimal(120), false));
        //when
        taskDao.save(task);
        int id = task.getId();
        //then
        assertNotEquals(0, id);
        //clean up
        //taskDao.deleteById(id);
    }


}