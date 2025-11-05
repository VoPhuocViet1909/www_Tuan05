package com.fit.vophuocviet_22730761_may2.service.clazz;

import com.fit.vophuocviet_22730761_may2.entity.Clazz;
import com.fit.vophuocviet_22730761_may2.repository.ClazzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzRepository clazzRepository;
    @Override
    public List<Clazz> findAll() {
        return clazzRepository.findAll();
    }
    @Override
    public Clazz findById(Integer id) {
        return clazzRepository.findById(id).orElse(null);
    }
    @Override
    public Clazz save(Clazz clazz) {
        return clazzRepository.save(clazz);
    }
    @Override
    public List<Clazz> findByMonthStartDateBetween(int fromMonth, int toMonth) {
        return clazzRepository.findByMonthStartDateBetween(fromMonth, toMonth);
    }
    @Override
    public List<Clazz> findByClassNameOrTeacherName(String keyword) {
        return clazzRepository.findByNameClassOrTeacherName(keyword);
    }
}
