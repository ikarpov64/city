package org.javaacademy.profession;

import lombok.NonNull;
import org.javaacademy.Sex;

public class Manager extends Employee {
    public Manager(@NonNull String firstName,
                   @NonNull String middleName,
                   @NonNull String lastName,
                   @NonNull Sex sex) {
        super(firstName, middleName, lastName, sex);
        setRate(10_000);
    }
}
