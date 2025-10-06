package iuh.fit.vophuocviet_22730761_springjpa.controllers;


import iuh.fit.vophuocviet_22730761_springjpa.dtos.*;
import iuh.fit.vophuocviet_22730761_springjpa.models.Employee;
import iuh.fit.vophuocviet_22730761_springjpa.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable String id) {
        return employeeService.getById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        employee.setEmpId(id);
        return employeeService.update(employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        employeeService.delete(id);
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
