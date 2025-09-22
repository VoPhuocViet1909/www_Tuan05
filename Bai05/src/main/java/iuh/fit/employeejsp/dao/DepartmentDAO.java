package iuh.fit.employeejsp.dao;

import iuh.fit.employeejsp.model.Department;
import iuh.fit.employeejsp.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private final DBUtil dbUtil;

    // Constructor nhận DataSource từ context.xml
    public DepartmentDAO(DataSource dataSource) {
        this.dbUtil = new DBUtil(dataSource);
    }

    // Lấy tất cả departments
    public List<Department> getAll() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM departments";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy department theo id
    public Department getById(int id) {
        Department dep = null;
        String sql = "SELECT * FROM departments WHERE department_id=?";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dep = new Department(
                            rs.getInt("department_id"),
                            rs.getString("department_name")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dep;
    }

    // Thêm department mới
    public void save(Department dep) {
        String sql = "INSERT INTO departments(department_name) VALUES (?)";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dep.getDepartment_name());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cập nhật department
    public void update(Department dep) {
        String sql = "UPDATE departments SET department_name=? WHERE department_id=?";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dep.getDepartment_name());
            ps.setInt(2, dep.getDepartment_id());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa department
    public void delete(int id) {
        String sql = "DELETE FROM departments WHERE department_id=?";

        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
