package iuh.fit.vophuocviet_22730761_springjpa.controllers;


import iuh.fit.vophuocviet_22730761_springjpa.models.Department;
import iuh.fit.vophuocviet_22730761_springjpa.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public Department create(@RequestBody Department department) {
        return departmentService.create(department);
    }

    @GetMapping
    public List<Department> getAll() {
        return departmentService.getAll();
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable String id) {
        return departmentService.getById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable String id, @RequestBody Department department) {
        department.setDeptId(id);
        return departmentService.update(department);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        departmentService.delete(id);
    }
}
