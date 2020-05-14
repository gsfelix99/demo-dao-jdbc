package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Departmet implements Serializable {
    private int id;
    private String name;

    public Departmet() {
    }

    public Departmet(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departmet departmet = (Departmet) o;
        return id == departmet.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Departmet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
