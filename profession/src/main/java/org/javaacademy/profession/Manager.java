package org.javaacademy.profession;

import lombok.NonNull;
import org.javaacademy.Sex;

public class Manager extends Employee {
    public Manager(@NonNull String name,
                   @NonNull String surname,
                   @NonNull String patronymic,
                   Sex sex) {
        super(name, surname, patronymic, sex);
        setRate(10_000);
    }
}
