package iuh.fit.employeejsp.dao;

import iuh.fit.employeejsp.model.Employee;
import iuh.fit.employeejsp.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private final DBUtil dbUtil;

    // Constructor nhận DataSource từ context.xml
    public EmployeeDAO(DataSource dataSource) {
        this.dbUtil = new DBUtil(dataSource);
    }

    // Lấy toàn bộ nhân viên
    public List<Employee> getAllEmployees() {
        List<Employee> emplist = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setDepartmentId(rs.getInt("department_id"));
                emplist.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return emplist;
    }

    // Lấy nhân viên theo phòng ban
    public List<Employee> getAllByDepartment(int deptId) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE department_id=?";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, deptId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Employee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("department_id"),
                            rs.getDouble("salary")
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm nhân viên mới
    public void save(Employee emp) {
        String sql = "INSERT INTO employees(name, salary, department_id) VALUES (?,?,?)";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setDouble(2, emp.getSalary());
            ps.setInt(3, emp.getDepartmentId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cập nhật nhân viên
    public void update(Employee emp) {
        String sql = "UPDATE employees SET name=?, salary=?, department_id=? WHERE id=?";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setDouble(2, emp.getSalary());
            ps.setInt(3, emp.getDepartmentId());
            ps.setInt(4, emp.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa nhân viên theo id
    public void delete(int id) {
        String sql = "DELETE FROM employees WHERE id=?";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Lấy nhân viên theo id
    public Employee getById(int id) {
        Employee emp = null;
        String sql = "SELECT * FROM employees WHERE id=?";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    emp = new Employee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("department_id"),
                            rs.getDouble("salary")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }



}
