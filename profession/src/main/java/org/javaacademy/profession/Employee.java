package org.javaacademy.profession;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.javaacademy.Human;
import org.javaacademy.Sex;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public  class Employee extends Human {
    double moneyEarned;
    int rate;

    public Employee(@NonNull String name,
                    @NonNull String surname,
                    @NonNull String patronymic,
                    Sex sex) {
        super(name, surname, patronymic, sex);
    }
}
