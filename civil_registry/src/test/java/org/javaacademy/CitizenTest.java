package org.javaacademy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CitizenTest {

    @Test
    @DisplayName("Проверка создания гражданина с null в качестве параметров")
    void newCitizenFailed() {
        Assertions.assertThrows(NullPointerException.class,
                () -> new Citizen(null, "null", "null", Sex.MALE));
        Assertions.assertThrows(NullPointerException.class,
                () -> new Citizen("null", null, "null", Sex.MALE));
        Assertions.assertThrows(NullPointerException.class,
                () -> new Citizen("null", "null", null, Sex.MALE));
        Assertions.assertThrows(NullPointerException.class,
                () -> new Citizen("null", "null", "null", null));
        Assertions.assertThrows(NullPointerException.class,
                () -> new Citizen(null, null, null, null));
    }

    @Test
    @DisplayName("Проверка создания гражданина на основе Human с null в качестве параметра")
    void newCitizenBasedHumanFailed() {
        Assertions.assertThrows(NullPointerException.class,
                () -> new Citizen(null));
    }

    @Test
    @DisplayName("Проверка создания гражданина на основе Human")
    void newCitizenBasedHumanSuccess() {
        Human humanMock = Mockito.mock(Human.class);
        Assertions.assertDoesNotThrow(() -> new Citizen(humanMock));
    }
}
