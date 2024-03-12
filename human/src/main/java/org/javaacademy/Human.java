package org.javaacademy;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.javaacademy.exceptions.SameSexException;

@Getter
@ToString
public class Human {
    @NonNull private String firstName;
    @NonNull private String middleName;
    @NonNull private String lastName;
    @Getter
    @NonNull private final Sex sex;
    @Setter
    @ToString.Exclude
    private Human father;
    @Setter
    @ToString.Exclude
    private Human mother;
    @ToString.Exclude
    private List<Human> children = new ArrayList<>();

    public Human(@NonNull String firstName,
                 @NonNull String middleName,
                 @NonNull String lastName,
                 @NonNull Sex sex) {
        this.firstName = HumanUtil.capitalizeString(firstName);
        this.middleName = HumanUtil.capitalizeString(middleName);
        this.lastName = HumanUtil.capitalizeString(lastName);
        this.sex = sex;
    }

    public Human(Human human) {
        this.firstName = human.getFirstName();
        this.middleName = human.getMiddleName();
        this.lastName = human.getLastName();
        this.sex = human.getSex();
        this.father = human.getFather();
        this.mother = human.getMother();
    }

    /**
     * Устанавливает родителей данного человека и обновляет их списки детей.
     * Родители должны иметь разный пол.
     *
     * @param parent1 Первый родитель для установки.
     * @param parent2 Второй родитель для установки.
     * @throws SameSexException если оба родителя имеют одинаковый пол.
     */
    public void indicateParents(@NonNull Human parent1, @NonNull Human parent2) {
        if (HumanUtil.checkSameGender(parent1, parent2)) {
            throw new SameSexException("Родители не могут быть одного пола.");
        }

        this.father = parent1.getSex().equals(Sex.MALE) ? parent1 : parent2;
        this.mother = parent1.getSex().equals(Sex.MALE) ? parent2 : parent1;

        parent1.addChild(this);
        parent2.addChild(this);
    }

    /**
     * Создает и возвращает нового ребенка, связанного с текущим объектом Human и указанным партнером.
     * Проверяет, что родители имеют разный пол.
     *
     * @param firstName   Имя ребенка.
     * @param middleName  Отчество ребенка.
     * @param lastName    Фамилия ребенка.
     * @param sex         Пол ребенка.
     * @param partner     Партнер текущего объекта Human.
     * @return Вновь созданный объект Human, представляющий ребенка.
     * @throws NullPointerException если какой-либо из аргументов является null.
     * @throws SameSexException     если текущий объект Human и партнер имеют одинаковый пол.
     * @throws IllegalArgumentException     если текущий объект Human равен партнеру.
     */
    public Human makeChild(@NonNull String firstName,
                           @NonNull String middleName,
                           @NonNull String lastName,
                           @NonNull Sex sex,
                           @NonNull Human partner) {

        if (this == partner) {
            throw new IllegalArgumentException("Нельзя создать ребенка с самим собой.");
        }

        if (HumanUtil.checkSameGender(this, partner)) {
            throw new SameSexException("Родители не могут быть одного пола.");
        }

        Human child = new Human(firstName, lastName, middleName, sex);

        child.indicateParents(this, partner);
        children.add(child);
        partner.addChild(child);
        return child;
    }

    /**
     * Возвращает полное имя человека, состоящее из его фамилии, имени и отчества.
     *
     * @return Полное имя человека в формате "Фамилия Имя Отчество ".
     */
    public String getFullName() {
        return String.format("%s %s %s", lastName, firstName, middleName);
    }

    /**
     * Добавляет указанного ребенка к списку детей данного человека.
     *
     * @param child Ребенок, которого необходимо добавить к списку детей данного человека.
     */
    private void addChild(@NonNull Human child) {
        this.children.add(child);
    }
}
