package org.javaacademy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CitizenTest {

    @Test
    @DisplayName("Вывод гражданина в строку")
    void toStringTest() {
        String expectedString = "Citizen(familyStatus=SINGLE)";
        Citizen citizen = new Citizen("test", "test", "test", Sex.MALE);
        String actualString = citizen.toString();
        Assertions.assertEquals(expectedString, actualString);
    }
}
