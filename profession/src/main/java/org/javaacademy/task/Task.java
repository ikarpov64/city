package org.javaacademy.task;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter()
public class Task {
    final String description;
    final int numberHoursPerTask;
    @Setter
    boolean isDone;

    public Task(@NonNull String description, int numberHoursPerTask) {
        if (numberHoursPerTask < 0) {
            throw new IllegalArgumentException("Время выполнения задачи не может быть меньше нуля");
        }
        this.description = description;
        this.numberHoursPerTask = numberHoursPerTask;
    }
}
