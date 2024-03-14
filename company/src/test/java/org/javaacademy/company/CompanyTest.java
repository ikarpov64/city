package org.javaacademy.company;

import org.javaacademy.Sex;
import org.javaacademy.profession.Manager;
import org.javaacademy.profession.Programmer;
import org.javaacademy.task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompanyTest {
    private static final String COMPANY_NAME = "Oracle";
    private static final int PROGRAMMER_RATE = 1_600;
    private static Manager managerMock = Mockito.mock(Manager.class);
    private static Programmer programmerOneMock = Mockito.mock(Programmer.class);
    private static Programmer programmerTwoMock = Mockito.mock(Programmer.class);
    private static List<Programmer> programmerListMock = List.of(programmerOneMock, programmerTwoMock);
    private static Task taskOneMock = Mockito.mock(Task.class);
    private static Task taskTwoMock = Mockito.mock(Task.class);
    private static Task taskThreeMock = Mockito.mock(Task.class);

    @Test
    @DisplayName("Создание компании без ошибок")
    void companyCreate() {
        Assertions.assertDoesNotThrow(
                () -> new Company(COMPANY_NAME, managerMock, programmerListMock, PROGRAMMER_RATE));
    }

    @Test
    @DisplayName("Создание компании с null именем компании")
    void companyNameNull() {
        assertThrows(NullPointerException.class, () ->
                new Company(null, managerMock, programmerListMock, PROGRAMMER_RATE));
    }

    @Test
    @DisplayName("Создание компании с null менеджером")
    void companyManagerNull() {
        Assertions.assertThrows(NullPointerException.class, (
                () -> new Company(COMPANY_NAME, null, programmerListMock, PROGRAMMER_RATE)));
    }

    @Test
    @DisplayName("Создание компании с null списком программистов")
    void companyProgrammerNull() {
        Assertions.assertThrows(NullPointerException.class, (
                () -> new Company(COMPANY_NAME, managerMock, null, PROGRAMMER_RATE)));
    }

    @Test
    @DisplayName("Создание компании с невалидными значениями ставки программистов")
    void companyInvalidProgrammersRate() {
        int programmerRateLow = 500;
        int programmerRateHigh = 3_000;
        Programmer programmerOne = new Programmer("test", "test", "test", Sex.MALE);
        Assertions.assertThrows(IllegalArgumentException.class, (
                () -> new Company("Oracle", managerMock, List.of(programmerOne), programmerRateLow)));
        Assertions.assertThrows(IllegalArgumentException.class, (
                () -> new Company("Oracle", managerMock, List.of(programmerOne), programmerRateHigh)));
    }

    @Test
    @DisplayName("Запуск рабочей недели без ошибок")
    void companyWeeklyWorkSuccess() {
        Company companyTest = new Company(COMPANY_NAME, managerMock,
                List.of(programmerOneMock, programmerTwoMock), PROGRAMMER_RATE);
        Assertions.assertDoesNotThrow(
                () -> companyTest.weeklyWork(new LinkedList<>(List.of(taskOneMock, taskTwoMock, taskThreeMock))));
    }

    @Test
    @DisplayName("Запуск рабочей недели с null списком задач")
    void companyWeeklyWorkFailed() {
        Company companyTest = new Company(COMPANY_NAME, managerMock,
                List.of(programmerOneMock, programmerTwoMock), PROGRAMMER_RATE);
        Assertions.assertThrows(NullPointerException.class,
                () -> companyTest.weeklyWork(null));
    }

    @Test
    @DisplayName("Запуск оплаты сотрудников")
    void companyPaySalaries() {
        Company companyTest = new Company(COMPANY_NAME, managerMock,
                List.of(programmerOneMock, programmerTwoMock), PROGRAMMER_RATE);
        Assertions.assertDoesNotThrow(companyTest::paySalaries);
    }

    @Test
    @DisplayName("Запуск печати информации о компании")
    void companyPrintInfo() {
        Company companyTest = new Company(COMPANY_NAME, managerMock,
                List.of(programmerOneMock, programmerTwoMock), PROGRAMMER_RATE);
        companyTest.weeklyWork(new LinkedList<>(List.of(taskOneMock, taskTwoMock, taskThreeMock)));
        Assertions.assertDoesNotThrow(companyTest::companyInfo);
    }
}
