package org.javaacademy;

import org.javaacademy.enums.FamilyStatus;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CivilRegistryUtil {

    /**
     * Проверяет, имеют ли два гражданина одинаковый пол.
     *
     * @param citizen1 Первый гражданин для проверки.
     * @param citizen2 Второй гражданин для проверки.
     * @return true, если пол граждан одинаков, иначе false.
     * @throws NullPointerException если один из переданных граждан является null.
     */
    boolean checkCitizensGender(@NonNull Citizen citizen1, @NonNull Citizen citizen2) {
        return citizen1.getSex().equals(citizen2.getSex());
    }

    /**
     * Проверяет семейный статус гражданина на наличие брака.
     *
     * @param citizen Гражданин, чей семейный статус проверяется.
     * @return true, если семейный статус гражданина равен "Состоит в браке" (MARRIED), иначе false.
     * @throws NullPointerException если переданный гражданин является null.
     */
    boolean checkMarriageStatus(@NonNull Citizen citizen) {
        return FamilyStatus.MARRIED.equals(citizen.getFamilyStatus());
    }

    /**
     * Проверяет, состоят ли два гражданина в браке друг с другом.
     *
     * @param citizen1 Первый гражданин для проверки.
     * @param citizen2 Второй гражданин для проверки.
     * @return true, если первый гражданин имеет партнера и этот партнер равен второму гражданину, иначе false.
     * @throws NullPointerException если один из переданных граждан является null.
     */
    boolean checkCitizensAreMarried(@NonNull Citizen citizen1, @NonNull Citizen citizen2) {
        return citizen1.getPartner() != null
                && citizen1.getPartner().equals(citizen2);
    }
}
