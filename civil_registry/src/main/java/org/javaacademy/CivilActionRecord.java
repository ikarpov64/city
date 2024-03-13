package org.javaacademy;

import java.time.LocalDate;
import java.util.List;
import lombok.Value;
import org.javaacademy.enums.CivilActionType;

/**
 * Представляет запись о гражданском действии.
 */
@Value
public class CivilActionRecord {
    CivilActionType civilActionType;
    List<Citizen> personList;
    LocalDate date;
}
