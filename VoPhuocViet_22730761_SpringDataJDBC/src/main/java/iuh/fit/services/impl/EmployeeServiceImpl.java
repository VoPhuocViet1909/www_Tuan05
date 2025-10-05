package iuh.fit.services.impl;

import iuh.fit.dtos.*;
import iuh.fit.models.*;
import iuh.fit.repos.EmployeeRepository;
import iuh.fit.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final JdbcTemplate jdbcTemplate;
    @Override
    public Employee create(Employee employee) {
        if (employeeRepository.existsById(employee.getEmpId())) {
            throw new IllegalArgumentException("Employee already exists: " + employee.getEmpId());
        }
        employeeRepository.insert(
                employee.getEmpId(),
                employee.getEmpName(),
                employee.getEmail(),
                employee.getAge(),
                employee.getStatus(),
                employee.getDeptId(),
                employee.getSalary()
        );
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        return (List<Employee>) employeeRepository.findAll();
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
        return employeeRepository.findByDeptId(deptId);
    }

    @Override
    public List<Employee> findEmployeesWithMaxSalary() {
        String sql = """
            SELECT * FROM employee 
            WHERE salary = (SELECT MAX(salary) FROM employee)
        """;
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public List<Employee> findEmployeesWithMaxAge() {
        String sql = """
            SELECT * FROM employee 
            WHERE age = (SELECT MAX(age) FROM employee)
        """;
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public List<AvgSalaryByDeptDTO> getEmployeeCountAndAvgSalaryByDepartment() {
        String sql = """
            SELECT dept_id, COUNT(*) as count, AVG(salary) as avgSalary
            FROM employee
            GROUP BY dept_id
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new AvgSalaryByDeptDTO(
                        rs.getString("dept_id"),
                        rs.getInt("count"),
                        rs.getDouble("avgSalary")
                )
        );
    }

    @Override
    public List<AvgSalaryByStatusDTO> getEmployeeCountAndAvgSalaryByStatus() {
        String sql = """
            SELECT status, COUNT(*) as count, AVG(salary) as avgSalary
            FROM employee
            GROUP BY status
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new AvgSalaryByStatusDTO(
                        rs.getInt("status"),
                        rs.getInt("count"),
                        rs.getDouble("avgSalary")
                )
        );
    }

    @Override
    public List<DepartmentWithEmployeesDTO> getEmployeesSortedBySalaryPerDepartment() {
        String sql = """
        SELECT dept_id, emp_id, emp_name, salary
        FROM employee
        ORDER BY dept_id, salary ASC
    """;

        List<EmployeeSalaryByDeptDTO> employees = jdbcTemplate.query(sql, (rs, rowNum) ->
                new EmployeeSalaryByDeptDTO(
                        rs.getString("dept_id"),
                        rs.getString("emp_id"),
                        rs.getString("emp_name"),
                        rs.getDouble("salary")
                )
        );

        return employees.stream()
                .collect(Collectors.groupingBy(EmployeeSalaryByDeptDTO::getDeptId))
                .entrySet().stream()
                .map(e -> new DepartmentWithEmployeesDTO(e.getKey(), e.getValue()))
                .toList();
    }
}
