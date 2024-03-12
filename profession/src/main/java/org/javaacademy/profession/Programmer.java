package org.javaacademy.profession;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.javaacademy.Sex;
import org.javaacademy.task.Task;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Programmer extends Employee {
    static int MIN_RATE = 1_500;
    static int MAX_RATE = 2_000;

    public Programmer(@NonNull String name,
                      @NonNull String surname,
                      @NonNull String patronymic,
                      Sex sex) {
        super(name, surname, patronymic, sex);
    }

    @Override
    public void setRate(int rate) {
        if (rate >= MIN_RATE && rate <= MAX_RATE) {
            super.setRate(rate);
            return;
        }
        throw new IllegalArgumentException("Ставка от 1500 до 2000 рублей");
    }

    public void acceptsTask(Task task) {
        task.setDone(true);
        System.out.println(task.getName() + "- сделана");
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
