package iuh.fit.vophuocviet_22730761_springjpa.controllers;


import iuh.fit.vophuocviet_22730761_springjpa.dtos.*;
import iuh.fit.vophuocviet_22730761_springjpa.models.Department;
import iuh.fit.vophuocviet_22730761_springjpa.models.Employee;
import iuh.fit.vophuocviet_22730761_springjpa.services.DepartmentService;
import iuh.fit.vophuocviet_22730761_springjpa.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor

@Controller

public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    // Hiển thị danh sách nhân viên
    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "employee/employee-list";
    }

    // Hiển thị form thêm nhân viên
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAll());
        return "employee/employee-form";
    }

    // Xử lý thêm nhân viên
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee, @RequestParam("department.deptId") String deptId) {
        Department dept = departmentService.getById(deptId).orElse(null);
        employee.setDepartment(dept);
        employeeService.create(employee);
        return "redirect:/employees";
    }

    // Hiển thị form sửa nhân viên
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Employee employee = employeeService.getById(id).orElse(null);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.getAll());
        return "employee/employee-form";
    }

    // Xử lý sửa nhân viên
    @PostMapping("/edit/{id}")
    public String editEmployee(@PathVariable String id, @ModelAttribute Employee employee, @RequestParam("department.deptId") String deptId) {
        employee.setEmpId(id);
        Department dept = departmentService.getById(deptId).orElse(null);
        employee.setDepartment(dept);
        employeeService.update(employee);
        return "redirect:/employees";
    }

    // Xử lý xóa nhân viên
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable String id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }

    @GetMapping("/search/name")
    public List<Employee> searchByName(@RequestParam String name) {
        return employeeService.searchByName(name);
    }

    @GetMapping("/search/age")
    public List<Employee> searchByAge(@RequestParam int age) {
        return employeeService.searchByAge(age);
    }

    @GetMapping("/search/salary")
    public List<Employee> searchBySalary(@RequestParam double salary) {
        return employeeService.searchBySalary(salary);
    }

    @GetMapping("/department/{deptId}")
    public List<Employee> getByDepartment(@PathVariable String deptId) {
        return employeeService.getEmployeesByDepartment(deptId);
    }

    @GetMapping("/max-salary")
    public List<Employee> getEmployeesWithMaxSalary() {
        return employeeService.findEmployeesWithMaxSalary();
    }

    @GetMapping("/max-age")
    public List<Employee> getEmployeesWithMaxAge() {
        return employeeService.findEmployeesWithMaxAge();
    }

    @GetMapping("/stats/departments")
    public List<AvgSalaryByDeptDTO> getEmployeeStatsByDepartment() {
        return employeeService.getEmployeeCountAndAvgSalaryByDepartment();
    }

    @GetMapping("/stats/status")
    public List<AvgSalaryByStatusDTO> getEmployeeStatsByStatus() {
        return employeeService.getEmployeeCountAndAvgSalaryByStatus();
    }

    @GetMapping("/sorted/departments")
    public List<DepartmentWithEmployeesDTO> getEmployeesSortedBySalaryPerDepartment() {
        return employeeService.getEmployeesSortedBySalaryPerDepartment();
    }
}
