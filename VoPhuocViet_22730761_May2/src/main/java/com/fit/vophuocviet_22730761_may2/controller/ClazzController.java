package com.fit.vophuocviet_22730761_may2.controller;

import com.fit.vophuocviet_22730761_may2.entity.Clazz;
import com.fit.vophuocviet_22730761_may2.service.clazz.ClazzService;
import com.fit.vophuocviet_22730761_may2.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private TeacherService teacherService;

    @GetMapping
    public String getListClazz(Model model) {
        List<Clazz> clazzes = clazzService.findAll();
        model.addAttribute("clazzes", clazzes);
        return "clazz-list";
    }
}
