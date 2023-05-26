package com.example.contact;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Consultation {
    private String name;

    public Consultation(String name, String label, String value) {
        this.name = name;
        this.label = label;
        this.value = value;
    }
    private String label;
    private String value;

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                ", name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}