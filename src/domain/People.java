package domain;

import java.io.Serializable;

/**
 * @author Mr.MC
 */
public class People implements Serializable {
    private String name;
    private Character sex;
    private Animal animal;

    public People() {
    }

    public People(String name, Character sex, Animal animal) {
        this.name = name;
        this.sex = sex;
        this.animal = animal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", animal=" + animal +
                '}';
    }
}
