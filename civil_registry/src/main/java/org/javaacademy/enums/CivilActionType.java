package org.javaacademy.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Перечисление, представляющее тип гражданского действия.
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum CivilActionType {
    BIRTH_REGISTRATION("Регистрация рождения"),
    WEDDING_REGISTRATION("Регистрация свадьбы"),
    DIVORCE_REGISTRATION("Регистрация развод");
    String actionType;
}
