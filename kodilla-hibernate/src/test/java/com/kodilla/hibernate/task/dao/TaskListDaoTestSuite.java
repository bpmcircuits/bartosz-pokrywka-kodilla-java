package com.kodilla.hibernate.task.dao;

import com.kodilla.hibernate.task.Task;
import com.kodilla.hibernate.task.TaskFinancialDetails;
import com.kodilla.hibernate.task.TaskList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskListDaoTestSuite {

    @Autowired
    private TaskListDao taskListDao;

    @Test
    void testFindByListName() {
        //given
        TaskList taskList = new TaskList("Test list one", "Description one");
        taskListDao.save(taskList);
        //when
        List<TaskList> actualList = taskListDao.findByDescription("Description one");
        //then
        assertEquals(1, actualList.size());
        assertEquals("Description one", actualList.getFirst().getDescription());
        //clean up
        taskListDao.delete(taskList);
    }

    @Test
    void testTaskListDaoSaveWithTasks() {
        //given
        Task task = new Task("Test: Learn Hibernate", 14);
        Task task2 = new Task("Test: Write some entities", 3);
        TaskFinancialDetails tfd = new TaskFinancialDetails(new BigDecimal(20), false);
        TaskFinancialDetails tfd2 = new TaskFinancialDetails(new BigDecimal(10), false);
        task.setTaskFinancialDetails(tfd);
        task.setTaskFinancialDetails(tfd2);
        TaskList taskList = new TaskList("TaskList", "ToDo tasks");
        taskList.getTasks().add(task);
        taskList.getTasks().add(task2);
        task.setTaskList(taskList);
        task2.setTaskList(taskList);
        //when
        taskListDao.save(taskList);
        int id = taskList.getId();
        //then
        assertNotEquals(0, id);
        //clean up
        taskListDao.deleteById(id);
    }
}