package org.javaacademy.profession;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.javaacademy.Sex;
import org.javaacademy.task.Task;
import org.javaacademy.task.TaskAssignable;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Programmer extends Employee implements TaskAssignable {
    static int MIN_RATE = 1_500;
    static int MAX_RATE = 2_000;

    public Programmer(@NonNull String firstName,
                      @NonNull String middleName,
                      @NonNull String lastName,
                      @NonNull Sex sex) {
        super(firstName, middleName, lastName, sex);
    }

    @Override
    public void setRate(int rate) {
        if (rate >= MIN_RATE && rate <= MAX_RATE) {
            super.setRate(rate);
            return;
        }
        throw new IllegalArgumentException("Ставка от 1500 до 2000 рублей");
    }

    @Override
    public void acceptsTask(Task task) {
        task.setDone(true);
        System.out.println(task.getDescription() + " - сделана");
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
