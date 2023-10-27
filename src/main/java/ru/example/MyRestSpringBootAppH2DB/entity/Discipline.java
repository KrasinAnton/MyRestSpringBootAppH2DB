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

    @Column(name = "title")
    private String title;

    @Column(name = "subgroup")
    private String subgroup;

    @Column(name = "lecture")
    private String lecture;

    @Column(name = "practice")
    private String practice;


    public Discipline() {

    }

    public Discipline(String title, String subgroup, String lecture,String practice) {
        this.title = title;
        this.subgroup = subgroup;
        this.lecture = lecture;
        this.practice = practice;
    }


}