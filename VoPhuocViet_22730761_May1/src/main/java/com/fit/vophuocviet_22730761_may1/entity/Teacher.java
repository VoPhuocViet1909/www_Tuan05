package com.fit.vophuocviet_22730761_may1.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private  String name;
    private  String address;

    @Enumerated(EnumType.STRING)
    private  ChucVu chucVu;

    @OneToMany (mappedBy = "teacherId")
    private List<Clazz> clazzes;

    public Teacher() {
    }

    public Teacher(Integer id, String name, String address, ChucVu chucVu, List<Clazz> clazzes) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.chucVu = chucVu;
        this.clazzes = clazzes;
    }

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
