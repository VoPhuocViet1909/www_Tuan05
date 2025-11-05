package com.fit.vophuocviet_22730761_may2.service.clazz;

import com.fit.vophuocviet_22730761_may2.entity.Clazz;

import java.util.List;

public interface ClazzService {
    List<Clazz> findAll();

    Clazz findById(Integer id);

    Clazz save(Clazz clazz);

    List<Clazz> findByMonthStartDateBetween(int fromMonth, int toMonth);

    List<Clazz> findByClassNameOrTeacherName(String keyword);
}
