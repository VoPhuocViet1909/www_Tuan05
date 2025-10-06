package iuh.fit.vophuocviet_22730761_springjpa.services.impl;



import iuh.fit.vophuocviet_22730761_springjpa.dtos.*;
import iuh.fit.vophuocviet_22730761_springjpa.models.*;
import iuh.fit.vophuocviet_22730761_springjpa.repos.DepartmentRepository;
import iuh.fit.vophuocviet_22730761_springjpa.repos.EmployeeRepository;
import iuh.fit.vophuocviet_22730761_springjpa.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Employee create(Employee employee) {
        if (employeeRepository.existsById(employee.getEmpId())) {
            throw new IllegalArgumentException("Employee already exists: " + employee.getEmpId());
        }
        String deptId = employee.getDepartment().getDeptId();
        Department dept = departmentRepository.findById(deptId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + deptId));

        employee.setDepartment(dept);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getById(String id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee update(Employee employee) {
        if (!employeeRepository.existsById(employee.getEmpId())) {
            throw new IllegalArgumentException("Employee not found: " + employee.getEmpId());
        }

        String deptId = employee.getDepartment().getDeptId();
        Department dept = departmentRepository.findById(deptId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + deptId));

        employee.setDepartment(dept);
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(String id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> searchByName(String name) {
        return employeeRepository.findByEmpNameContainingIgnoreCase(name);
    }

    @Override
    public List<Employee> searchByAge(int age) {
        return employeeRepository.findByAge(age);
    }

    @Override
    public List<Employee> searchBySalary(double salary) {
        return employeeRepository.findBySalaryGreaterThanEqual(salary);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String deptId) {
        return employeeRepository.findByDepartment_DeptId(deptId);
    }

    @Override
    public List<Employee> findEmployeesWithMaxSalary() {
        return employeeRepository.findEmployeesWithMaxSalary();
    }

    @Override
    public List<Employee> findEmployeesWithMaxAge() {
        return employeeRepository.findEmployeesWithMaxAge();
    }

    @Override
    public List<AvgSalaryByDeptDTO> getEmployeeCountAndAvgSalaryByDepartment() {
        return employeeRepository.getEmployeeCountAndAvgSalaryByDepartment();
    }

    @Override
    public List<AvgSalaryByStatusDTO> getEmployeeCountAndAvgSalaryByStatus() {
        return employeeRepository.getEmployeeCountAndAvgSalaryByStatus();
    }

    @Override
    public List<DepartmentWithEmployeesDTO> getEmployeesSortedBySalaryPerDepartment() {
        return employeeRepository.getEmployeesSortedBySalaryPerDepartment().stream()
                .collect(Collectors.groupingBy(x-> x.getDepartment().getDeptId()))
                .entrySet().stream()
                .map(e -> new DepartmentWithEmployeesDTO(
                        e.getKey(),
                        e.getValue().stream()
                                .map(emp -> new EmployeeSalaryByDeptDTO(
                                        emp.getDepartment().getDeptId(),
                                        emp.getEmpId(),
                                        emp.getEmpName(),
                                        emp.getSalary()
                                ))
                                .toList()
                ))
                .toList();
    }
}
