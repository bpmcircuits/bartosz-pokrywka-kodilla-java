package com.kodilla.hibernate.task.dao;

import com.kodilla.hibernate.task.TaskFinancialDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskFinancialDetailsDaoTestSuite {

    @Autowired
    private TaskFinancialDetailsDao taskFinancialDetailsDao;

    @Test
    void testFindByPaid() {
        //given
        TaskFinancialDetails taskFinancialDetails = new TaskFinancialDetails(new BigDecimal(115), false);
        taskFinancialDetailsDao.save(taskFinancialDetails);
        int id = taskFinancialDetails.getId();
        //when
        List<TaskFinancialDetails> resultList = taskFinancialDetailsDao.findByPaid(false);
        //then
        assertEquals(1, resultList.size());
        //clean up
        taskFinancialDetailsDao.deleteById(id);
    }
}