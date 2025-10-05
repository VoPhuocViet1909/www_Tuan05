package iuh.fit.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("department")
public class Department {

    @Id
    private String deptId;

    private String deptName;
}
