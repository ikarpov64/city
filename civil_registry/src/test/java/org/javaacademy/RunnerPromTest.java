package org.javaacademy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RunnerPromTest {
    @Test
    @DisplayName("Тестирование пром раннера без ошибок")
    void RunnerTestSuccess() {
        Assertions.assertDoesNotThrow(() -> RunnerProm.main(new String[]{"Центральный"}));
    }

    @Test
    @DisplayName("Тестирование пром раннера c ошибкой")
    void RunnerTestFailed() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> RunnerProm.main(new String[]{}));
    }
}
