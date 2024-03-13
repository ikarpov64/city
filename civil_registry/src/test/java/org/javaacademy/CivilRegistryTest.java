package org.javaacademy;

import static org.javaacademy.Sex.FEMALE;
import static org.javaacademy.Sex.MALE;

import org.javaacademy.enums.FamilyStatus;
import org.javaacademy.exceptions.SameSexException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

@DisplayName("Тестирование ЗАГСа")
public class CivilRegistryTest {
    private static CivilRegistry civilRegistryTest = new CivilRegistry("TEST");
    private static Citizen mockCitizen1 = Mockito.mock(Citizen.class);
    private static Citizen mockCitizen2 = Mockito.mock(Citizen.class);
    private static Citizen mockCitizen3 = Mockito.mock(Citizen.class);
    private static Citizen mockCitizen4 = Mockito.mock(Citizen.class);

    @Test
    @DisplayName("Создание записи о свадьбе без ошибок.")
    void weddingRegistrationSuccess() {
        Mockito.when(mockCitizen1.getSex()).thenReturn(MALE);
        Mockito.when(mockCitizen2.getSex()).thenReturn(FEMALE);
        LocalDate dateNow = LocalDate.now();

        Assertions.assertDoesNotThrow(
                () -> civilRegistryTest.weddingRegistration(mockCitizen1, mockCitizen2, dateNow));
    }

