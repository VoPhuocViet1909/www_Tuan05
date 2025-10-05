package iuh.fit.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import iuh.fit.dtos.*;
import iuh.fit.models.*;
import iuh.fit.services.*;
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
