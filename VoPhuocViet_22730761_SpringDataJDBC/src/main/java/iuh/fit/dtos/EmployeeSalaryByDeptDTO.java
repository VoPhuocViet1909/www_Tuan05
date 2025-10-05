package iuh.fit.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSalaryByDeptDTO {
    private String deptId;
    private String empId;
    private String empName;
    private double salary;
}