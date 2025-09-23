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
                        rs.getString("TENDANHMUC"),
                        rs.getString("TENDANHMUC"),
                        rs.getString("GHICHU")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}