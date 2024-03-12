package org.javaacademy.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.javaacademy.Sex;
import org.javaacademy.profession.Manager;
import org.javaacademy.profession.Programmer;
import org.javaacademy.task.Task;

public class Runner {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("нужно 2 агрумента, Название компании и ставка программистов");
        }
        if (!args[1].matches("^\\d+$")) {
            throw new IllegalArgumentException("Введите число в десятичной системе");
        }
        Manager manager = new Manager("Вася", "иванов", "петрович", Sex.MALE);
        Programmer programmerOne = new Programmer("Олег", "сидоров", "олегович", Sex.MALE);
        Programmer programmerTwo = new Programmer("Анна", "иванова", "смирнова", Sex.FEMALE);
        Company oracle = new Company(
                args[0], manager, List.of(programmerOne, programmerTwo), Integer.parseInt(args[1]));

        Task taskOne = new Task("задача #1", 8);
        Task taskTwo = new Task("задача #2", 12);
        Task taskThree = new Task("задача #3", 15);

        Queue<Task> tasks = new LinkedList<>(List.of(taskOne, taskTwo, taskThree));
        oracle.weeklyWork(tasks);
        oracle.paySalaries();
        oracle.companyInfo();
    }
}