    @Test
    @DisplayName("Ошибки создание записей о свадьбе")
    void weddingRegistrationFailed() {
        Mockito.when(mockCitizen1.getSex()).thenReturn(MALE);
        Mockito.when(mockCitizen2.getSex()).thenReturn(FEMALE);
        Mockito.when(mockCitizen3.getSex()).thenReturn(FEMALE);
        Mockito.when(mockCitizen4.getSex()).thenReturn(FEMALE);
        Mockito.when(mockCitizen4.getFamilyStatus()).thenReturn(FamilyStatus.MARRIED);
        LocalDate dateNow = LocalDate.now();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> civilRegistryTest.weddingRegistration(mockCitizen1, mockCitizen1, dateNow));
        Assertions.assertThrows(SameSexException.class,
                () -> civilRegistryTest.weddingRegistration(mockCitizen2, mockCitizen3, dateNow));
        Assertions.assertThrows(RuntimeException.class,
                () -> civilRegistryTest.weddingRegistration(mockCitizen4, mockCitizen1, dateNow));
    }

    @Test
    @DisplayName("Создание записи о разводе без ошибок")
    void divorceRegistrationSuccess() {

        Mockito.when(mockCitizen1.getSex()).thenReturn(MALE);
        Mockito.when(mockCitizen2.getSex()).thenReturn(FEMALE);
        Mockito.when(mockCitizen1.getFamilyStatus()).thenReturn(FamilyStatus.MARRIED);
        Mockito.when(mockCitizen2.getFamilyStatus()).thenReturn(FamilyStatus.MARRIED);
        Mockito.when(mockCitizen1.getPartner()).thenReturn(mockCitizen2);
        Mockito.when(mockCitizen2.getPartner()).thenReturn(mockCitizen1);

        LocalDate dateNow = LocalDate.now();

        Assertions.assertDoesNotThrow(
                () -> civilRegistryTest.divorceRegistration(mockCitizen1, mockCitizen2, dateNow));
    }

    @Test
    @DisplayName("Ошибки при создании записи о разводе")
    void divorceRegistrationFailed() {
        Mockito.when(mockCitizen1.getSex()).thenReturn(MALE);
        Mockito.when(mockCitizen2.getSex()).thenReturn(FEMALE);
        Mockito.when(mockCitizen3.getSex()).thenReturn(FEMALE);
        Mockito.when(mockCitizen1.getFamilyStatus()).thenReturn(FamilyStatus.MARRIED);
        Mockito.when(mockCitizen2.getFamilyStatus()).thenReturn(FamilyStatus.SINGLE);
        Mockito.when(mockCitizen3.getFamilyStatus()).thenReturn(FamilyStatus.MARRIED);
        Mockito.when(mockCitizen1.getPartner()).thenReturn(mockCitizen2);
        Mockito.when(mockCitizen2.getPartner()).thenReturn(mockCitizen1);

        LocalDate dateNow = LocalDate.now();

        Assertions.assertThrows(RuntimeException.class,
                () -> civilRegistryTest.divorceRegistration(mockCitizen1, mockCitizen2, dateNow));

        Assertions.assertThrows(RuntimeException.class,
                () -> civilRegistryTest.divorceRegistration(mockCitizen1, mockCitizen3, dateNow));
    }

    @Test
    @DisplayName("Создание записи о рождении без ошибок")
    void birthRegistrationSuccess() {
        Citizen mockCitizen1 = Mockito.mock(Citizen.class);
        Citizen mockCitizen2 = Mockito.mock(Citizen.class);
        Human mockChild = Mockito.mock(Human.class);
        Mockito.when(mockCitizen1.getSex()).thenReturn(MALE);
        Mockito.when(mockCitizen2.getSex()).thenReturn(FEMALE);

        LocalDate dateNow = LocalDate.now();

        Assertions.assertDoesNotThrow(
                () -> civilRegistryTest.birthRegistration(mockChild, mockCitizen1, mockCitizen2, dateNow));
    }

    @Test
    @DisplayName("Создание записи о рождении c ошибками")
    void birthRegistrationFailed() {
        Citizen mockCitizen1 = Mockito.mock(Citizen.class);
        Citizen mockCitizen2 = Mockito.mock(Citizen.class);
        Human mockChild = Mockito.mock(Human.class);
        Mockito.when(mockCitizen1.getSex()).thenReturn(MALE);
        Mockito.when(mockCitizen2.getSex()).thenReturn(MALE);

        LocalDate dateNow = LocalDate.now();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> civilRegistryTest.birthRegistration(mockChild, mockCitizen1, mockCitizen1, dateNow));
        Assertions.assertThrows(SameSexException.class,
                () -> civilRegistryTest.birthRegistration(mockChild, mockCitizen1, mockCitizen2, dateNow));
    }

    @Test
    @DisplayName("Проверка вывода класса в строку")
    void civilRegistryTestToString() {
        String expectedString = "CivilRegistry(name=TEST, civilActionRecordsTree=[])";
        Assertions.assertEquals(expectedString, civilRegistryTest.toString());
    }

    @Test
    @DisplayName("Проверка вывода статистики без ошибок.")
    void getStatisticsSuccess() {
        Mockito.when(mockCitizen1.getSex()).thenReturn(MALE);
        Mockito.when(mockCitizen2.getSex()).thenReturn(FEMALE);
        civilRegistryTest.weddingRegistration(mockCitizen1, mockCitizen2, LocalDate.now().plusDays(2));

        Assertions.assertDoesNotThrow(() -> civilRegistryTest.getStatistics());
    }

    @Test
    @DisplayName("Проверка вывода корректной статистики.")
    void getCorrectStatisticsSuccess() {
        Citizen mockCitizen1 = Mockito.mock(Citizen.class);
        Citizen mockCitizen2 = Mockito.mock(Citizen.class);
        Mockito.when(mockCitizen1.getSex()).thenReturn(MALE);
        Mockito.when(mockCitizen2.getSex()).thenReturn(FEMALE);
        LocalDate registrationDate = LocalDate.of(2024, 3, 15);

        civilRegistryTest.weddingRegistration(mockCitizen1, mockCitizen2, registrationDate);

        String expectedString = "Статистика по ЗАГС: TEST\n" +
                "Дата 15/03/2024: Количество свадеб - 1, Количество разводов - 0, Количество рождений - 0";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        civilRegistryTest.getStatistics();
        String printedResult = outputStream.toString().trim();
        Assertions.assertEquals(expectedString, printedResult);
    }
}
