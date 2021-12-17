package ru.netology.domain;

import lombok.Data;

import java.util.Objects;

@Data
public class SmartPhone extends Product {

    private String producer;

    public SmartPhone() {
        super();
    }

    public SmartPhone(int id, String name, int price, String producer) {
        super(id, name, price);
        this.producer = producer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SmartPhone phone = (SmartPhone) o;
        return Objects.equals(producer, phone.producer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), producer);
    }

    @Override
    public String toString() {
        return "SmartPhone{" +
                "producer='" + producer + '\'' +
                '}';
    }
}
