package org.javaacademy;

import java.util.Comparator;
import lombok.NonNull;

/**
 * Сравнивает записи о гражданском действии на основе их даты и хэш-кода.
 */
public class CivilActionComparator implements Comparator<CivilActionRecord> {

    /**
     * Сравнивает две записи о гражданском действии на основе их даты и хэш-кода.
     *
     * @param o1 Первая запись о гражданском действии для сравнения.
     * @param o2 Вторая запись о гражданском действии для сравнения.
     * @return Значение меньше нуля, если первая запись предшествует второй в порядке сортировки.
     *         Ноль, если обе записи равны.
     *         Значение больше нуля, если вторая запись предшествует первой в порядке сортировки.
     * @throws NullPointerException если один из аргументов является null.
     */
    @Override
    public int compare(@NonNull CivilActionRecord o1, @NonNull CivilActionRecord o2) {
        // Сравниваем записи по дате
        int dateComparison = o1.getDate().compareTo(o2.getDate());
        if (dateComparison != 0) {
            return dateComparison;
        }
        // Если даты равны, сравниваем записи по их хэш-кодам
        return o1.hashCode() - o2.hashCode();
    }
}

