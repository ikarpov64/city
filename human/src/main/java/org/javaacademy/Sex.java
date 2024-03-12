package org.javaacademy;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Перечисление, представляющее пол человека.
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum Sex {
    MALE("Мужчина"), FEMALE("Женщина");
    String sex;
}
