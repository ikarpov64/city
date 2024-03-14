package org.javaacademy.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирования аргументов запуска Runner")
public class RunnerTest {
    private static final String COMPANY_NAME = "Oracle";
    private static final String PROGRAMMER_RATE = "1600";

    @Test
    @DisplayName("Тестирование раннера без ошибок")
    void runnerTestSuccess() {
        Assertions.assertDoesNotThrow(() -> Runner.main(new String[]{COMPANY_NAME, PROGRAMMER_RATE}));
    }

    @Test
    @DisplayName("Тестирование раннера без аргументов")
    void runnerTestWithoutArguments() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Runner.main(new String[]{}));
    }

    @Test
    @DisplayName("Тестирование раннера c одним аргументом")
    void runnerTestWithOneArgument() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Runner.main(new String[]{COMPANY_NAME}));
    }

    @Test
    @DisplayName("Тестирование раннера c тремя аргументом")
    void runnerTestWithThreeArgument() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Runner.main(new String[]{COMPANY_NAME, PROGRAMMER_RATE, "invalidData"}));
    }

    @Test
    @DisplayName("Тестирование раннера c пустым именем компании одним аргументом")
    void runnerTestWithInvalidDataCompanyName() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Runner.main(new String[]{"", PROGRAMMER_RATE}));
    }

    @Test
    @DisplayName("Тестирование раннера c валидным названием компании и невалидным ставкой")
    void runnerTestWithInvalidProgrammerRate() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Runner.main(new String[]{COMPANY_NAME, "qwer32"}));
    }
}
