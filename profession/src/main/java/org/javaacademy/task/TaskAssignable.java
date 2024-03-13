package org.javaacademy.task;

/**
 * Интерфейс, представляющий возможность назначения задачи.
 * Классы, реализующие этот интерфейс, способны принимать задачи и выполнять их.
 */
public interface TaskAssignable {
    void acceptsTask(Task task);
}
