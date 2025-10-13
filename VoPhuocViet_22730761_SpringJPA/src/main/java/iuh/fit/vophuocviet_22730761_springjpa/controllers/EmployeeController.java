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
//    @GetMapping
//    public String listEmployees(@RequestParam(value = "deptId", required = false) String deptId, Model model) {
//        List<Employee> employees;
//        if (deptId != null && !deptId.isEmpty()) {
//            employees = employeeService.getEmployeesByDepartment(deptId);
//        } else {
//            employees = employeeService.getAll();
//        }
//        model.addAttribute("employees", employees);
//        return "employee/employee-list";
//    }
//    @GetMapping
//    public String listEmployees(
//            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "age", required = false) Integer age,
//            @RequestParam(value = "salary", required = false) Double salary,
//            Model model) {
//
//        List<Employee> employees;
//
//        if (name != null && !name.isEmpty()) {
//            employees = employeeService.searchByName(name);
//        } else if (age != null) {
//            employees = employeeService.searchByAge(age);
//        } else if (salary != null) {
//            employees = employeeService.searchBySalary(salary);
//        } else {
//            employees = employeeService.getAll();
//        }
//        model.addAttribute("employees", employees);
//        return "employee/employee-list";
//    }
    @GetMapping
    public String listEmployees(@RequestParam(value = "deptId", required = false) String deptId, Model model) {
        List<Employee> employees;
        if (deptId != null && !deptId.isEmpty()) {
            employees = employeeService.getEmployeesByDepartment(deptId);
        } else {
            employees = employeeService.getAll();
        }
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

// --- Thống kê/đặc biệt ---

    // Nhân viên lương cao nhất
    @GetMapping("/max-salary-view")
    public String maxSalaryView(Model model) {
        List<Employee> employees = employeeService.findEmployeesWithMaxSalary();
        model.addAttribute("employees", employees);
        return "employee/employee-max-salary";
    }

    // Nhân viên lớn tuổi nhất
    @GetMapping("/max-age-view")
    public String maxAgeView(Model model) {
        List<Employee> employees = employeeService.findEmployeesWithMaxAge();
        model.addAttribute("employees", employees);
        return "employee/employee-max-age";
    }

    // Thống kê số lượng và lương TB theo phòng ban
    @GetMapping("/stats-department-view")
    public String statsDepartmentView(Model model) {
        List<AvgSalaryByDeptDTO> stats = employeeService.getEmployeeCountAndAvgSalaryByDepartment();
        model.addAttribute("stats", stats);
        return "employee/employee-stats-department";
    }

    // Thống kê số lượng và lương TB theo trạng thái
    @GetMapping("/stats-status-view")
    public String statsStatusView(Model model) {
        List<AvgSalaryByStatusDTO> stats = employeeService.getEmployeeCountAndAvgSalaryByStatus();
        model.addAttribute("stats", stats);
        return "employee/employee-stats-status";
    }

    // Danh sách nhân viên từng phòng ban, sắp xếp theo lương
    @GetMapping("/sorted-by-department-view")
    public String sortedByDepartmentView(Model model) {
        List<DepartmentWithEmployeesDTO> data = employeeService.getEmployeesSortedBySalaryPerDepartment();
        model.addAttribute("departments", data);
        return "employee/employee-sorted-by-department";
    }
}
