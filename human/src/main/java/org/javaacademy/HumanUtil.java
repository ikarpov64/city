package org.javaacademy;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.javaacademy.exceptions.SameSexException;

@UtilityClass
public class HumanUtil {

    /**
     * Проверяет, имеют ли два человека одинаковый пол.
     *
     * @param human1 Первый объект типа Human для сравнения пола.
     * @param human2 Второй объект типа Human для сравнения пола.
     * @throws RuntimeException если оба объекта имеют одинаковый пол.
     */
    boolean checkSameGender(@NonNull Human human1, @NonNull Human human2) {
        return human1.getSex().equals(human2.getSex());
    }

    /**
     * Возвращает строку, в которой первая буква строки написана заглавной, а остальные буквы в нижнем регистре.
     * Лишние пробелы в начале и конце строки удаляются.
     *
     * @param string Строка, которую нужно преобразовать.
     * @return Преобразованная строка с первой буквой в верхнем регистре и остальными буквами в нижнем регистре.
     * @throws NullPointerException если входная строка является null.
     */
    String capitalizeString(@NonNull String string) {
        return StringUtils.capitalize(StringUtils.trimToEmpty(string).toLowerCase());
    }
}
