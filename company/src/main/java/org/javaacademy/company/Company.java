package org.javaacademy.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.javaacademy.profession.Employee;
import org.javaacademy.profession.Manager;
import org.javaacademy.profession.Programmer;
import org.javaacademy.task.Task;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company {
    final String name;
    final Manager manager;
    final List<Programmer> programmers;
    double totalCosts;
    MultiValuedMap<Programmer, Task> programmerTaskList = new ArrayListValuedHashMap<>();
    Map<Employee, Double> employeeWorkHours = new HashMap<>();


    /**
     * @param name           Название компании
     * @param manager        Менеджер
     * @param programmers    Список программистов
     * @param programmerRate Единая ставка для программистов
     */
    public Company(@NonNull String name,
                   @NonNull Manager manager,
                   @NonNull List<Programmer> programmers,
                   int programmerRate) {
        this.name = name;
        this.manager = manager;
        this.programmers = programmers;
        programmers.forEach(programmer -> programmer.setRate(programmerRate));
    }

    /**
     * 5.3. В компании есть недельная работа
     *
     * @param tasks Cписок задач
     */
    public void weeklyWork(@NonNull Queue<Task> tasks) {
        while (!tasks.isEmpty()) {
            for (Programmer programmer : programmers) {
                if (tasks.isEmpty()) {
                    break;
                }
                Task task = tasks.poll();
                programmer.acceptsTask(task);
                programmerTaskList.put(programmer, task);
                employeeWorkHours.put(programmer, employeeWorkHours.getOrDefault(programmer, 0.0)
                        + task.getNumberHoursPerTask());
                employeeWorkHours.put(manager, (employeeWorkHours.getOrDefault(manager, 0.0)
                        + task.getNumberHoursPerTask() * 0.1));
            }
        }
    }

    /**
     * 5.4. Компания расплачивается за неделю работы: обнуляет табель учета времени,
     * а всем работникам выплачивает деньги. В сумму затрат вносятся все выплаты.
     */
    public void paySalaries() {
        double managerMoney = manager.getRate() * employeeWorkHours.getOrDefault(manager, 0.0);
        totalCosts += managerMoney;
        manager.setMoneyEarned(managerMoney);
        for (Programmer programmer : programmers) {
            double earnedMoney = programmer.getRate() * employeeWorkHours.getOrDefault(programmer, 0.0);
            programmer.setMoneyEarned(earnedMoney);
            totalCosts += earnedMoney;
        }
        employeeWorkHours = new HashMap<>();
    }

    /**
     * 5.5 Печатает информацию:
     * "
     * [имя компании]
     * Затраты: [сумма затрат до 2х знаков после запятой]
     * Список выполненных задач у компании:
     * [ФИО программиста] - [список задач]
     * [ФИО программиста] - [список задач]
     * "
     */
    public void companyInfo() {
        System.out.printf("%s\nЗатраты: %.2f\n", name, totalCosts);
        System.out.println("Список выполненных задач у компании:");
        programmerTaskList.keySet().forEach(programmer -> {
            System.out.print(programmer.getFullName() + " - ");
            programmerTaskList.get(programmer)
                    .stream()
                    .map(task -> task.getDescription() + ", ")
                    .forEach(System.out::print);
            System.out.println();
        });
    }
}
