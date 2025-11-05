package com.fit.vophuocviet_22730761_may1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;


@Entity
@Table (name = "Clazz")
public class Clazz {

    public Clazz( ) {
    }

    public Clazz(Integer id, String name, LocalDate startDate, Integer quantity, Teacher teacherId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.quantity = quantity;
        this.teacherId = teacherId;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private LocalDate startDate;


    private Integer quantity;

    @ManyToOne
    @JoinColumn (name = "teacherId")
    private Teacher teacherId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }
}