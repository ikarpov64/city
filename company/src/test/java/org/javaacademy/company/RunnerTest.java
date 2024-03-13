package org.javaacademy.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RunnerTest {
    private static final String COMPANY_NAME = "Oracle";
    private static final String PROGRAMMER_RATE = "1600";
    @Test
    @DisplayName("Тестирование раннера без ошибок")
    void RunnerTestSuccess() {
        Assertions.assertDoesNotThrow(() -> Runner.main(new String[]{COMPANY_NAME, PROGRAMMER_RATE}));
    }

    @Test
    @DisplayName("Тестирование раннера без аргументов")
    void RunnerTestWithoutArguments() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Runner.main(new String[]{}));
    }

    @Test
    @DisplayName("Тестирование раннера c одним аргументом")
    void RunnerTestWithOneArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Runner.main(new String[]{COMPANY_NAME}));
    }

    @Test
    @DisplayName("Тестирование раннера c тремя аргументом")
    void RunnerTestWithThreeArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Runner.main(new String[]{COMPANY_NAME, PROGRAMMER_RATE, "invalidData"}));
    }

    @Test
    @DisplayName("Тестирование раннера c пустым именем компании одним аргументом")
    void RunnerTestWithInvalidDataCompanyName() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Runner.main(new String[]{"", PROGRAMMER_RATE}));
    }

    @Test
    @DisplayName("Тестирование раннера c валидным названием компании и невалидным ставкой")
    void RunnerTestWithInvalidProgrammerRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Runner.main(new String[]{COMPANY_NAME, "qwer32"}));
    }
}
