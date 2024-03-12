package org.javaacademy;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.javaacademy.enums.FamilyStatus;

/**
 * Представляет гражданина с указанными персональными данными и семейным статусом.
 * Класс наследует основные характеристики человека из класса Human.
 */
@Getter
@Setter
@ToString
public class Citizen extends Human {
    private FamilyStatus familyStatus = FamilyStatus.SINGLE;
    @ToString.Exclude
    private Citizen partner;

    public Citizen(@NonNull String firstName, @NonNull String middleName, @NonNull String lastName, @NonNull Sex sex) {
        super(firstName, middleName, lastName, sex);
    }

    /**
     * Создает нового гражданина на основе существующего объекта Human.
     *
     * @param human Объект Human, на основе которого создается гражданин.
     * @throws NullPointerException если переданный объект Human является null.
     */
    public Citizen(@NonNull Human human) {
        super(human);
    }
}
