package com.kodilla.patterns2.observer.homework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeworkTopicTestSuite {

    @Test
    void testUpdate() {
        // Given
        HomeworkTopic forLoopsExercise = new ForLoopsExercise();
        HomeworkTopic javaPatternsExercise = new JavaPatternsExercise();
        Mentor john = new Mentor("John");
        Mentor kate = new Mentor("Kate");
        forLoopsExercise.registerObserver(john);
        forLoopsExercise.registerObserver(kate);
        javaPatternsExercise.registerObserver(john);
        // When
        forLoopsExercise.addExercise("Test for loop solution one...");
        forLoopsExercise.addExercise("Test loop two...");
        forLoopsExercise.addExercise("Test for i one...");
        javaPatternsExercise.addExercise("Test pattern one...");
        javaPatternsExercise.addExercise("Test extra pattern two...");
        // Then
        assertEquals(5, john.getUpdateCount());
        assertEquals(3, kate.getUpdateCount());

    }

}