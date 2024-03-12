package org.javaacademy;

import org.javaacademy.enums.CivilActionType;
import java.time.LocalDate;
import java.util.List;
import lombok.Value;

/**
 * Представляет запись о гражданском действии.
 */
@Value
public class CivilActionRecord {
    CivilActionType civilActionType;
    List<Citizen> personList;
    LocalDate date;
}
