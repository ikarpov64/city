package org.javaacademy;

import org.javaacademy.enums.FamilyStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование утилитарного класса CivilRegistryUtil")
public class CivilRegistryUtilTest {

    @Test
    @DisplayName("Проверка на одинаковый пол у граждан.")
    void checkCitizensGenderTrue() {
        Citizen citizen1 = new Citizen("test", "test", "test", Sex.MALE);
        Citizen citizen2 = new Citizen("test", "test", "test", Sex.MALE);
        Citizen citizen3 = new Citizen("test", "test", "test", Sex.FEMALE);

        Assertions.assertTrue(CivilRegistryUtil.checkCitizensGender(citizen1, citizen2));
        Assertions.assertFalse(CivilRegistryUtil.checkCitizensGender(citizen1, citizen3));
        Assertions.assertThrows(NullPointerException.class,
                () -> CivilRegistryUtil.checkCitizensGender(citizen1, null));
        Assertions.assertThrows(NullPointerException.class,
                () -> CivilRegistryUtil.checkCitizensGender(null, citizen1));
    }

    @Test
    @DisplayName("Проверяет семейный статус гражданина на наличие брака.")
    void checkMarriageStatusTrue() {
        Citizen citizen1 = new Citizen("test", "test", "test", Sex.MALE);
        Citizen citizen2 = new Citizen("test", "test", "test", Sex.MALE);
        citizen1.setFamilyStatus(FamilyStatus.MARRIED);
        citizen2.setFamilyStatus(FamilyStatus.DIVORCED);

        Assertions.assertTrue(CivilRegistryUtil.checkMarriageStatus(citizen1));
        Assertions.assertFalse(CivilRegistryUtil.checkMarriageStatus(citizen2));
        Assertions.assertThrows(NullPointerException.class,
                () -> CivilRegistryUtil.checkMarriageStatus(null));
    }

    @Test
    @DisplayName("Проверка что два гражданина в браке друг с другом.")
    public void checkCitizensAreMarriedTrue() {
        Citizen citizen1 = new Citizen("test", "test", "test", Sex.MALE);
        Citizen citizen2 = new Citizen("test", "test", "test", Sex.FEMALE);
        Citizen citizen3 = new Citizen("test", "test", "test", Sex.MALE);
        citizen1.setPartner(citizen2);
        citizen2.setPartner(citizen1);

        Assertions.assertTrue(CivilRegistryUtil.checkCitizensAreMarried(citizen1, citizen2));
        Assertions.assertFalse(CivilRegistryUtil.checkCitizensAreMarried(citizen1, citizen3));
        Assertions.assertThrows(NullPointerException.class,
                () -> CivilRegistryUtil.checkCitizensAreMarried(null, null));
        Assertions.assertThrows(NullPointerException.class,
                () -> CivilRegistryUtil.checkCitizensAreMarried(null, citizen1));
        Assertions.assertThrows(NullPointerException.class,
                () -> CivilRegistryUtil.checkCitizensAreMarried(citizen1, null));
    }
}
