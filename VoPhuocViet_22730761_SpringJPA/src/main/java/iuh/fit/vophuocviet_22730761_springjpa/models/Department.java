package iuh.fit.vophuocviet_22730761_springjpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "departments")
@Entity
public class Department {

    @Id
    private String deptId;

    private String deptName;
}
