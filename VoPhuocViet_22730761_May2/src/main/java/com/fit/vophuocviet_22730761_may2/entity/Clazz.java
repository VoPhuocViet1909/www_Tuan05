package com.fit.vophuocviet_22730761_may2.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Clazz {

    public Clazz( ) {
    }

    public Clazz(int id, String name, LocalDate startDate, int quantity, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.quantity = quantity;
        this.teacher = teacher;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate startDate;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
