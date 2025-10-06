package iuh.fit.vophuocviet_22730761_springjpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Entity
public class Employee {

    @Id
    private String empId;

    private String empName;
    private String email;
    private int age;
    private int status;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "deptId")
    private Department department;
}
