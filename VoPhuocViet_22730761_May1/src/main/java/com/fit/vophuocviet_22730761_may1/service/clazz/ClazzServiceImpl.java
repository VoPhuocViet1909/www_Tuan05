package com.fit.vophuocviet_22730761_may1.service.clazz;

import com.fit.vophuocviet_22730761_may1.entity.Clazz;
import com.fit.vophuocviet_22730761_may1.repository.ClazzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzRepository clazzRepository;

    @Override
    public List<Clazz> findAll(){
        return  clazzRepository.findAll();
    }

    @Override
    public Clazz findById(Integer id){
        return clazzRepository.findById(id).orElse(null);
    }

    @Override
    public List<Clazz>  findByMonthStartDateBeween(int fromMonth, int toMonth){
        return clazzRepository.findByMonthStartDateBeween(fromMonth,toMonth);
    }

    @Override
    public List<Clazz> findByNameClassOrTeacherName(String keyword){
        return  clazzRepository.findByNameClassOrTeacherName(keyword);
    }

    @Override
    public Clazz save(Clazz clazz){
        return  clazzRepository.save(clazz);
    }


}
