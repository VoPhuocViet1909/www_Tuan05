package iuh.fit.services;



import iuh.fit.models.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department create(Department department);

    List<Department> getAll();

    Optional<Department> getById(String id);

    Department update(Department department);

    void delete(String id);
}
