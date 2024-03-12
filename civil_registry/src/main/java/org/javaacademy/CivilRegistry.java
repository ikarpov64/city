package org.javaacademy;

import static org.javaacademy.enums.CivilActionType.*;
import static org.javaacademy.enums.FamilyStatus.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.javaacademy.enums.CivilActionType;
import org.javaacademy.enums.FamilyStatus;
import org.javaacademy.exceptions.SameSexException;


/**
 * Класс, представляющий реестр гражданских действий (ЗАГС).
 * Содержит функции для регистрации различных гражданских событий,
 * управления статусом граждан и предоставления статистики.
 */
@ToString
@Getter
@RequiredArgsConstructor
public class CivilRegistry {
    private final String name;
    private Set<CivilActionRecord> civilActionRecordsTree = new TreeSet<>(new CivilActionComparator());

    /**
     * Регистрирует рождение ребенка с указанными данными о гражданах и датой регистрации.
     *
     * @param child    Ребенок, для которого происходит регистрация рождения.
     * @param citizen1 Первый гражданин, родитель ребенка.
     * @param citizen2 Второй гражданин, родитель ребенка.
     * @param date     Дата регистрации рождения.
     * @throws SameSexException если родители имеют одинаковый пол.
     * @throws IllegalArgumentException если родители это один объект.
     * @throws NullPointerException     если один из аргументов является null.
     */
    public void birthRegistration(@NonNull Human child, @NonNull Citizen citizen1,
                                  @NonNull Citizen citizen2, @NonNull LocalDate date) {
        if (citizen1 == citizen2) {
            throw new IllegalArgumentException("У ребенка должно быть два родителя.");
        }

        if (CivilRegistryUtil.checkCitizensGender(citizen1, citizen2)) {
            throw new SameSexException("Родители не могут быть одного пола.");
        }

        // Создаем объект гражданина на основе ребенка
        Citizen citizenChild = new Citizen(child);
        CivilActionRecord civilActionRecord = new CivilActionRecord(
                BIRTH_REGISTRATION,
                List.of(citizenChild, citizen1, citizen2), date);
        addCivilActionReport(civilActionRecord);
    }

    /**
     * Регистрирует свадьбу между двумя гражданами с указанной датой.
     *
     * @param citizen1 Первый гражданин, участвующий в свадьбе.
     * @param citizen2 Второй гражданин, участвующий в свадьбе.
     * @param date     Дата регистрации свадьбы.
     * @throws SameSexException если граждане имеют одинаковый пол.
     * @throws IllegalArgumentException если родители это один объект.
     * @throws RuntimeException если первый или второй гражданин уже в браке.
     * @throws NullPointerException     если один из аргументов является null.
     */
    public void weddingRegistration(@NonNull Citizen citizen1,
                                    @NonNull Citizen citizen2,
                                    @NonNull LocalDate date) {
        if (citizen1 == citizen2) {
            throw new IllegalArgumentException("Брак с самим собой невозможен");
        }

        if (CivilRegistryUtil.checkCitizensGender(citizen1, citizen2)) {
            throw new SameSexException("Однополые браки запрещены.");
        }

        if (CivilRegistryUtil.checkMarriageStatus(citizen1)
                || CivilRegistryUtil.checkMarriageStatus(citizen2)) {
            throw new RuntimeException("Граждане не должны состоять в браке.");
        }

        setCitizenStatus(citizen1, citizen2, MARRIED);

        CivilActionRecord civilActionRecord = new CivilActionRecord(
                WEDDING_REGISTRATION, List.of(citizen1, citizen2), date);
        addCivilActionReport(civilActionRecord);
    }

    /**
     * Регистрирует развод между двумя гражданами с указанной датой.
     *
     * @param citizen1 Первый гражданин, участвующий в разводе.
     * @param citizen2 Второй гражданин, участвующий в разводе.
     * @param date     Дата регистрации развода.
     * @throws NullPointerException     если какой-либо из аргументов является null.
     * @throws RuntimeException         если граждане не состоят в браке
     *                                  или не в браке друг с другом.
     */
    public void divorceRegistration(@NonNull Citizen citizen1,
                                    @NonNull Citizen citizen2,
                                    @NonNull LocalDate date) {
        if (!CivilRegistryUtil.checkMarriageStatus(citizen1)
                || !CivilRegistryUtil.checkMarriageStatus(citizen2)) {
            throw new RuntimeException("Граждане должны состоять в браке.");
        }
        if (!CivilRegistryUtil.checkCitizensAreMarried(citizen1, citizen2)) {
            throw new RuntimeException("Граждане не состоят в браке друг с другом.");
        }

        setCitizenStatus(citizen1, citizen2, DIVORCED);
        CivilActionRecord civilActionRecord = new CivilActionRecord(
                DIVORCE_REGISTRATION, List.of(citizen1, citizen2), date);
        addCivilActionReport(civilActionRecord);
    }

    /**
     * Устанавливает семейный статус для указанных граждан и обновляет информацию о партнерах при необходимости.
     *
     * @param citizen1 Первый гражданин.
     * @param citizen2 Второй гражданин.
     * @param status   Новый семейный статус для граждан.
     * @throws NullPointerException если какой-либо из аргументов является null.
     */
    private void setCitizenStatus(@NonNull Citizen citizen1,
                                  @NonNull Citizen citizen2,
                                  @NonNull FamilyStatus status) {
        List.of(citizen1, citizen2).forEach(citizen -> {
            citizen.setFamilyStatus(status);
            if (status == MARRIED) {
                citizen.setPartner(citizen == citizen1 ? citizen2 : citizen1);
            } else if (status == DIVORCED) {
                citizen.setPartner(null);
            }
        });
    }

    /**
     * Добавляет запись о гражданском действии в реестр.
     *
     * @param civilActionRecord Запись о гражданском действии.
     */
    private void addCivilActionReport(@NonNull CivilActionRecord civilActionRecord) {
        civilActionRecordsTree.add(civilActionRecord);
    }

    /**
     * Выводит статистику по ЗАГС (Запись актов гражданского действия),
     * включая количество проведенных свадеб,
     * разводов и рождений, сгруппированных по датам.
     */
    public void getStatistics() {

        System.out.printf("Статистика по ЗАГС: %S\n", name);

        // Группируем записи о гражданских действиях по датам и типам действий
        Map<LocalDate, Map<CivilActionType, Integer>> countsByDateAndType =
                civilActionRecordsTree.stream()
                        .collect(Collectors.groupingBy(
                                CivilActionRecord::getDate,
                                Collectors.groupingBy(
                                        CivilActionRecord::getCivilActionType,
                                        Collectors.summingInt(e -> 1)
                                )
                        ));
        // Сортируем записи по датам
        TreeMap<LocalDate, Map<CivilActionType, Integer>> localDateMapTreeMap =
                new TreeMap<>(countsByDateAndType);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Выводим статистику по каждой дате
        for (LocalDate date : localDateMapTreeMap.keySet()) {
            System.out.printf("Дата %s: ", date.format(formatter));
            Map<CivilActionType, Integer> countsByType = countsByDateAndType.get(date);
            System.out.printf("Количество свадеб - %s, Количество разводов - %s, Количество рождений - %s\n",
                    countsByType.getOrDefault(WEDDING_REGISTRATION, 0),
                    countsByType.getOrDefault(DIVORCE_REGISTRATION, 0),
                    countsByType.getOrDefault(BIRTH_REGISTRATION, 0));
        }
    }
}
