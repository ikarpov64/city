package org.javaacademy;

import static org.javaacademy.Sex.FEMALE;
import static org.javaacademy.Sex.MALE;
import static org.javaacademy.enums.CivilActionType.BIRTH_REGISTRATION;

import org.javaacademy.enums.FamilyStatus;
import org.javaacademy.exceptions.SameSexException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.List;

@DisplayName("Тестирование ЗАГСа")
public class CivilRegistryTest {
    private static CivilRegistry civilRegistryTest = new CivilRegistry("TEST");
    private static Citizen mockCitizen1 = Mockito.mock(Citizen.class);
    private static Citizen mockCitizen2 = Mockito.mock(Citizen.class);
    private static Citizen mockCitizen3 = Mockito.mock(Citizen.class);
    private static Citizen mockCitizen4 = Mockito.mock(Citizen.class);

    @Test
    @DisplayName("Свадьба без ошибок.")
    void weddingRegistrationSuccess() {
        Mockito.when(mockCitizen1.getSex()).thenReturn(MALE);
        Mockito.when(mockCitizen2.getSex()).thenReturn(FEMALE);
        LocalDate dateNow = LocalDate.now();

        Assertions.assertDoesNotThrow(
                () -> civilRegistryTest.weddingRegistration(mockCitizen1, mockCitizen2, dateNow));
    }

    @Test
    @DisplayName("Свадьба с ошибками")
    void weddingRegistrationFailed() {
        Mockito.when(mockCitizen1.getSex()).thenReturn(MALE);
        Mockito.when(mockCitizen2.getSex()).thenReturn(FEMALE);
        Mockito.when(mockCitizen3.getSex()).thenReturn(FEMALE);
        Mockito.when(mockCitizen4.getSex()).thenReturn(FEMALE);
        Mockito.when(mockCitizen1.getFamilyStatus()).thenReturn(FamilyStatus.DIVORCED);
        Mockito.when(mockCitizen4.getFamilyStatus()).thenReturn(FamilyStatus.MARRIED);
        LocalDate dateNow = LocalDate.now();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> civilRegistryTest.weddingRegistration(mockCitizen1, mockCitizen1, dateNow));
        Assertions.assertThrows(SameSexException.class,
                () -> civilRegistryTest.weddingRegistration(mockCitizen2, mockCitizen3, dateNow));
        Assertions.assertThrows(RuntimeException.class,
                () -> civilRegistryTest.weddingRegistration(mockCitizen4, mockCitizen1, dateNow));
        Assertions.assertThrows(RuntimeException.class,
                () -> civilRegistryTest.weddingRegistration(mockCitizen1, mockCitizen4, dateNow));
        Assertions.assertThrows(NullPointerException.class,
                () -> civilRegistryTest.weddingRegistration(null, mockCitizen1, dateNow));
        Assertions.assertThrows(NullPointerException.class,
                () -> civilRegistryTest.weddingRegistration(mockCitizen1, null, dateNow));
        Assertions.assertThrows(NullPointerException.class,
                () -> civilRegistryTest.weddingRegistration(mockCitizen1, mockCitizen2, null));
    }

    @Test
    @DisplayName("Установка статусов граждан без ошибок")
    void setCitizenStatusSuccess() {
        Assertions.assertDoesNotThrow(
                () -> civilRegistryTest.setCitizenStatus(mockCitizen1, mockCitizen1, FamilyStatus.DIVORCED));
    }

    @Test
    @DisplayName("Установка статусов граждан ошибками")
    void setCitizenStatusFailed() {
        Assertions.assertThrows(NullPointerException.class,
                () -> civilRegistryTest.setCitizenStatus(null, mockCitizen1, FamilyStatus.DIVORCED));
        Assertions.assertThrows(NullPointerException.class,
                () -> civilRegistryTest.setCitizenStatus(mockCitizen1, null, FamilyStatus.DIVORCED));
        Assertions.assertThrows(NullPointerException.class,
                () -> civilRegistryTest.setCitizenStatus(mockCitizen1, mockCitizen2, null));
    }

    @Test
    @DisplayName("Развод без ошибок")
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
    @DisplayName("Развод с ошибками")
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
                () -> civilRegistryTest.divorceRegistration(mockCitizen2, mockCitizen1, dateNow));
        Assertions.assertThrows(RuntimeException.class,
                () -> civilRegistryTest.divorceRegistration(mockCitizen1, mockCitizen3, dateNow));
        Assertions.assertThrows(NullPointerException.class,
                () -> civilRegistryTest.divorceRegistration(null, mockCitizen3, dateNow));
        Assertions.assertThrows(NullPointerException.class,
                () -> civilRegistryTest.divorceRegistration(mockCitizen1, null, dateNow));
        Assertions.assertThrows(NullPointerException.class,
                () -> civilRegistryTest.divorceRegistration(mockCitizen1, mockCitizen3, null));
    }

    @Test
    @DisplayName("Добавление записи в реестр с ошибкой.")
    void addCivilActionReportFailed() {
        Assertions.assertThrows(NullPointerException.class,
                () -> civilRegistryTest.addCivilActionReport(null));
    }

    @Test
    @DisplayName("Добавление записи в реестр без ошибки.")
    void addCivilActionReportSuccess() {
        Citizen mockCitizen1 = Mockito.mock(Citizen.class);
        Citizen mockCitizen2 = Mockito.mock(Citizen.class);
        Citizen mockChild = Mockito.mock(Citizen.class);
        Mockito.when(mockCitizen1.getSex()).thenReturn(MALE);
        Mockito.when(mockCitizen2.getSex()).thenReturn(FEMALE);
        CivilActionRecord civilActionRecord = new CivilActionRecord(
                BIRTH_REGISTRATION,
                List.of(mockChild, mockCitizen1, mockCitizen2), LocalDate.now());

        Assertions.assertDoesNotThrow(() -> civilRegistryTest.addCivilActionReport(civilActionRecord));
    }

    @Test
    @DisplayName("Рождение ребенка без ошибок")
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
    @DisplayName("Рождение ребенка с ошибками")
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
        Assertions.assertThrows(NullPointerException.class,
                () -> civilRegistryTest.birthRegistration(null, mockCitizen1, mockCitizen2, dateNow));
        Assertions.assertThrows(NullPointerException.class,
                () -> civilRegistryTest.birthRegistration(mockChild, null, mockCitizen2, dateNow));
        Assertions.assertThrows(NullPointerException.class,
                () -> civilRegistryTest.birthRegistration(mockChild, mockCitizen1, null, dateNow));
        Assertions.assertThrows(NullPointerException.class,
                () -> civilRegistryTest.birthRegistration(mockChild, mockCitizen1, mockCitizen2, null));
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
}
