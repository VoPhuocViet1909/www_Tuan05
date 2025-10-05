package iuh.fit.dtos;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentWithEmployeesDTO {
    private String deptId;
    private List<EmployeeSalaryByDeptDTO> employees;
}