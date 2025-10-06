package iuh.fit.vophuocviet_22730761_springjpa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvgSalaryByDeptDTO {
    private String deptId;
    private long count;
    private double avgSalary;
}