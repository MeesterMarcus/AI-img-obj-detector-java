package com.marcuslorenzana.imageobjectdetector.entities;

import jakarta.persistence.*;

@Entity(name = "objects")
public class ObjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ObjectEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}