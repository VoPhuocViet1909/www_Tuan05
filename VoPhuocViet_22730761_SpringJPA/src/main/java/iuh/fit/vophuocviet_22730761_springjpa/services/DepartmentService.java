package iuh.fit.vophuocviet_22730761_springjpa.services;



import iuh.fit.vophuocviet_22730761_springjpa.models.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department create(Department department);

    List<Department> getAll();

    Optional<Department> getById(String id);

    Department update(Department department);

    void delete(String id);
}
