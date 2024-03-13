package org.javaacademy;

import java.time.LocalDate;

public class RunnerProm {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Необходимо название ЗАГСа в качестве параметра.");
        }

        String civilRegistryName = args[0];
        CivilRegistry civilRegistry = new CivilRegistry(civilRegistryName);

        // Мужчины
        Citizen eddardStark = new Citizen("Эддард",
                "Рикардович", "Старк", Sex.MALE);
        Citizen jonSnow = new Citizen("Джон",
                "Рейегарович", "Сноу", Sex.MALE);
        Citizen jaimeLanister = new Citizen("Джейме",
                "Тайвинович", "Ланистер", Sex.MALE);

        // Женщины
        Citizen catelynStark  = new Citizen(
                "Кейтилин", "Хостеровна", "Старк", Sex.FEMALE);
        Citizen cerseiLanister  = new Citizen(
                "Серсея", "Тайвиновна", "Ланистер", Sex.FEMALE);
        Citizen daenerysTargaryen = new Citizen(
                "Дейнерис", "Эйгоновна", "Таргариен", Sex.FEMALE);

        // Дети
        Human aryaStark = catelynStark.makeChild(
                "Арья", "Эддардовна", "Старк", Sex.FEMALE, eddardStark);
        Human sansaStark = catelynStark.makeChild(
                "Санса", "Эддардовна", "Старк", Sex.FEMALE, eddardStark);
        Human joffreyBaratheon = cerseiLanister.makeChild(
                "Джофри", "Джеймович", "Ланистер", Sex.MALE, jaimeLanister);

        // Создаем события в ЗАГСе, 2 свадьбы. 1 развод, 3 рождения ребенка.
        civilRegistry.weddingRegistration(jonSnow, daenerysTargaryen, LocalDate.now());
        civilRegistry.weddingRegistration(jaimeLanister, cerseiLanister, LocalDate.now());
        civilRegistry.divorceRegistration(jaimeLanister, cerseiLanister, LocalDate.now());
        civilRegistry.birthRegistration(aryaStark, eddardStark, catelynStark, LocalDate.now());
        civilRegistry.birthRegistration(sansaStark, eddardStark, catelynStark, LocalDate.now());
        civilRegistry.birthRegistration(joffreyBaratheon, jaimeLanister, cerseiLanister, LocalDate.now());

        // Получаем статистику по ЗАГСу.
        civilRegistry.getStatistics();
    }
}
