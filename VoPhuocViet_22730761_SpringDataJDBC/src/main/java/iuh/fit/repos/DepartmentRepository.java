package iuh.fit.repos;

import iuh.fit.dtos.*;
import iuh.fit.models.*;
import iuh.fit.services.*;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface DepartmentRepository extends CrudRepository<Department, String> {

    @Modifying
    @Query("INSERT INTO department (dept_id, dept_name) VALUES (:deptId, :deptName)")
    void insert(@Param("deptId") String deptId, @Param("deptName") String deptName);
}
