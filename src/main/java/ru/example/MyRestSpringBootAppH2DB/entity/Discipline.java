package ru.example.MyRestSpringBootAppH2DB.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name =  "DISCIPLINES")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    // Конструкторы класса
    public Discipline() {
        // Пустой конструктор по умолчанию
    }

    public Discipline(String name, String description) {
        this.name = name;
        this.description = description;
    }


}