package org.javaacademy.profession;

import lombok.NonNull;
import org.javaacademy.Sex;

public class Manager extends Employee {
    private static final int MANAGER_RATE = 10_000;

    public Manager(@NonNull String firstName,
                   @NonNull String middleName,
                   @NonNull String lastName,
                   @NonNull Sex sex) {
        super(firstName, middleName, lastName, sex);
        setRate(MANAGER_RATE);
    }
}
