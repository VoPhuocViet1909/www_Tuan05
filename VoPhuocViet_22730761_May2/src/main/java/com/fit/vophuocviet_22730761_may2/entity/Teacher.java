package com.fit.vophuocviet_22730761_may2.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Teacher {
    public Teacher() {

    }

    public Teacher(int id, String name, String address, ChucVu chucVu, List<Clazz> clazzes) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.chucVu = chucVu;
        this.clazzes = clazzes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    @Enumerated (EnumType.STRING)
    private ChucVu chucVu;

    @OneToMany(mappedBy = "teacher")
    List<Clazz> clazzes;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public List<Clazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(List<Clazz> clazzes) {
        this.clazzes = clazzes;
    }
}
