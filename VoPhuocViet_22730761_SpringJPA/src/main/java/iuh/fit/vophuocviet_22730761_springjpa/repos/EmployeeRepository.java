package iuh.fit.vophuocviet_22730761_springjpa.repos;


import iuh.fit.vophuocviet_22730761_springjpa.dtos.AvgSalaryByDeptDTO;
import iuh.fit.vophuocviet_22730761_springjpa.dtos.AvgSalaryByStatusDTO;
import iuh.fit.vophuocviet_22730761_springjpa.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findByEmpNameContainingIgnoreCase(String name);
    List<Employee> findByAge(int age);
    List<Employee> findBySalaryGreaterThanEqual(double salary);

    List<Employee> findByDepartment_DeptId(String departmentDeptId);

    @Query("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e2.salary) FROM Employee e2)")
    List<Employee> findEmployeesWithMaxSalary();

    @Query("SELECT e FROM Employee e WHERE e.age = (SELECT MAX(e2.age) FROM Employee e2)")
    List<Employee> findEmployeesWithMaxAge();

    @Query("SELECT new iuh.fit.vophuocviet_22730761_springjpa.dtos.AvgSalaryByDeptDTO(" +
            "e.department.deptId, COUNT(e), AVG(e.salary)) " +
            "FROM Employee e GROUP BY e.department.deptId")
    List<AvgSalaryByDeptDTO> getEmployeeCountAndAvgSalaryByDepartment();


    @Query("SELECT new iuh.fit.vophuocviet_22730761_springjpa.dtos.AvgSalaryByStatusDTO(e.status, COUNT(e), AVG(e.salary)) " +
            "FROM Employee e GROUP BY e.status")
    List<AvgSalaryByStatusDTO> getEmployeeCountAndAvgSalaryByStatus();

    @Query("SELECT e FROM Employee e ORDER BY e.department.deptId, e.salary ASC")
    List<Employee> getEmployeesSortedBySalaryPerDepartment();
}