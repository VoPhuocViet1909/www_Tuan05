package com.fit.vophuocviet_22730761_may1.repository;

import com.fit.vophuocviet_22730761_may1.entity.Clazz;
import com.fit.vophuocviet_22730761_may1.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
