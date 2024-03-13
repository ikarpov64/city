package org.javaacademy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RunnerTestTest {

    @Test
    @DisplayName("Тестирование тестового раннера без ошибок")
    void RunnerTestSuccess() {
        Assertions.assertDoesNotThrow(() -> RunnerTest.main(new String[]{}));
    }
}
