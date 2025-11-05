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

}
