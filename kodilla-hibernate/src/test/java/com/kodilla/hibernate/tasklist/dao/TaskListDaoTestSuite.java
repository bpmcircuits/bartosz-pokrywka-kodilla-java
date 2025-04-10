package com.kodilla.hibernate.tasklist.dao;

import com.kodilla.hibernate.tasklist.TaskList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        List<TaskList> actualList = taskListDao.findByListName("Test list one");
        //then
        assertEquals(1, actualList.size());
        assertEquals("Description one", actualList.getFirst().getDescription());
        //clean up
        taskListDao.delete(taskList);
    }
}