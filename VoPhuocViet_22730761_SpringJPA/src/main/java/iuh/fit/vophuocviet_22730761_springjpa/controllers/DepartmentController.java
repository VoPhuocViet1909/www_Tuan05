package iuh.fit.vophuocviet_22730761_springjpa.controllers;


import iuh.fit.vophuocviet_22730761_springjpa.models.Department;
import iuh.fit.vophuocviet_22730761_springjpa.models.Employee;
import iuh.fit.vophuocviet_22730761_springjpa.services.DepartmentService;
import iuh.fit.vophuocviet_22730761_springjpa.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    // Hiển thị danh sách phòng ban
    @GetMapping
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments", departments);
        return "department/department-list";
    }

    // Hiển thị form thêm phòng ban
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        return "department/department-form";
    }

    // Xử lý thêm phòng ban
    @PostMapping("/add")
    public String addDepartment(@ModelAttribute Department department) {
        departmentService.create(department);
        return "redirect:/departments";
    }

    // Hiển thị form sửa phòng ban
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Department department = departmentService.getById(id).orElse(null);
        List<Employee> employees = employeeService.getEmployeesByDepartment(id);
        model.addAttribute("department", department);
        model.addAttribute("employees", employees);
        return "department/department-form";
    }

    // Xử lý sửa phòng ban
    @PostMapping("/edit/{id}")
    public String editDepartment(@PathVariable String id, @ModelAttribute Department department) {
        department.setDeptId(id);
        departmentService.update(department);
        return "redirect:/departments";
    }

    // Xóa phòng ban
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable String id) {
        departmentService.delete(id);
        return "redirect:/departments";
    }
}
