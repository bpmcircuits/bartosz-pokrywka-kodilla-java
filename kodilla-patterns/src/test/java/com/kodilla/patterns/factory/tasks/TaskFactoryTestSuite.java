package com.kodilla.patterns.factory.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskFactoryTestSuite {

    @Test
    void testCreateTask() {
        //given
        Task drivingTask = TaskFactory.createTask("DRIVING_TASK");
        Task shoppingTask = TaskFactory.createTask("SHOPPING_TASK");
        Task paintingTask = TaskFactory.createTask("PAINTING_TASK");
        //when
        drivingTask.executeTask();
        shoppingTask.executeTask();
        paintingTask.executeTask();
        String actualDriving = drivingTask.getTaskName();
        String actualShopping = shoppingTask.getTaskName();
        String actualPainting = paintingTask.getTaskName();
        //then
        assertEquals("Drive to school", actualDriving);
        assertEquals("Buying groceries", actualShopping);
        assertEquals("Painting walls", actualPainting);
        assertTrue(drivingTask.isTaskExecuted());
        assertTrue(shoppingTask.isTaskExecuted());
        assertFalse(paintingTask.isTaskExecuted());
    }
}