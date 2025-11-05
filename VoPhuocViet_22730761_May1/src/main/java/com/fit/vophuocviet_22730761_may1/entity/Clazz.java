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

    @NotBlank (message = "Tên không được để trống")
    private String name;

    @PastOrPresent(message = " ngày bắt đầu phải sau ngày hiện tại")
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @PositiveOrZero(message = "số lượng phải lớn hơn 0")
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

    public @NotBlank(message = "Tên không được để trống") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Tên không được để trống") String name) {
        this.name = name;
    }

    public @PastOrPresent(message = " ngày bắt đầu phải sau ngày hiện tại") LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@PastOrPresent(message = " ngày bắt đầu phải sau ngày hiện tại") LocalDate startDate) {
        this.startDate = startDate;
    }

    public @PositiveOrZero(message = "số lượng phải lớn hơn 0") Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@PositiveOrZero(message = "số lượng phải lớn hơn 0") Integer quantity) {
        this.quantity = quantity;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }
}
