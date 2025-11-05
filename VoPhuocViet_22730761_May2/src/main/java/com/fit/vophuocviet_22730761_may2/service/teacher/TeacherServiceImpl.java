package com.fit.vophuocviet_22730761_may2.service.teacher;

import com.fit.vophuocviet_22730761_may2.entity.Teacher;
import com.fit.vophuocviet_22730761_may2.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
}
