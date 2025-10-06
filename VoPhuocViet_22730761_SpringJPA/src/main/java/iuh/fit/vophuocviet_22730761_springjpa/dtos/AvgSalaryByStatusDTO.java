package iuh.fit.vophuocviet_22730761_springjpa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvgSalaryByStatusDTO {
    private int status;
    private long count;
    private double avgSalary;
}