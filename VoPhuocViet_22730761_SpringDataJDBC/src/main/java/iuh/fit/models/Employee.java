package iuh.fit.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("employee")
public class Employee {

    @Id
    private String empId;

    private String empName;
    private String email;
    private int age;
    private int status;
    private double salary;
    private String deptId;
}
