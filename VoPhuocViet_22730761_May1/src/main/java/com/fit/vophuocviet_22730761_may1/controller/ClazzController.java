package com.fit.vophuocviet_22730761_may1.controller;

import com.fit.vophuocviet_22730761_may1.entity.Clazz;
import com.fit.vophuocviet_22730761_may1.service.clazz.ClazzService;
import com.fit.vophuocviet_22730761_may1.service.teacher.TeacherServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/clazzes")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private TeacherServiceI teacherServiceI;

    @GetMapping
    public String getListClazz(Model model){
        List <Clazz> clazzes = clazzService.findAll();
        model.addAttribute("clazzes", clazzes);
        return "clazz-list";
    }

}
