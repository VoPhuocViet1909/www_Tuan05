package iuh.fit.bai06.dao;

import iuh.fit.bai06.model.DanhMuc;
import iuh.fit.bai06.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DanhMucDAO {
    private final DBUtil dbUtil;

    public DanhMucDAO(DataSource dataSource) {
        this.dbUtil = new DBUtil(dataSource);
    }

    // Lấy tất cả danh mục
    public List<DanhMuc> getAll() {
        List<DanhMuc> list = new ArrayList<>();
        String sql = "SELECT * FROM DANHMUC";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new DanhMuc(
                        rs.getInt("MADM"),
                        rs.getString("TENDANHMUC")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm mới danh mục
    public void add(DanhMuc dm) {
        String sql = "INSERT INTO DANHMUC(TENDANHMUC) VALUES (?)";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dm.getTenDM());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tìm danh mục theo MADM
    public DanhMuc getById(int maDM) {
        String sql = "SELECT * FROM DANHMUC WHERE MADM = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maDM);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new DanhMuc(
                            rs.getInt("MADM"),
                            rs.getString("TENDANHMUC")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Xóa danh mục theo MADM
    public void delete(int maDM) {
        String sql = "DELETE FROM DANHMUC WHERE MADM = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maDM);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Sửa tên danh mục
    public void update(DanhMuc dm) {
        String sql = "UPDATE DANHMUC SET TENDANHMUC = ? WHERE MADM = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dm.getTenDM());
            ps.setInt(2, dm.getMaDM());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}