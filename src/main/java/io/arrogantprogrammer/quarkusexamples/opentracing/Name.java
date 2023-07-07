package io.arrogantprogrammer.quarkusexamples.opentracing;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Name extends PanacheEntity {

    String value;

    public Name() {
    }

    @Override
    public String toString() {
        return "Name{" +
                "value='" + value + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(id, name.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
