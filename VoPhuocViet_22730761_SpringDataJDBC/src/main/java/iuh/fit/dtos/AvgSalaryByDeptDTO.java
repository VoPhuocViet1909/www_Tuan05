package iuh.fit.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvgSalaryByDeptDTO {
    private String deptId;
    private int count;
    private double avgSalary;
}