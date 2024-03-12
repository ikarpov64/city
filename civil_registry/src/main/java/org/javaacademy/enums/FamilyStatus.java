package org.javaacademy.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Перечисление, представляющее семейный статус гражданина.
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum FamilyStatus {
    SINGLE("Одинокий"), DIVORCED("Разведен"), MARRIED("Состоит в браке");
    String familyStatus;
}
