package org.javaacademy.task;

import lombok.Data;
import lombok.NonNull;

@Data
public class Task {
    @NonNull
    final String description;
    final int numberHoursPerTask;
    boolean isDone;
}
