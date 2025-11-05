package com.fit.vophuocviet_22730761_may1.service.clazz;

import com.fit.vophuocviet_22730761_may1.entity.Clazz;

import java.util.List;

public interface ClazzService {
    List<Clazz> findAll();

    Clazz findById(Integer id);

    List<Clazz> findByMonthStartDateBeween(int fromMonth, int toMonth);

    List<Clazz> findByNameClassOrTeacherName(String keyword);

    Clazz save(Clazz clazz);
}
