package org.javaacademy.profession;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.javaacademy.Sex;
import org.javaacademy.task.Task;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Programmer extends Employee {

    public Programmer(@NonNull String name,
                      @NonNull String surname,
                      @NonNull String patronymic,
                      Sex sex) {
        super(name, surname, patronymic, sex);
    }

    @Override
    public void setRate(int rate) {
        if (rate >= 1500 && rate <= 2000) {
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
